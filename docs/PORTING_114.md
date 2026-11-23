# Minecraft 1.14.4 port

This port starts from released Base Gems 1.13.2 at `a89b4444`. Modern Metals
1.14.4 supplies working Forge 28 examples. Gem properties, mining levels,
saved names and the deposit distribution stay with Base Gems.

- Forge 28.2.26, MCP snapshot 20190719-1.14.3 and config 20190829.143755.
  Gradle uses Java 17, Mavenizer uses Java 25, and compilation and Minecraft
  still use Java 8.
- All solid blocks have native loot tables. Ore drops retain Fortune and
  Silk Touch, with no XP from Silk Touch. Doors drop from their lower half;
  double slabs yield two ordinary slabs. Fluids have empty loot tables.
- Recipe types and serializers use Forge 28's registries and crafting
  inventories. Blast furnaces process the same ore and powder as ordinary
  furnaces at half the cooking time. Shared crushing keeps each recipe ID.
- Smith trades use the armorer, toolsmith and weaponsmith professions.
  Gem rarity, bundles and prices carry over, with villager XP and restocking.
- Native fluid attributes supply animated still/flow textures and gem
  colours. The old fluid-renderer coremod is removed. Fluid construction
  stays in sequential registration, before blocks and buckets need it.
- Both configuration entry points use native 1.14 screens. The common TOML
  file and every key stay unchanged. Fantasy Level remains Base Gems' own
  setting, latched until restart. A login handshake checks it and all gem
  switches before the client receives recipes.
- Tag-based recipes also discover through their tags. Shield repair stays
  manual because the recipe book cannot place damaged equipment reliably.
- The legacy registry snapshot and old IDs remain. World hooks target the
  1.14 save/chunk loader, with `full` as the completed chunk status. The
  obsolete Forge 25 leaves-fixer patch is removed.
- OreSpawn 4.1.7.114041 owns generation. The 33 existing rule IDs, heights,
  patterns, hosts and rarity settings are unchanged. Generation remains
  Overworld-only, with retrogen off. No fallback generator or terrain engine
  was added.
- Ore overlays use Base Metals' corrected 0.05 model-unit separation from
  the stone layer, with explicit 0..16 face UVs to keep sampling inside each
  texture sprite. The shared template generates all 33 block and item models.

References: [Forge's porting notes](https://docs.minecraftforge.net/en/1.14.x/legacy/porting1214/),
the exact mapped Forge 28 sources, and
[OreSpawn 4.1.7.114041](https://github.com/MinecraftModDevelopmentMods/OreSpawn/releases/tag/4.1.7.114041).

The build checks generated data, startup ordering, Java 8 artifacts and
metadata. Explicit server/client probes cover native loot, recipes, discovery,
trades, fluids, models and saving/reopening. They live in separate source
sets and are excluded from player jars and ordinary launches. Expensive
gameplay checks run locally; CI builds and audits the candidate.

Manual acceptance and publication remain separate steps. See the local
verification receipt for the exact jars, copied-world comparisons and retained
failed attempts, and [manual testing](MANUAL_TESTING.md) for the playtest.
