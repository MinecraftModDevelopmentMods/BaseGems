"""Translate the legacy art and build the data pack for Forge 31."""

from pathlib import Path
import json
import shutil
import sys

PROJECT = Path(__file__).resolve().parent.parent
ASSETS = PROJECT / "src/main/resources/assets/basegems"
OUTPUT = Path(sys.argv[1]).resolve()
if (PROJECT / "build").resolve() not in OUTPUT.parents or OUTPUT.name != "basegems-resources":
    raise SystemExit(f"Refusing to clear an unexpected output directory: {OUTPUT}")
if OUTPUT.exists():
    shutil.rmtree(OUTPUT)

GEMS = sorted(path.stem.removesuffix("_ore") for path in (ASSETS / "blockstates").glob("*_ore.json"))
assert len(GEMS) == 33
FORMS = ("block", "ore", "plate", "bars", "door", "trapdoor", "button", "slab",
         "lever", "pressure_plate", "stairs", "wall")
TEMPLATES = json.loads((PROJECT / "gradle/model-templates.json").read_text(encoding="utf-8"))


def write(path, data):
    target = OUTPUT / path
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8", newline="\n")


def asset(path, data):
    write(f"assets/basegems/{path}", data)


def generated_model(texture):
    return {"parent": "minecraft:item/generated", "textures": {"layer0": texture}}


def convert_models():
    for path in (ASSETS / "models").rglob("*.json"):
        if any(path.stem.endswith("_" + armour) for armour in
               ("helmet", "chestplate", "leggings", "boots", "horse_armor")):
            continue
        data = json.loads(path.read_text(encoding="utf-8"))
        if data.get("parent") in ("block/half_slab", "minecraft:block/half_slab"):
            data["parent"] = "minecraft:block/slab"
        elif data.get("parent") in ("block/upper_slab", "minecraft:block/upper_slab"):
            data["parent"] = "minecraft:block/slab_top"
        asset(path.relative_to(ASSETS).as_posix(), data)
    for path, template in TEMPLATES.items():
        if "{gem}" not in path:
            asset(path, template)
            continue
        for gem in GEMS:
            data = json.loads(json.dumps(template).replace("{gem}", gem))
            for key, texture in data.get("textures", {}).items():
                if not texture.startswith("basegems:block/"):
                    continue
                texture_path = texture.removeprefix("basegems:block/")
                if not (ASSETS / "textures/blocks" / (texture_path + ".png")).is_file():
                    texture_path = gem + "_block"
                data["textures"][key] = "basegems:blocks/" + texture_path
            asset(path.replace("{gem}", gem), data)
    for gem in GEMS:
        asset(f"blockstates/{gem}.json", {"variants": {"": {"model": "basegems:block/molten_gem"}}})
        asset(f"models/item/{gem}.json", generated_model(f"basegems:items/{gem}_ingot"))
        asset(f"models/item/{gem}_bucket.json", {"parent": "minecraft:item/generated", "textures": {"layer0": "minecraft:item/bucket", "layer1": "basegems:items/bucket_fluid", "layer2": "basegems:items/bucket_overlay"}})
        for form in FORMS:
            path = f"{gem}_{form}"
            if form == "door":
                model = generated_model(f"basegems:blocks/{gem}_door_bottom")
            elif form == "slab":
                model = {"parent": "basegems:block/half_slab_" + gem}
            else:
                suffix = {"bars": "_bars_post", "button": "_button_inventory", "trapdoor": "_trapdoor_bottom",
                          "wall": "_wall_inventory", "pressure_plate": "_pressure_plate_up"}.get(form, "_" + form)
                model = {"parent": "basegems:block/" + gem + suffix}
            asset(f"models/item/{path}.json", model)
    asset("models/block/molten_gem.json", {"textures": {"particle": "basegems:block/molten_metal_still"}})


def convert_languages():
    for path in (ASSETS / "lang").glob("*.lang"):
        values = {}
        for line in path.read_text(encoding="utf-8-sig").splitlines():
            if not line or line.startswith("#") or "=" not in line:
                continue
            key, value = line.split("=", 1)
            if key.startswith(("item.basegems.", "tile.basegems.")) and key.endswith(".name"):
                key = key.removesuffix(".name")
                if key.startswith("tile."):
                    key = "block." + key.removeprefix("tile.")
            values[key] = value
        values["itemGroup.basegems"] = "Base Gems"
        values.setdefault("config.basegems.restart", "Restart Minecraft to apply changes.")
        for gem in GEMS:
            name = values.get(f"item.basegems.{gem}_ingot", gem)
            values[f"item.basegems.{gem}_bucket"] = values.get(f"fluid.basegems.{gem}", name) + " Bucket"
        values.setdefault("config.basegems.connection.mismatch", "Base Gems settings differ from this server. Set these values in config/basegems-common.toml, then restart Minecraft and reconnect: %s")
        asset(f"lang/{path.stem.lower()}.json", values)


def item(gem, form):
    return {"item": f"basegems:{gem}_{form}"}


def tag(kind, gem=None):
    return {"tag": f"forge:{kind}" + (f"/{gem}" if gem else "")}


def vanilla(name):
    return {"item": f"minecraft:{name}"}


def game_data(kind, path, data):
    write(f"data/basegems/{kind}/{path}.json", data)


def advancement(gem, name, trigger):
    recipe = f"basegems:{gem}_{name}"
    predicate = {"ingot": tag("gems", gem), "nugget": tag("nuggets", gem),
                 "block": tag("storage_blocks", gem), "rod": tag("rods", gem),
                 "plate": tag("plates", gem)}.get(trigger, item(gem, trigger))
    game_data("advancements/recipes", f"{gem}_{name}", {
        "parent": "minecraft:recipes/root",
        "rewards": {"recipes": [recipe]},
        "criteria": {
            "has_ingredient": {"trigger": "minecraft:inventory_changed",
                               "conditions": {"items": [predicate]}},
            "has_recipe": {"trigger": "minecraft:recipe_unlocked", "conditions": {"recipe": recipe}},
        },
        "requirements": [["has_ingredient", "has_recipe"]],
    })


def crafting(gem):
    gem_tag, nugget, block, rod, gear = (tag(kind, gem) for kind in
                                        ("gems", "nuggets", "storage_blocks", "rods", "gears"))
    stick = tag("rods/wooden")

    def recipe(name, form, count, trigger, body):
        body["result"] = dict(item(gem, form), count=count)
        game_data("recipes", f"{gem}_{name}", {"type": "basegems:content_crafting", "recipe": body})
        advancement(gem, name, trigger)

    def shaped(form, count, trigger, pattern, key, name=None):
        recipe(name or form, form, count, trigger,
               {"type": "minecraft:crafting_shaped", "pattern": pattern.split("|"), "key": key})

    def shapeless(name, form, count, trigger, ingredient):
        recipe(name, form, count, trigger, {"type": "minecraft:crafting_shapeless", "ingredients": [ingredient]})

    shaped("block", 1, "ingot", "xxx|xxx|xxx", {"x": gem_tag})
    shapeless("unpack_block", "ingot", 9, "block", block)
    shaped("ingot", 1, "nugget", "xxx|xxx|xxx", {"x": nugget})
    shapeless("unpack_gem", "nugget", 9, "ingot", gem_tag)
    shaped("powder", 1, "smallpowder", "xxx|xxx|xxx", {"x": item(gem, "smallpowder")})
    shapeless("unpack_powder", "smallpowder", 9, "powder", item(gem, "powder"))
    for form, count, trigger, pattern, ingredients in (
        ("plate", 3, "ingot", "xxx", {"x": gem_tag}),
        ("slab", 6, "block", "xxx", {"x": block}),
        ("lever", 1, "ingot", "s|x", {"s": stick, "x": gem_tag}),
        ("stairs", 4, "block", "x  |xx |xxx", {"x": block}),
        ("wall", 6, "block", "xxx|xxx", {"x": block}),
        ("bars", 16, "ingot", "xxx|xxx", {"x": gem_tag}),
        ("pressure_plate", 1, "ingot", "xx", {"x": gem_tag}),
        ("door", 3, "ingot", "xx|xx|xx", {"x": gem_tag}),
        ("trapdoor", 1, "ingot", "xx|xx", {"x": gem_tag}),
        ("button", 1, "nugget", "x|x", {"x": nugget}),
        ("rod", 4, "ingot", "x|x", {"x": gem_tag}),
        ("gear", 1, "rod", " x |xrx| x ", {"x": gem_tag, "r": rod}),
        ("crackhammer", 1, "block", "x|s|s", {"x": block, "s": stick}),
        ("axe", 1, "ingot", "xx|xs| s", {"x": gem_tag, "s": stick}),
        ("hoe", 1, "ingot", "xx| s| s", {"x": gem_tag, "s": stick}),
        ("pickaxe", 1, "ingot", "xxx| s | s ", {"x": gem_tag, "s": stick}),
        ("shovel", 1, "ingot", "x|s|s", {"x": gem_tag, "s": stick}),
        ("sword", 1, "ingot", "x|x|s", {"x": gem_tag, "s": stick}),
        ("shears", 1, "ingot", " x|x ", {"x": gem_tag}),
        ("arrow", 4, "nugget", "x|r|f", {"x": nugget, "r": rod, "f": vanilla("feather")}),
        ("bow", 1, "rod", " rs|r s| rs", {"r": rod, "s": vanilla("string")}),
        ("bolt", 4, "rod", "r|r|f", {"r": rod, "f": vanilla("feather")}),
        ("crossbow", 1, "rod", "srr| gr|r s", {"r": rod, "g": gear, "s": vanilla("string")}),
        ("fishing_rod", 1, "rod", "  r| rs|r s", {"r": rod, "s": vanilla("string")}),
        ("shield", 1, "ingot", "xpx|xxx| x ", {"x": gem_tag, "p": {"tag": "minecraft:planks"}}),
    ):
        shaped(form, count, trigger, pattern, ingredients)
    shaped("bars", 4, "rod", "xxx", {"x": rod}, name="bars_from_rods")
    game_data("recipes", f"{gem}_repair_shield", {"type": "basegems:plate_repair",
              "target": f"basegems:{gem}_shield", "plate": tag("plates", gem)})
    for form in ("ore", "powder"):
        game_data("recipes", f"{gem}_{form}_smelting", {"type": "basegems:smelting",
                  "ingredient": item(gem, form), "result": item(gem, "ingot"), "experience": 0.45})
        advancement(gem, f"{form}_smelting", form)
        game_data("recipes", f"{gem}_{form}_blasting", {"type": "basegems:blasting",
                  "ingredient": item(gem, form), "result": item(gem, "ingot"), "experience": 0.45})
        advancement(gem, f"{form}_blasting", form)
    for form, result, count in (("ore", "powder", 2), ("ingot", "powder", 1), ("block", "powder", 9),
                                ("nugget", "smallpowder", 1)):
        game_data("recipes", f"{gem}_{form}_crushing", {"type": "basegems:crushing",
                  "ingredient": item(gem, form), "result": dict(item(gem, result), count=count)})


def generate_tags():
    for category, form in {"gems": "ingot", "ingots": "ingot", "nuggets": "nugget", "dusts": "powder",
                           "tiny_dusts": "smallpowder", "rods": "rod", "gears": "gear", "ores": "ore",
                           "storage_blocks": "block", "plates": "plate"}.items():
        for gem in GEMS:
            entry = {"replace": False, "values": [f"basegems:{gem}_{form}"]}
            write(f"data/forge/tags/items/{category}/{gem}.json", entry)
            if form in ("ore", "block", "plate"):
                write(f"data/forge/tags/blocks/{category}/{gem}.json", entry)
        write(f"data/forge/tags/items/{category}.json",
              {"replace": False, "values": [f"#forge:{category}/{gem}" for gem in GEMS]})
        if form in ("ore", "block", "plate"):
            write(f"data/forge/tags/blocks/{category}.json",
                  {"replace": False, "values": [f"#forge:{category}/{gem}" for gem in GEMS]})
    write("data/forge/tags/items/rods/wooden.json", {"replace": False, "values": ["minecraft:stick"]})
    write("data/minecraft/tags/items/arrows.json",
          {"replace": False, "values": [f"basegems:{gem}_arrow" for gem in GEMS]})


def loot(gem):
    for form in FORMS:
        block = f"basegems:{gem}_{form}"
        entry = {"type": "minecraft:item", "name": block}
        conditions = [{"condition": "minecraft:survives_explosion"}]
        if form == "door":
            conditions.append({"condition": "minecraft:block_state_property", "block": block,
                               "properties": {"half": "lower"}})
        if form == "slab":
            entry["functions"] = [{"function": "minecraft:set_count", "count": 2, "conditions": [
                {"condition": "minecraft:block_state_property", "block": block, "properties": {"type": "double"}}]}]
        if form == "ore":
            entry = {"type": "minecraft:alternatives", "children": [
                {"type": "minecraft:item", "name": block, "conditions": [{"condition": "minecraft:match_tool",
                    "predicate": {"enchantments": [{"enchantment": "minecraft:silk_touch", "levels": {"min": 1}}]}}]},
                {"type": "minecraft:item", "name": f"basegems:{gem}_ingot", "functions": [
                    {"function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune", "formula": "minecraft:ore_drops"},
                    {"function": "minecraft:explosion_decay"}]}]}
            conditions = []
        game_data("loot_tables/blocks", f"{gem}_{form}", {"type": "minecraft:block", "pools": [
            {"rolls": 1, "entries": [entry], "conditions": conditions}]})
    game_data("loot_tables/blocks", f"double_{gem}_slab", {"type": "minecraft:block", "pools": [
        {"rolls": 1, "entries": [{"type": "minecraft:item", "name": f"basegems:{gem}_slab",
         "functions": [{"function": "minecraft:set_count", "count": 2}]}],
         "conditions": [{"condition": "minecraft:survives_explosion"}]}]})
    game_data("loot_tables/blocks", gem, {"type": "minecraft:block", "pools": []})


convert_models()
convert_languages()
generate_tags()
for gem in GEMS:
    crafting(gem)
    loot(gem)
