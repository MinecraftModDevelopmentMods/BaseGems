# Upgrading Base Gems worlds to 1.21.1

1. Close Minecraft or stop the server. Copy the whole world directory before
   upgrading, and keep the copy until you're happy with the result.
2. Install Base Gems 4.0.0, MMDLib 2.0.2 or later and MMD OreSpawn 4.1.1 or
   later on NeoForge for 1.21.1, plus 1.21.1 versions of the other mods that
   placed blocks or items in the world.
3. Open the world. Use Optimize World or the server's `--forceUpgrade`
   option to convert every region at once; otherwise chunks convert as you
   visit them.

## From 1.13.2

Base Gems 2.5.0.113021 worlds upgrade directly. Gem block and item IDs are
unchanged and Minecraft converts their states. A few things change:

- Double slabs were `basegems:double_<gem>_slab`. They load as
  `basegems:<gem>_double_slab`, which still drops two slabs.
- Gem walls connect with 1.16's low and tall sides. Walls saved in 1.13.2
  pick up their connections when a neighbouring block changes.
- Arrows and bolts in flight used shared `basegems:custom_arrow` and
  `custom_bolt` entities. These are now one entity per gem, so projectiles
  that were mid-flight when the world was saved are dropped.
- Set Fantasy Level and gem switches again if you changed them. The config
  is still `config/basegems-common.toml` with the same keys.

## From 1.12.2 and earlier

Upgrade the world on 1.13.2 with Base Gems 2.5.0.113021 first. That version
reads the old Forge registry and keeps the gems through Minecraft's 1.13
flattening; 1.21.1 cannot do this. Then follow the 1.13.2 steps above.
