# Upgrading Base Gems worlds to 1.15.2

Close the game and copy the whole save. Install the 1.15.2 versions of Base
Gems, OreSpawn and every other mod that owns blocks or items in the world.
Open the copy with Java 8 and Forge 31.2.57. Keep the original until you have
checked your builds and inventories.

For worlds from 1.10–1.12, first use released Base Gems 2.5.0.113021 on
Minecraft 1.13.2. Keep `basegems-legacy-registry.dat` with that world when
moving on through 1.14 and 1.15, especially if some old chunks remain
unvisited. The 1.15 port retains the old block and item names, fluid and bucket
IDs, named block states and the legacy snapshot reader. Both old double-slab blocks and
ordinary slabs remain registered.

Use Optimize World or the server's `--forceUpgrade` option to convert every
region at once. Reopen the copy and check the places and inventories that
matter to you. OreSpawn keeps its world profile; gem rules retain their old
IDs, so upgrading does not create a second family of deposits.

The focused test receipt records the copied world used for automated checks.
It does not replace a backup or manual acceptance of your own modpack.
