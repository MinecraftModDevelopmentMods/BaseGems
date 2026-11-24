# Testing Base Gems 1.15.2

Use Java 8, Forge 31.2.57 and OreSpawn 4.1.7.115021. The game jar is
`build/libs/BaseGems-2.5.0.115021.jar`. Base Metals 3.0.3.115021 is optional.

Build the siblings, run `pinLocalCompanions`, then run `prepareEclipse` separately. Choose a normal launch:

- `runClient.launch`: Base Gems and OreSpawn.
- `runClientWithBaseMetals.launch`: adds the selected Base Metals candidate.
- `runClientWithModernMetals.launch`: adds the pinned Modern Metals build.
- `runClientWithBaseAndModern.launch`: adds both pinned metal candidates.

Use the matching versions in a separate launcher instance if testing packaged
jars. Avoid duplicate copies of the same mod.

Start with a fresh Survival player. Pick up gems, nuggets, rods and storage
blocks and check their recipe-book entries. A tagged equivalent from another
mod should teach the recipes that accept it. Test both ordinary and blast
furnaces; the blast furnace should take half the time. Check gem crushing
with a gem hammer, then the available Base Metals hammers.

Mine several gems with the required tool level, Fortune and Silk Touch.
Ordinary mining should give gems and XP; Silk Touch gives the ore without
XP. Check a door, slab, double slab and plate for their correct drops.
Look at several gem ores from shallow angles and in the inventory, including
with your usual resource pack. Their overlays should stay clear of the stone
without flickering or showing pixels from neighbouring textures.

Damage a named or enchanted gem shield. One matching plate in a crafting
grid should fully repair it and keep its other data. The repair itself
should not appear in the recipe book. Try bows, bolts and crossbows, a
fishing rod and a filled bucket as well.

Visit an armourer, toolsmith and weaponsmith. Check gem offers, emerald
prices, restocking and progression. Armour remains left to add-ons.

Open the config from both Mods and OreSpawn. Test the gem pages, Done,
Cancel and returning to the parent screen. Restart after changing Fantasy
Level or a gem switch. Low Fantasy should retain mining, processing, plates,
storage and gem trades while hiding equipment and decorative recipes.

For multiplayer, use the same Fantasy Level and gem switches on both sides.
Change one client setting, restart and reconnect: the connection should
stop before joining and name the required TOML key and server value.
Restore it and restart before reconnecting.

Use a copy for world upgrades. Keep the legacy registry sidecar, then check
placed gems, door states, inventories, buckets and worn equipment. Save,
leave and reopen the copy. See [migration](MIGRATION.md).
