# Base Gems for Minecraft 1.12.2

Base Gems adds 33 gemstones to Minecraft. Mine agate and amethyst in upper
caves, look deeper for ruby and sapphire, or head down near bedrock for black
diamond. Trade gems with smiths or use them for storage blocks, decorative
pieces, tools, weapons and shields.

## Installation

Use Java 8 and Forge **14.23.5.2859 or later for Minecraft 1.12.2**. Put these
jars in the `mods` folder on both the client and server.

- Base Gems `2.5.0.112021`.
- [MMDLib 1.0.0 RC3](https://www.curseforge.com/minecraft/mc-mods/mmdlib/files/9414723).
- [MMD OreSpawn 4.1.6.112021](https://www.curseforge.com/minecraft/mc-mods/mmd-orespawn/files/9527784),
  or a compatible 4.x update.

You can play with just those three mods. If you also use Base Metals, this
build was checked with [Base Metals 2.6.2.112021](https://www.curseforge.com/minecraft/mc-mods/base-metals/files/9532211).

## Mining and trading

Gem ores give XP when mined. Fortune can increase the gem yield, and Silk
Touch lets you take the ore block home. Smiths buy and sell gems for
emeralds, with rarer gems appearing as their careers advance.

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

Open **Mods → Base Gems → Config** or choose **Base Gems** from OreSpawn's
**Mods** list. Both screens save to `config/BaseGems.cfg`. Restart the game or
server after changing Fantasy Level or switching a gem on or off.

**High Fantasy** is the default and includes gem tools, weapons, shields and
decorative pieces. Player and horse armour are left to add-ons.
**Low Fantasy** keeps mining, trades, gem processing, plates and storage blocks.
It leaves out recipes for things like ruby doors, gemstone gears and gem tools.
Base Gems has its own Fantasy Level. Use the same setting on the server and
client in multiplayer.

## Mod compatibility

Other mods can use the Ore Dictionary names, such as `gemRuby`, `oreRuby`,
`blockRuby`, `dustRuby` and `gearRuby`. The block and item IDs, config keys
and Ore Dictionary names carry over from 1.11.2.

## Development and credits

The 1.12.2 branch uses ForgeGradle 7 and builds a Java 8 mod.
See [building and Eclipse setup](docs/BUILDING.md) and the
[changelog](CHANGELOG.txt).

The gem artwork comes from the 1.12.2 branch. Thanks to Aedda, Eejit,
jmlovelace, KiriCattus and the Tinkers' Construct team for their work.
[Artwork credits and licences](LICENSE-ART.txt)
