# Minecraft 1.15.2 port

Starts from the published 1.14.4 release, commit `87de48f964d47476a98b1e6af01bfc7657f04212`.
The existing Gradle 9/ForgeGradle 7 build is retained. Forge 31.2.57 matches
the local Base Metals and OreSpawn targets and Forge's recommended MDK; the
official MDK SHA-1 is `42343d3e5d531475276a55c2016572fef87c3355`. Compilation
and Minecraft use Java 8, Gradle Java 17 and Mavenizer Java 25.

The port keeps all gem names, config keys, recipes, Fantasy modes and ore
distribution. No new flattening is needed after the 1.13 registry conversion.
OreSpawn owns all generation; this mod only supplies its ore provider. Native
1.15 render layers and entity rendering replace 1.14 render hooks. Resource
and data packs use format 5. All 33 ores keep the corrected 0.05 overlay spacing
and explicit face UVs. World-data hooks were checked against mapped 1.15
classes and the sibling Base Metals hook before testing an upgraded copy.

Client setup registers render layers through `RenderTypeLookup` and registers
the arrow and bolt renderers by `EntityType`. The shared fluid implementation
uses the mapped `canDisplace` hook, and item/entity positions use native
accessors. The config screens retain the target's matrix-free signatures.
The save-format, chunk-loader and worldgen-protection coremod descriptors
match the mapped 1.15 classes; their existing legacy conversion is retained.

Published dependencies: OreSpawn 4.1.7.115021 and optional Base Metals 3.0.3.115021.
Their player jars are SHA-256 pinned. No Modern Metals 1.15.2 release is
available, so it is omitted from dependency pins and manual profiles.

References: [Forge porting guide](https://docs.minecraftforge.net/en/1.15.x/legacy/porting/),
[1.14 to 1.15 primer](https://docs.neoforged.net/primer/docs/1.15/),
[1.15.2 release notes](https://www.minecraft.net/en-us/article/minecraft-java-edition-1-15-2)
and the exact Forge 31 mapped classes.

Local checks cover all recipe IDs and discovery advancements, Fortune/Silk
Touch/XP, both Fantasy modes, disabled gems, smith trades, furnace accumulation,
ore dimension boundaries, all item models and all 33 ore block/item overlays.
Standalone and Base Metals clients check fluid layers, both projectile renderers,
both config entry points and integrated-world save/reopen. Seven packaged
server boots include a copied 1.14 world upgrade and reopen; the comparison
retains 20,703 gem blocks and 1,128 stacks with their states and item data.
The original fixture remains unchanged. Tests stay outside player jars and
ordinary Eclipse profiles. Manual gameplay and visual acceptance remain next.

The later stack pass corrects all gem-bar connection templates and tests the
local Modern Metals candidate. Four isolated profiles use local hash pins when
present; public/release dependency resolution remains separate.
