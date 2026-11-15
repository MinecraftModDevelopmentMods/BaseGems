# Building Base Gems for Minecraft 1.21.1

The build follows the MMD mod template: Gradle 9.8 with NeoForge's
ModDevGradle, Java 21 and Parchment mappings. Gradle downloads Java 21 itself
if it isn't installed.

```sh
./gradlew build
```

On Windows, use `gradlew.bat`. The game jar is
`build/libs/BaseGems-4.0.0.121012-development.jar`; a release tag sets the
version instead (see [releasing](RELEASING.md)). `./gradlew runClient` and
`./gradlew runServer` start the game from `run/`.

MMDLib and OreSpawn come from the MMD Maven repository at
`https://maven.moddev.zone/releases`. Their versions are in `gradle.properties`.

## Where things live

- `src/main/resources/mmdlib/material/` holds one MMDLib material per gem: its
  statistics, forms, mining tier and runtime settings. MMDLib registers the
  items, blocks and projectiles from these files.
- `src/main/resources/mmdlib/item/` and `mmdlib/block/` add Base Gems' own
  forms: `basegems:powder`, `basegems:bucket` and `basegems:ore`, which keep
  the existing item IDs and give ores their XP.
- `src/main/resources/assets/` holds the textures, language files and the
  shared shield and plate models.
- `src/generated/resources/` holds the blockstates, models, recipes,
  advancements, tags and loot tables written by `tools/generate_resources.py`.

After changing a material, a form or the art, run the generator from the
project root and commit its output:

```sh
python3 tools/generate_resources.py
```

The build does not run it. Use a fresh test world when checking the OreSpawn
defaults. In Low Fantasy, try mining and trading a gem, then check that
storage blocks are still craftable and equipment recipes have gone. See
[ore generation](ORE_GENERATION.md) for the placement rules and overrides.
