"""Writes Base Gems' generated resources for Minecraft 1.21.1.

Run from the project root after changing a gem, a form or the art:

    python3 tools/generate_resources.py

Reads the gem list from src/main/resources/mmdlib/material/ and the textures from
src/main/resources/assets/basegems/textures/, then replaces src/generated/resources/
with the blockstates, models, recipes, advancements, tags and loot tables. The output
is committed; the build does not run this script.
"""

from pathlib import Path
import json
import shutil

PROJECT = Path(__file__).resolve().parent.parent
RESOURCES = PROJECT / "src/main/resources"
TEXTURES = RESOURCES / "assets/basegems/textures"
OUTPUT = PROJECT / "src/generated/resources"

GEMS = sorted(path.stem for path in (RESOURCES / "mmdlib/material").glob("*.json"))
MATERIALS = {gem: json.loads((RESOURCES / f"mmdlib/material/{gem}.json").read_text(encoding="utf-8"))
             for gem in GEMS}
SIMPLE_ITEMS = ("ingot", "nugget", "powder", "smallpowder", "rod", "gear", "arrow", "bolt", "shears")
HANDHELD_ITEMS = ("pickaxe", "axe", "hoe", "shovel", "sword", "crackhammer")
# Everything a pickaxe mines, except the molten gem.
MINED_FORMS = ("block", "ore", "plate", "bars", "door", "trapdoor", "button", "lever",
               "pressure_plate", "slab", "double_slab", "stairs", "wall")


def write(path, data):
    target = OUTPUT / path
    target.parent.mkdir(parents=True, exist_ok=True)
    target.write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8", newline="\n")


def asset(path, data):
    write(f"assets/basegems/{path}", data)


def game_data(path, data):
    write(f"data/{path}", data)


def bg(path):
    return f"basegems:{path}"


def block_texture(gem, name):
    """The gem's own texture for a part, or its storage block's texture when there is none."""
    return bg(f"block/{gem}_{name}") if (TEXTURES / f"block/{gem}_{name}.png").is_file() else bg(f"block/{gem}_block")


def model(path, parent, **textures):
    asset(f"models/{path}.json", {"parent": parent, "textures": textures} if textures else {"parent": parent})


def simple_state(gem, form, target=None):
    asset(f"blockstates/{gem}_{form}.json", {"variants": {"": {"model": bg(f"block/{target or gem + '_' + form}")}}})


# ---------------------------------------------------------------- client assets

def horizontal_y(facing):
    return {"north": 0, "east": 90, "south": 180, "west": 270}[facing]


def door_states(gem):
    variants = {}
    for facing in ("east", "north", "south", "west"):
        for half, part in (("lower", "bottom"), ("upper", "top")):
            for hinge in ("left", "right"):
                for opened in (False, True):
                    name = f"{gem}_door_{part}_{hinge}" + ("_open" if opened else "")
                    # Matches vanilla's iron door: closed doors turn a quarter back,
                    # open ones swing about their hinge.
                    if not opened:
                        y = (horizontal_y(facing) + 270) % 360
                    else:
                        y = (horizontal_y(facing) + (0 if hinge == "left" else 180)) % 360
                    entry = {"model": bg(f"block/{name}")}
                    if y:
                        entry["y"] = y
                    variants[f"facing={facing},half={half},hinge={hinge},open={str(opened).lower()}"] = entry
    asset(f"blockstates/{gem}_door.json", {"variants": variants})
    textures = {"bottom": block_texture(gem, "door_bottom"), "top": block_texture(gem, "door_top")}
    for part in ("bottom", "top"):
        for hinge in ("left", "right"):
            for suffix in ("", "_open"):
                model(f"block/{gem}_door_{part}_{hinge}{suffix}", f"minecraft:block/door_{part}_{hinge}{suffix}",
                      **textures)
    model(f"item/{gem}_door", "minecraft:item/generated", layer0=block_texture(gem, "door_bottom"))


def trapdoor_states(gem):
    variants = {}
    for facing in ("east", "north", "south", "west"):
        for half in ("bottom", "top"):
            for opened in (False, True):
                # Matches vanilla's iron trapdoor: only an open trapdoor turns to face.
                entry = {"model": bg(f"block/{gem}_trapdoor_" + ("open" if opened else half))}
                y = horizontal_y(facing) if opened else 0
                if y:
                    entry["y"] = y
                variants[f"facing={facing},half={half},open={str(opened).lower()}"] = entry
    asset(f"blockstates/{gem}_trapdoor.json", {"variants": variants})
    for kind in ("bottom", "top", "open"):
        model(f"block/{gem}_trapdoor_{kind}", f"minecraft:block/template_trapdoor_{kind}",
              texture=block_texture(gem, "trapdoor"))
    model(f"item/{gem}_trapdoor", bg(f"block/{gem}_trapdoor_bottom"))


def face_states(gem, form, model_for):
    """Buttons and levers: attachment face, facing and power."""
    variants = {}
    for face in ("ceiling", "floor", "wall"):
        for facing in ("east", "north", "south", "west"):
            for powered in (False, True):
                entry = {"model": model_for(powered)}
                x = {"floor": 0, "wall": 90, "ceiling": 180}[face]
                y = horizontal_y(facing)
                if face == "ceiling":
                    y = (y + 180) % 360
                if x:
                    entry["x"] = x
                if y:
                    entry["y"] = y
                if face == "wall" and form == "button":
                    entry["uvlock"] = True
                variants[f"face={face},facing={facing},powered={str(powered).lower()}"] = entry
    asset(f"blockstates/{gem}_{form}.json", {"variants": variants})


def lever_model(gem, name, angle):
    base = block_texture(gem, "block")
    faces = lambda texture, uv: {side: {"uv": uv[side], "texture": texture} for side in uv}
    asset(f"models/block/{name}.json", {
        "ambientocclusion": False,
        "textures": {"particle": base, "base": base, "lever": "minecraft:block/lever"},
        "elements": [
            {"from": [5, 0, 4], "to": [11, 3, 12], "faces": faces("#base", {
                "down": [5, 4, 11, 12], "up": [5, 4, 11, 12], "north": [5, 0, 11, 3],
                "south": [5, 0, 11, 3], "west": [4, 0, 12, 3], "east": [4, 0, 12, 3]})},
            {"from": [7, 1, 7], "to": [9, 11, 9], "rotation": {"origin": [8, 1, 8], "axis": "x", "angle": angle},
             "faces": faces("#lever", {
                 "down": [7, 6, 9, 8], "up": [7, 6, 9, 8], "north": [7, 6, 9, 16],
                 "south": [7, 6, 9, 16], "west": [7, 6, 9, 16], "east": [7, 6, 9, 16]})},
        ],
    })


def stair_states(gem):
    variants = {}
    for facing in ("east", "north", "south", "west"):
        for half in ("bottom", "top"):
            for shape in ("inner_left", "inner_right", "outer_left", "outer_right", "straight"):
                kind = "" if shape == "straight" else "_" + shape.split("_")[0]
                y = (horizontal_y(facing) + 270) % 360
                if shape.endswith("left"):
                    y = (y + 270) % 360
                x = 180 if half == "top" else 0
                if half == "top" and shape != "straight":
                    y = (y + 90) % 360
                entry = {"model": bg(f"block/{gem}_stairs{kind}")}
                if x:
                    entry["x"] = x
                if y:
                    entry["y"] = y
                if x or y:
                    entry["uvlock"] = True
                variants[f"facing={facing},half={half},shape={shape}"] = entry
    asset(f"blockstates/{gem}_stairs.json", {"variants": variants})
    texture = block_texture(gem, "block")
    for kind, parent in (("", "stairs"), ("_inner", "inner_stairs"), ("_outer", "outer_stairs")):
        model(f"block/{gem}_stairs{kind}", f"minecraft:block/{parent}", bottom=texture, top=texture, side=texture)
    model(f"item/{gem}_stairs", bg(f"block/{gem}_stairs"))


def wall_states(gem):
    parts = [{"when": {"up": "true"}, "apply": {"model": bg(f"block/{gem}_wall_post")}}]
    for side, y in (("north", 0), ("east", 90), ("south", 180), ("west", 270)):
        for height, suffix in (("low", "side"), ("tall", "side_tall")):
            apply = {"model": bg(f"block/{gem}_wall_{suffix}"), "uvlock": True}
            if y:
                apply["y"] = y
            parts.append({"when": {side: height}, "apply": apply})
    asset(f"blockstates/{gem}_wall.json", {"multipart": parts})
    texture = block_texture(gem, "block")
    for kind in ("post", "side", "side_tall"):
        model(f"block/{gem}_wall_{kind}", f"minecraft:block/template_wall_{kind}", wall=texture)
    model(f"block/{gem}_wall_inventory", "minecraft:block/wall_inventory", wall=texture)
    model(f"item/{gem}_wall", bg(f"block/{gem}_wall_inventory"))


def bars_states(gem):
    sides = ("north", "east", "south", "west")
    closed = {side: "false" for side in sides}
    parts = [{"apply": {"model": bg(f"block/{gem}_bars_post_ends")}},
             {"when": dict(closed), "apply": {"model": bg(f"block/{gem}_bars_post")}}]
    for side, cap, y in (("north", "cap", 0), ("east", "cap", 90), ("south", "cap_alt", 0), ("west", "cap_alt", 90)):
        apply = {"model": bg(f"block/{gem}_bars_{cap}")}
        if y:
            apply["y"] = y
        parts.append({"when": dict(closed, **{side: "true"}), "apply": apply})
    for side, kind, y in (("north", "side", 0), ("east", "side", 90), ("south", "side_alt", 0), ("west", "side_alt", 90)):
        apply = {"model": bg(f"block/{gem}_bars_{kind}")}
        if y:
            apply["y"] = y
        parts.append({"when": {side: "true"}, "apply": apply})
    asset(f"blockstates/{gem}_bars.json", {"multipart": parts})
    texture = block_texture(gem, "bars")
    for kind in ("post_ends", "post", "cap", "cap_alt", "side", "side_alt"):
        model(f"block/{gem}_bars_{kind}", f"minecraft:block/iron_bars_{kind}",
              particle=texture, bars=texture, edge=texture)
    model(f"item/{gem}_bars", "minecraft:item/generated", layer0=texture)


def slab_states(gem):
    texture = block_texture(gem, "block")
    model(f"block/{gem}_slab", "minecraft:block/slab", bottom=texture, top=texture, side=texture)
    model(f"block/{gem}_slab_top", "minecraft:block/slab_top", bottom=texture, top=texture, side=texture)
    states = {"variants": {"type=bottom": {"model": bg(f"block/{gem}_slab")},
                           "type=top": {"model": bg(f"block/{gem}_slab_top")},
                           "type=double": {"model": bg(f"block/{gem}_block")}}}
    asset(f"blockstates/{gem}_slab.json", states)
    # The double slab keeps old full slabs; it is a slab block that starts double.
    asset(f"blockstates/{gem}_double_slab.json", states)
    model(f"item/{gem}_slab", bg(f"block/{gem}_slab"))


def plate_states(gem):
    rotations = {"down": {"x": 90}, "up": {"x": 270}, "north": {}, "south": {"y": 180},
                 "west": {"y": 270}, "east": {"y": 90}}
    asset(f"blockstates/{gem}_plate.json", {"variants": {
        f"facing={facing}": dict({"model": bg(f"block/{gem}_plate")}, **rotation)
        for facing, rotation in rotations.items()}})
    texture = block_texture(gem, "plate")
    model(f"block/{gem}_plate", bg("block/plate"), top=texture, side=texture, bottom=texture)
    model(f"item/{gem}_plate", bg(f"block/{gem}_plate"))


def ore_model(gem):
    base, overlay = "minecraft:block/stone", bg(f"block/ore_overlays/{gem}_ore")
    sides = ("down", "up", "north", "south", "west", "east")
    asset(f"models/block/{gem}_ore.json", {
        "parent": "minecraft:block/block",
        "render_type": "minecraft:cutout_mipped",
        "textures": {"particle": base, "base": base, "overlay": overlay},
        "elements": [
            {"from": [0, 0, 0], "to": [16, 16, 16],
             "faces": {side: {"texture": "#base", "cullface": side} for side in sides}},
            {"from": [0, 0, 0], "to": [16, 16, 16],
             "faces": {side: {"texture": "#overlay", "cullface": side} for side in sides}},
        ],
    })
    simple_state(gem, "ore")
    model(f"item/{gem}_ore", bg(f"block/{gem}_ore"))


def item_models(gem):
    for form in SIMPLE_ITEMS:
        model(f"item/{gem}_{form}", "minecraft:item/generated", layer0=bg(f"item/{gem}_{form}"))
    for form in HANDHELD_ITEMS:
        model(f"item/{gem}_{form}", "minecraft:item/handheld", layer0=bg(f"item/{gem}_{form}"))

    def pulled(form, thresholds, charged=False):
        overrides = [{"predicate": {"pulling": 1}, "model": bg(f"item/{gem}_{form}_pulling_0")}]
        for stage, pull in enumerate(thresholds, start=1):
            overrides.append({"predicate": {"pulling": 1, "pull": pull},
                              "model": bg(f"item/{gem}_{form}_pulling_{stage}")})
        if charged:
            # There is no loaded-crossbow art; a charged crossbow is shown drawn.
            overrides.append({"predicate": {"charged": 1}, "model": bg(f"item/{gem}_{form}_pulling_2")})
        parent = "minecraft:item/crossbow" if form == "crossbow" else "minecraft:item/bow"
        asset(f"models/item/{gem}_{form}.json", {"parent": parent,
              "textures": {"layer0": bg(f"item/{gem}_{form}_standby")}, "overrides": overrides})
        for stage in range(3):
            model(f"item/{gem}_{form}_pulling_{stage}", bg(f"item/{gem}_{form}"),
                  layer0=bg(f"item/{gem}_{form}_pulling_{stage}"))

    pulled("bow", (0.65, 0.9))
    pulled("crossbow", (0.58, 1.0), charged=True)
    asset(f"models/item/{gem}_fishing_rod.json", {"parent": "minecraft:item/handheld_rod",
          "textures": {"layer0": bg(f"item/{gem}_fishing_rod_uncast")},
          "overrides": [{"predicate": {"cast": 1}, "model": bg(f"item/{gem}_fishing_rod_cast")}]})
    model(f"item/{gem}_fishing_rod_cast", "minecraft:item/handheld_rod", layer0=bg(f"item/{gem}_fishing_rod_cast"))
    asset(f"models/item/{gem}_shield.json", {"parent": bg("item/shield"),
          "textures": {"shield": block_texture(gem, "block")},
          "overrides": [{"predicate": {"blocking": 1}, "model": bg(f"item/{gem}_shield_blocking")}]})
    model(f"item/{gem}_shield_blocking", bg("item/shield_blocking"), shield=block_texture(gem, "block"))
    asset(f"models/item/{gem}_bucket.json", {"parent": "neoforge:item/bucket", "loader": "neoforge:fluid_container",
          "fluid": bg(gem), "apply_tint": True})


def block_assets(gem):
    texture = block_texture(gem, "block")
    model(f"block/{gem}_block", "minecraft:block/cube_all", all=texture)
    simple_state(gem, "block")
    model(f"item/{gem}_block", bg(f"block/{gem}_block"))
    ore_model(gem)
    plate_states(gem)
    bars_states(gem)
    door_states(gem)
    trapdoor_states(gem)
    face_states(gem, "button", lambda powered: bg(f"block/{gem}_button" + ("_pressed" if powered else "")))
    for kind, parent in (("", "button"), ("_pressed", "button_pressed"), ("_inventory", "button_inventory")):
        model(f"block/{gem}_button{kind}", f"minecraft:block/{parent}", texture=texture)
    model(f"item/{gem}_button", bg(f"block/{gem}_button_inventory"))
    face_states(gem, "lever", lambda powered: bg(f"block/{gem}_lever" + ("" if powered else "_off")))
    lever_model(gem, f"{gem}_lever", -45)
    lever_model(gem, f"{gem}_lever_off", 45)
    model(f"item/{gem}_lever", bg(f"block/{gem}_lever_off"))
    asset(f"blockstates/{gem}_pressure_plate.json", {"variants": {
        "powered=false": {"model": bg(f"block/{gem}_pressure_plate")},
        "powered=true": {"model": bg(f"block/{gem}_pressure_plate_down")}}})
    model(f"block/{gem}_pressure_plate", "minecraft:block/pressure_plate_up", texture=texture)
    model(f"block/{gem}_pressure_plate_down", "minecraft:block/pressure_plate_down", texture=texture)
    model(f"item/{gem}_pressure_plate", bg(f"block/{gem}_pressure_plate"))
    slab_states(gem)
    stair_states(gem)
    wall_states(gem)
    # The molten gem: its fluid renderer draws it, and the hidden item from old saves.
    asset(f"blockstates/{gem}.json", {"variants": {"": {"model": bg("block/molten_gem")}}})
    model(f"item/{gem}", "minecraft:item/generated", layer0=bg("block/molten_metal_still"))


def client_assets():
    for gem in GEMS:
        block_assets(gem)
        item_models(gem)
    asset("models/block/molten_gem.json", {"textures": {"particle": bg("block/molten_metal_still")}})


# -------------------------------------------------------------------- game data

def item(gem, form):
    return {"item": bg(f"{gem}_{form}")}


def tag(path):
    return {"tag": f"c:{path}"}


def vanilla(name):
    return {"item": f"minecraft:{name}"}


def advancement(gem, name, trigger):
    recipe = bg(f"{gem}_{name}")
    game_data(f"basegems/advancement/recipes/{gem}_{name}.json", {
        "parent": "minecraft:recipes/root",
        "criteria": {
            "has_ingredient": {"trigger": "minecraft:inventory_changed",
                               "conditions": {"items": [{"items": bg(f"{gem}_{trigger}")}]}},
            "has_the_recipe": {"trigger": "minecraft:recipe_unlocked", "conditions": {"recipe": recipe}},
        },
        "requirements": [["has_ingredient", "has_the_recipe"]],
        "rewards": {"recipes": [recipe]},
    })


def recipes(gem):
    gem_tag, nugget, block, rod, gear = (tag(f"{kind}/{gem}") for kind in
                                        ("gems", "nuggets", "storage_blocks", "rods", "gears"))
    stick = tag("rods/wooden")

    def crafting(name, form, count, trigger, body):
        body["result"] = {"id": bg(f"{gem}_{form}"), "count": count}
        # basegems:content_crafting applies the content mode and gem switches.
        game_data(f"basegems/recipe/{gem}_{name}.json", {"type": "basegems:content_crafting", "recipe": body})
        advancement(gem, name, trigger)

    def shaped(form, count, trigger, pattern, key, name=None):
        crafting(name or form, form, count, trigger,
                 {"type": "minecraft:crafting_shaped", "category": "misc", "pattern": pattern.split("|"), "key": key})

    def shapeless(name, form, count, trigger, ingredient):
        crafting(name, form, count, trigger,
                 {"type": "minecraft:crafting_shapeless", "category": "misc", "ingredients": [ingredient]})

    shaped("block", 1, "ingot", "xxx|xxx|xxx", {"x": gem_tag})
    shapeless("unpack_block", "ingot", 9, "block", block)
    shaped("ingot", 1, "nugget", "xxx|xxx|xxx", {"x": nugget})
    shapeless("unpack_gem", "nugget", 9, "ingot", gem_tag)
    shaped("powder", 1, "smallpowder", "xxx|xxx|xxx", {"x": item(gem, "smallpowder")})
    shapeless("unpack_powder", "smallpowder", 9, "powder", item(gem, "powder"))
    for form, count, trigger, pattern, key in (
        ("plate", 3, "ingot", "xxx", {"x": gem_tag}),
        ("slab", 6, "block", "xxx", {"x": block}),
        ("lever", 1, "ingot", "s|x", {"s": stick, "x": gem_tag}),
        ("stairs", 4, "block", "x  |xx |xxx", {"x": block}),
        ("wall", 6, "block", "xxx|xxx", {"x": block}),
        ("bars", 16, "ingot", "xxx|xxx", {"x": gem_tag}),
        ("pressure_plate", 1, "ingot", "xx", {"x": gem_tag}),
        ("door", 3, "ingot", "xx|xx|xx", {"x": gem_tag}),
        ("trapdoor", 1, "ingot", "xx|xx", {"x": gem_tag}),
        ("button", 1, "nugget", "x|x", {"x": nugget}),
        ("rod", 4, "ingot", "x|x", {"x": gem_tag}),
        ("gear", 1, "rod", " x |xrx| x ", {"x": gem_tag, "r": rod}),
        ("crackhammer", 1, "block", "x|s|s", {"x": block, "s": stick}),
        ("axe", 1, "ingot", "xx|xs| s", {"x": gem_tag, "s": stick}),
        ("hoe", 1, "ingot", "xx| s| s", {"x": gem_tag, "s": stick}),
        ("pickaxe", 1, "ingot", "xxx| s | s ", {"x": gem_tag, "s": stick}),
        ("shovel", 1, "ingot", "x|s|s", {"x": gem_tag, "s": stick}),
        ("sword", 1, "ingot", "x|x|s", {"x": gem_tag, "s": stick}),
        ("shears", 1, "ingot", " x|x ", {"x": gem_tag}),
        ("arrow", 4, "nugget", "x|r|f", {"x": nugget, "r": rod, "f": vanilla("feather")}),
        ("bow", 1, "rod", " rs|r s| rs", {"r": rod, "s": vanilla("string")}),
        ("bolt", 4, "rod", "r|r|f", {"r": rod, "f": vanilla("feather")}),
        ("crossbow", 1, "rod", "srr| gr|r s", {"r": rod, "g": gear, "s": vanilla("string")}),
        ("fishing_rod", 1, "rod", "  r| rs|r s", {"r": rod, "s": vanilla("string")}),
        ("shield", 1, "ingot", "xpx|xxx| x ", {"x": gem_tag, "p": {"tag": "minecraft:planks"}}),
    ):
        shaped(form, count, trigger, pattern, key)
    shaped("bars", 4, "rod", "xxx", {"x": rod}, name="bars_from_rods")

    game_data(f"basegems/recipe/{gem}_repair_shield.json", {
        "type": "basegems:plate_repair", "target": bg(f"{gem}_shield"), "plate": tag(f"plates/{gem}")})
    advancement(gem, "repair_shield", "plate")
    for form in ("ore", "powder"):
        game_data(f"basegems/recipe/{gem}_{form}_smelting.json", {
            "type": "basegems:smelting", "category": "misc", "ingredient": item(gem, form),
            "result": {"id": bg(f"{gem}_ingot")}, "experience": 0.45, "cookingtime": 200})
        advancement(gem, f"{form}_smelting", form)
    # Crushing with any crackhammer, shared with every MMDLib mod.
    for form, result, count in (("ore", "powder", 2), ("ingot", "powder", 1), ("block", "powder", 9),
                                ("nugget", "smallpowder", 1)):
        game_data(f"basegems/recipe/{gem}_{form}_crushing.json", {
            "neoforge:conditions": [{"type": "basegems:content_mode", "item": bg(f"{gem}_{result}")}],
            "type": "mmdlib:crushing", "ingredient": item(gem, form),
            "result": {"id": bg(f"{gem}_{result}"), "count": count}})


def tag_file(path, values):
    game_data(path, {"replace": False, "values": values})


def tags():
    item_forms = {"gems": "ingot", "ingots": "ingot", "nuggets": "nugget", "dusts": "powder",
                  "tiny_dusts": "smallpowder", "rods": "rod", "gears": "gear", "ores": "ore",
                  "storage_blocks": "block", "plates": "plate"}
    for category, form in item_forms.items():
        for gem in GEMS:
            entry = [bg(f"{gem}_{form}")]
            tag_file(f"c/tags/item/{category}/{gem}.json", entry)
            if form in ("ore", "block", "plate"):
                tag_file(f"c/tags/block/{category}/{gem}.json", entry)
        members = [f"#c:{category}/{gem}" for gem in GEMS]
        tag_file(f"c/tags/item/{category}.json", members)
        if form in ("ore", "block", "plate"):
            tag_file(f"c/tags/block/{category}.json", members)

    def blocks(*forms):
        return [bg(f"{gem}_{form}") for gem in GEMS for form in forms]

    tag_file("minecraft/tags/block/mineable/pickaxe.json", blocks(*MINED_FORMS))
    for level in ("stone", "iron", "diamond"):
        needed = f"minecraft:needs_{level}_tool"
        tag_file(f"minecraft/tags/block/needs_{level}_tool.json",
                 [bg(f"{gem}_{form}") for gem in GEMS if needed in MATERIALS[gem]["harvest_tags"]
                  for form in MINED_FORMS])
    for vanilla_tag, forms in (("walls", ("wall",)), ("slabs", ("slab", "double_slab")), ("stairs", ("stairs",)),
                               ("doors", ("door",)), ("trapdoors", ("trapdoor",)), ("buttons", ("button",)),
                               ("pressure_plates", ("pressure_plate",))):
        tag_file(f"minecraft/tags/block/{vanilla_tag}.json", blocks(*forms))
    for vanilla_tag, forms in (("walls", ("wall",)), ("slabs", ("slab",)), ("stairs", ("stairs",)),
                               ("doors", ("door",)), ("trapdoors", ("trapdoor",)), ("buttons", ("button",))):
        tag_file(f"minecraft/tags/item/{vanilla_tag}.json", blocks(*forms))
    # Crackhammers mine these at their full speed.
    tag_file("mmdlib/tags/block/crackhammer_crushable.json",
             [f"#c:{kind}/{gem}" for gem in GEMS for kind in ("ores", "storage_blocks")])


def loot_tables():
    def table(gem, form, pool):
        name = bg(f"{gem}_{form}")
        game_data(f"basegems/loot_table/blocks/{gem}_{form}.json", {
            "type": "minecraft:block", "pools": [dict({"rolls": 1.0, "bonus_rolls": 0.0}, **pool)],
            "random_sequence": bg(f"blocks/{gem}_{form}")})

    def self_drop(gem, form, conditions=None, functions=None):
        entry = {"type": "minecraft:item", "name": bg(f"{gem}_{form}")}
        if conditions:
            entry["conditions"] = conditions
        if functions:
            entry["functions"] = functions
        return entry

    survives = [{"condition": "minecraft:survives_explosion"}]
    for gem in GEMS:
        for form in ("block", "plate", "bars", "trapdoor", "button", "lever", "pressure_plate", "stairs", "wall"):
            table(gem, form, {"entries": [self_drop(gem, form)], "conditions": survives})
        table(gem, "door", {"conditions": survives, "entries": [self_drop(gem, "door", conditions=[{
            "condition": "minecraft:block_state_property", "block": bg(f"{gem}_door"),
            "properties": {"half": "lower"}}])]})
        table(gem, "slab", {"entries": [self_drop(gem, "slab", functions=[
            {"function": "minecraft:set_count", "add": False, "count": 2.0, "conditions": [{
                "condition": "minecraft:block_state_property", "block": bg(f"{gem}_slab"),
                "properties": {"type": "double"}}]},
            {"function": "minecraft:explosion_decay"}])]})
        table(gem, "double_slab", {"entries": [self_drop(gem, "slab", functions=[
            {"function": "minecraft:set_count", "add": False, "count": 2.0},
            {"function": "minecraft:explosion_decay"}])]})
        table(gem, "ore", {"entries": [{"type": "minecraft:alternatives", "children": [
            self_drop(gem, "ore", conditions=[{"condition": "minecraft:match_tool", "predicate": {
                "predicates": {"minecraft:enchantments": [
                    {"enchantments": "minecraft:silk_touch", "levels": {"min": 1}}]}}}]),
            self_drop(gem, "ingot", functions=[
                {"function": "minecraft:apply_bonus", "enchantment": "minecraft:fortune",
                 "formula": "minecraft:ore_drops"},
                {"function": "minecraft:explosion_decay"}]),
        ]}]})


def main():
    if OUTPUT.exists():
        shutil.rmtree(OUTPUT)
    client_assets()
    for gem in GEMS:
        recipes(gem)
    tags()
    loot_tables()


if __name__ == "__main__":
    main()
