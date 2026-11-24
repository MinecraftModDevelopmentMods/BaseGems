# Base Gems for Minecraft 1.15.2

Base Gems adds 33 gemstones to Minecraft. Mine agate and amethyst in upper
caves, look deeper for ruby and sapphire, or head down near bedrock for black
diamond. Trade gems with smiths or use them for storage blocks, decorative
pieces, tools, weapons and shields.

## Installation

Use Java 8 and Forge **31.2.57 for Minecraft 1.15.2** on both client and
server. Base Gems needs MMD OreSpawn and runs without MMDLib.

Use [MMD OreSpawn 4.1.7.115021](https://github.com/MinecraftModDevelopmentMods/OreSpawn/releases/tag/4.1.7.115021)
or a later compatible 1.15.2 release. It includes the fix for checking cave air
at generation boundaries; no separate local engine is needed.

Base Metals is optional for a new world. When installed together, use
[Base Metals 3.0.3.115021](https://github.com/MinecraftModDevelopmentMods/BaseMetals/releases/tag/3.0.3.115021)
or a later compatible 1.15.2 release. If your old
world contains Base Metals blocks or items, keep it installed for the upgrade.

Gem crushing works with the Base Gems crackhammer on its own. With Base
Metals installed its tools and crushers can use the same recipes. [Modern Metals 3.0.0.115021](https://github.com/MinecraftModDevelopmentMods/ModernMetals/releases/tag/3.0.0.115021)
also supplies shared crushing when installed.

## Upgrading a world

Make a separate copy before opening a 1.14.4 world in 1.15.2. Keep the
whole save, including `basegems-legacy-registry.dat` if it is present. Worlds
from 1.10–1.12 should first go through the released Base Gems 1.13.2 upgrade.
[World upgrade details](docs/MIGRATION.md)

## Mining and trading

Use a tool strong enough for the gem. Agate and jade need stone level;
jasper, ruby and sapphire need iron level; black diamond needs diamond level.
A weaker tool breaks the ore without a drop. These are the same mining
requirements as in 1.12.

Gem ores give XP when mined. Fortune can increase the gem yield, and Silk
Touch lets you take the ore block home. Armourers, toolsmiths and weaponsmiths buy and sell gems for emeralds,
with rarer gems appearing at higher levels. Their offers restock as they work.
In High Fantasy, toolsmiths and weaponsmiths also sell gem equipment.

OreSpawn handles placement in the Overworld. Amber and moldavite turn up in
shallow sediment pockets, while gems such as topaz and beryl tend to appear
in short veins. Use OreSpawn's world settings to change where gems are found.
Existing chunks stay as they are. [Where to find gems](docs/ORE_GENERATION.md)

The ore overlays use Minecraft's stone texture underneath. If a resource pack
changes stone, the background of the gem ores changes with it.

## Crafting and configuration

Pick up a gem to learn what you can make with it in the recipe book. Some
recipes appear when you pick up a processed part, such as a gem plate.
Tagged gems and parts from other mods teach the same recipes when they can
be used as ingredients. The recipes you learn follow your Fantasy Level and
gem settings. Smelt gem ore or powder in a furnace, or use a blast furnace
for the same result in half the time.

Repair a damaged gem shield by putting it and one matching plate in a
crafting grid. Its name and enchantments stay. Repair is a manual recipe
and does not appear in the recipe book.

Open **Mods > Base Gems > Config** or choose **Base Gems** from OreSpawn's
**Mods** list. Both screens save to `config/basegems-common.toml`. Restart the
game or server after changing Fantasy Level or switching a gem on or off.
Gem switches keep existing blocks and items available in old worlds.

**High Fantasy** is the default and includes gem tools, weapons, shields and
decorative pieces. Player and horse armour are left to add-ons.
**Low Fantasy** keeps mining, trades, gem processing, plates and storage blocks.
It leaves out recipes for things like ruby doors, gemstone gears and gem tools.
Base Gems has its own Fantasy Level. Multiplayer checks it and all gem
switches before joining; if they differ, the connection message lists the
settings to change. Restart after changing them, then reconnect.

## Mod compatibility

The Ore Dictionary names have moved to tags such as `forge:gems/ruby`,
`forge:ores/ruby`, `forge:storage_blocks/ruby`, `forge:dusts/ruby` and
`forge:gears/ruby`. The existing Base Gems block and item names carry over.
The older MMDLib machine integrations, equipment recycling recipes and shield
upgrade recipes are not part of this port.

## Development and credits

The 1.15.2 branch uses ForgeGradle 7 and builds a Java 8 mod.
See [building and Eclipse setup](docs/BUILDING.md) and the
[manual testing](docs/MANUAL_TESTING.md), [porting notes](docs/PORTING_115.md)
and the [changelog](CHANGELOG.txt).

The gem artwork comes from the 1.12.2 branch. Thanks to Aedda, Eejit,
jmlovelace, KiriCattus and the Tinkers' Construct team for their work.
[Artwork credits and licences](LICENSE-ART.txt)
