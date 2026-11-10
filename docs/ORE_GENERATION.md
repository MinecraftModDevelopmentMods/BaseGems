# Where to find gems

Base Gems uses OreSpawn 4 to place small deposits throughout the Overworld.
Look for agate, jasper and carnelian in upper caves, beryl and topaz farther
down, and black diamond near bedrock. Amber and moldavite turn up in shallow
sediment pockets. These are starting depths. The ranges overlap so one
mining trip can turn up several types.

| Setting | Gems | How they generate |
| --- | --- | --- |
| Chalcedony and quartz | Agate, jasper, carnelian, onyx, amethyst, citrine, ametrine | Compact clusters and short veins in upper and middle stone. Amethyst is easier to find than ametrine. |
| Shallow deposits | Amber, moldavite, malachite, turquoise, opal | Amber uses dirt, clay, sand and gravel. Moldavite favours gravel. The others use small pockets in stone or sedimentary rock. |
| Pegmatites | Beryl, aquamarine, golden beryl, heliodor, morganite, indicolite, lepidolite, moonstone, topaz, blue topaz | Short, mid-depth veins, generally one to three blocks per attempt. Intrusive rocks are eligible when present. |
| Metamorphic and volcanic deposits | Garnet, jade, iolite, alexandrite, spinel, tanzanite, ruby, sapphire, violet sapphire, peridot | Small clusters biased toward the bottom of their ranges. Garnet is relatively common. Ruby and sapphire mostly reward deeper mining. |
| Black diamond | Black diamond | A single-block find at Y 5–16. Some candidates exposed to cave air are skipped. |

The groupings draw on natural gemstone settings, including gem-rich
pegmatites, marble-hosted ruby and spinel, and sediment-hosted amber and opal.
Moldavite's shallow gravel placement represents redeposited impact glass.
Black diamond's depth is a Minecraft progression choice. Vanilla stone is a
fallback for hard-rock gems, so players can find every gem without a geology
mod. [USGS gemstone environments](https://pubs.usgs.gov/gip/gemstones/environment.html),
[GIA pegmatites](https://my.gia.edu/gems-gemology/summer-2025-colored-stones-unearthed),
[GIA gemstone deposits](https://www.gia.edu/gia-website/gems-gemology/winter-2025-colored-stones-unearthed),
[GIA sedimentary deposits](https://www.gia.edu/gems-gemology/winter-2023-colored-stones-unearthed),
[GIA moldavite](https://www.gia.edu/gems-gemology/summer-2021-labnotes-imitation-moldavite)

## Changing the defaults

Adjust frequencies, heights and hosts in OreSpawn's world settings or use
`config/basegems-orespawn.json` for a pack-wide provider override. Each rule
uses a stable ID such as `basegems:ore/agate`. The ore block still uses
`basegems:agate_ore`. The shipped table is in
[`GemWorldgen.java`](../src/main/java/zone/moddev/mc/basegems/worldgen/GemWorldgen.java).
Frequencies are expected attempts per chunk. A deposit may place fewer blocks
than its budget if it runs out of suitable host rock.

The provider covers the Overworld and leaves terrain replacement and
retrogen off. In existing worlds, use new chunks to find the ores and review
the saved OreSpawn settings for that world. When a material is disabled in
`BaseGems.cfg`, its ore block and default rule are omitted on restart.
Remove the corresponding rule from any pack-wide override when disabling a
gem, since the override can only refer to registered blocks.
