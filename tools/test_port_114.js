const assert = require('node:assert/strict');
const test = require('node:test');
const fs = require('node:fs');
const path = require('node:path');
const root = path.resolve(__dirname, '..');
const generated = path.join(root, 'build/generated/basegems-resources');
const load = name => JSON.parse(fs.readFileSync(path.join(generated, name), 'utf8'));
const base = 'data/basegems/';
const gems = [...fs.readFileSync(path.join(root, 'src/main/java/zone/moddev/mc/basegems/data/Gem.java'), 'utf8')
  .matchAll(/new Gem\("([a-z]+)", "(Enable\w+)"/g)].map(m => m[1]);

test('all gem ores use the wider Base Metals overlay spacing in blocks and inventory', () => {
  assert.equal(gems.length, 33);
  for (const gem of gems) {
    const model = load(`assets/basegems/models/block/${gem}_ore.json`);
    assert.equal(model.textures.base, 'minecraft:block/stone', gem);
    assert.equal(model.textures.overlay, `basegems:blocks/ore_overlays/${gem}_ore`, gem);
    assert.equal(model.elements.length, 2, gem);
    for (const [index, element] of model.elements.entries()) {
      assert.deepEqual(element.from, Array(3).fill(index ? -0.05 : 0), gem);
      assert.deepEqual(element.to, Array(3).fill(index ? 16.05 : 16), gem);
      assert.deepEqual(Object.keys(element.faces).sort(), ['down','east','north','south','up','west'], gem);
      for (const [direction, face] of Object.entries(element.faces)) {
        assert.equal(face.texture, index ? '#overlay' : '#base', gem);
        assert.equal(face.cullface, direction, gem);
      }
    }
    assert.equal(load(`assets/basegems/models/item/${gem}_ore.json`).parent, `basegems:block/${gem}_ore`, gem);
  }
});

test('every ore face keeps explicit UV coordinates inside its texture sprite', () => {
  for (const gem of gems) {
    const model = load(`assets/basegems/models/block/${gem}_ore.json`);
    for (const element of model.elements) for (const face of Object.values(element.faces)) {
      assert.deepEqual(face.uv, [0,0,16,16], gem);
    }
  }
});

test('all 33 gems have Fortune, Silk Touch and explosion-aware ore loot', () => {
  assert.equal(gems.length, 33);
  for (const gem of gems) {
    const entries = load(base + `loot_tables/blocks/${gem}_ore.json`).pools[0].entries[0].children;
    assert.equal(entries[0].name, `basegems:${gem}_ore`);
    assert.equal(entries[0].conditions[0].predicate.enchantments[0].enchantment, 'minecraft:silk_touch');
    assert.equal(entries[1].name, `basegems:${gem}_ingot`);
    assert.deepEqual(entries[1].functions.map(f => f.function), ['minecraft:apply_bonus', 'minecraft:explosion_decay']);
  }
});

test('every solid block and fluid has a native loot table', () => {
  const forms = ['block','ore','plate','bars','door','trapdoor','button','slab','lever','pressure_plate','stairs','wall'];
  for (const gem of gems) {
    for (const form of forms) assert.equal(load(base + `loot_tables/blocks/${gem}_${form}.json`).type, 'minecraft:block');
    assert.equal(load(base + `loot_tables/blocks/double_${gem}_slab.json`).pools[0].entries[0].functions[0].count, 2);
    assert.deepEqual(load(base + `loot_tables/blocks/${gem}.json`).pools, []);
  }
  assert.equal(fs.readdirSync(path.join(generated, base, 'loot_tables/blocks')).length, 462);
});

test('both furnace types retain every ore and powder recipe and unlock', () => {
  for (const gem of gems) for (const form of ['ore','powder']) for (const method of ['smelting','blasting']) {
    const name = `${gem}_${form}_${method}`;
    const recipe = load(base + `recipes/${name}.json`);
    assert.equal(recipe.type, `basegems:${method}`);
    assert.equal(recipe.ingredient.item, `basegems:${gem}_${form}`);
    assert.equal(recipe.result.item, `basegems:${gem}_ingot`);
    assert.deepEqual(load(base + `advancements/recipes/${name}.json`).rewards.recipes, [`basegems:${name}`]);
  }
});

test('tagged crafting discovery follows a complete accepted recipe ingredient', () => {
  let tagged = 0;
  for (const name of fs.readdirSync(path.join(generated, base, 'advancements/recipes'))) {
    const advancement = load(base + 'advancements/recipes/' + name);
    const recipe = load(base + 'recipes/' + name);
    const discovery = advancement.criteria.has_ingredient.conditions.items[0];
    if (discovery.tag) tagged++;
    const plain = recipe.recipe || recipe;
    const ingredients = plain.key ? Object.values(plain.key) : plain.ingredients || [plain.ingredient];
    assert.ok(ingredients.some(i => JSON.stringify(i) === JSON.stringify(discovery)), name);
  }
  assert.equal(tagged, 990);
});

test('all manual shield repair IDs remain but have no book advancement', () => {
  for (const gem of gems) {
    assert.equal(load(base + `recipes/${gem}_repair_shield.json`).type, 'basegems:plate_repair');
    assert.ok(!fs.existsSync(path.join(generated, base, `advancements/recipes/${gem}_repair_shield.json`)));
  }
  assert.equal(fs.readdirSync(path.join(generated, base, 'recipes')).length, 1353);
  assert.equal(fs.readdirSync(path.join(generated, base, 'advancements/recipes')).length, 1188);
});

test('all eighteen locale options have the connection message', () => {
  const directory = path.join(generated, 'assets/basegems/lang');
  const locales = fs.readdirSync(directory);
  assert.equal(locales.length, 18);
  for (const locale of locales) {
    const data = load('assets/basegems/lang/' + locale);
    assert.ok(data['config.basegems.connection.mismatch'].includes('%s'), locale);
  }
});
