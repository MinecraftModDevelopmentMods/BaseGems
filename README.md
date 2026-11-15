# Base Gems for Minecraft 1.21.1

Base Gems adds 33 gemstones to Minecraft. Mine agate and amethyst in upper
caves, look deeper for ruby and sapphire, or head down near bedrock for black
diamond. Trade gems with smiths or use them for storage blocks, decorative
pieces, tools, weapons and shields.

## Installation

Use Java 21 and NeoForge **21.1.265 or later for Minecraft 1.21.1** on both
client and server. Base Gems needs:

- [MMDLib](https://www.curseforge.com/minecraft/mc-mods/mmdlib) 2.0.2 or later.
  The gems, their items and blocks are MMDLib materials.
- [MMD OreSpawn](https://www.curseforge.com/minecraft/mc-mods/mmd-orespawn)
  4.1.1.121012 or a later 4.x release, which places the gem deposits.

Base Metals is optional. Its crackhammers and crushers use the same
`mmdlib:crushing` recipes as Base Gems.

## Upgrading a world

Copy the world before you open it. Worlds from Base Gems 2.5.0 for 1.13.2
upgrade directly: block and item names are unchanged, and old double slabs
become the new `basegems:<gem>_double_slab` blocks. Worlds from 1.12.2 or
earlier must first be upgraded with Base Gems 2.5.0.113021 on 1.13.2.
[World upgrade details](docs/MIGRATION.md)

## Mining and trading

Use a tool strong enough for the gem. Agate and jade need stone level;
jasper, ruby and sapphire need iron level; black diamond needs diamond level.
A weaker tool breaks the ore without a drop.

Gem ores give XP when mined. Fortune can increase the gem yield, and Silk
Touch lets you take the ore block home. Armourers, toolsmiths and
weaponsmiths buy and sell gems for emeralds, with rarer gems appearing at
higher levels. In High Fantasy, toolsmiths and weaponsmiths also sell gem
equipment.

OreSpawn handles placement in the Overworld. Amber and moldavite turn up in
shallow sediment pockets, while gems such as topaz and beryl tend to appear
in short veins. Use OreSpawn's world settings to change where gems are found.
Existing chunks stay as they are. [Where to find gems](docs/ORE_GENERATION.md)

The ore overlays use Minecraft's stone texture underneath. If a resource pack
changes stone, the background of the gem ores changes with it.

## Crafting and configuration

Pick up a gem to learn what you can make with it in the recipe book. Some
recipes appear when you pick up a processed part, such as a gem plate.
The recipes you learn follow your Fantasy Level and gem settings.
You can smelt gem ore or powder in a furnace to get the gem back.

Gem crackhammers are MMDLib crackhammers. Right-click a dropped gem, ore or
block to crush it into powder; sneak to crush the whole stack. Mining with a
crackhammer crushes the drops that have a crushing recipe, but never the gems.

Open **Mods → Base Gems → Config** or choose **Base Gems** from OreSpawn's
**Mods** list. Both screens save to `config/basegems-common.toml`. Restart the
game or server after changing Fantasy Level or switching a gem on or off.
Gem switches keep existing blocks and items available in old worlds.

**High Fantasy** is the default and includes gem tools, weapons, shields and
decorative pieces. Player and horse armour are left to add-ons.
**Low Fantasy** keeps mining, trades, gem processing, plates and storage blocks.
It leaves out recipes for things like ruby doors, gemstone gears and gem tools.
Use the same setting on the server and client in multiplayer.

## Mod compatibility

Gems use the common tags, such as `c:gems/ruby`, `c:ores/ruby`,
`c:storage_blocks/ruby`, `c:dusts/ruby`, `c:nuggets/ruby` and `c:gears/ruby`.
The older `forge:` tags are gone. Gem arrows and bolts are in `minecraft:arrows`,
so bows and crossbows fire both.

## Development and credits

See [building](docs/BUILDING.md), [releasing](docs/RELEASING.md) and the
[changelog](CHANGELOG.txt).

The gem artwork comes from the 1.12.2 branch. Thanks to Aedda, Eejit,
jmlovelace, KiriCattus and the Tinkers' Construct team for their work.
Unused armour and scythe art is kept in `extras/donor-art` for add-ons.
[Artwork credits and licences](LICENSE-ART.txt)
