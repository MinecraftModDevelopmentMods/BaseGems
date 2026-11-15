const assert = require('node:assert/strict');
const test = require('node:test');
const fs = require('node:fs');
const path = require('node:path');
const root = path.join(__dirname, '../src/main/java/zone/moddev/mc/basegems');
const mod = fs.readFileSync(path.join(root, 'BaseGems.java'), 'utf8');
const content = fs.readFileSync(path.join(root, 'content/ModContent.java'), 'utf8');

test('parallel construction does not write the shared fluid registry', () => {
  assert.ok(!/initializeFluids\s*\(/.test(mod));
});
test('the sequential block event binds fluids before creating blocks', () => {
  assert.ok(/@SubscribeEvent\s+public static void registerBlocks\(RegistryEvent\.Register<Block> event\)\s*\{\s*initializeFluids\(\);/.test(content));
  assert.equal((content.match(/initializeFluids\(\);/g) || []).length, 1);
});
test('fluid registration is private to its registry subscriber', () => {
  assert.ok(/private static void initializeFluids\(\)/.test(content));
});
