# Upgrading Base Gems worlds to 1.13.2

1. Close Minecraft or stop the old server. Copy the whole world directory
   before upgrading. Keep the original save and its mods available.
2. Use Java 8 and Forge 25.0.223. Install the 1.13.2 versions of Base Gems and
   MMD OreSpawn, plus 1.13.2 ports of the other mods that placed blocks or
   items in the world. The migration only handles Base Gems content. For
   worlds with Base Metals, use its 1.13.2 version too.
3. Open the copy. Use Optimize World or the server's `--forceUpgrade` option
   to convert every region at once. Normal loading converts chunks as you
   visit them. Keep the `basegems-legacy-registry.dat` file that Base Gems
   writes in the save, especially if some old chunks are still unvisited.
4. Check the places and inventories that matter to you and keep the old
   save until you're happy with the upgrade.

Minecraft 1.13 replaces numeric block IDs and metadata with named block
states. Base Gems reads the IDs assigned to the world, including the
Forge snapshot in `level.dat_old` when needed. It preserves the old gem
names and restores settings such as slab halves, stair facing, plate
orientation, fluid levels and the hinge and opening on both halves of a
door. Old universal buckets containing molten gems become named gem
buckets. Item counts, tool damage and existing item tags are carried over.

Base Gems creates a `*.basegems-legacy-backup` next to any level or player
file it needs to rewrite. Minecraft writes converted chunks in place. The
untouched copy of the original world is what you need if you want to go back.

Set Fantasy Level and gem switches in the new Mods config screen after
the upgrade. The old `BaseGems.cfg` is a reference for the settings you used
before. OreSpawn owns its saved world profiles.
