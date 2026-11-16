"""Lightweight default-declaration contracts; generated-world tests stay local."""
import json
import re
from pathlib import Path

root = Path(__file__).resolve().parents[1]
source = (root / 'src/main/java/zone/moddev/mc/basegems/worldgen/GemWorldgen.java').read_text()
deposits = re.findall(r'new Deposit\("([a-z]+)", Setting\.([A-Z_]+), ([^)]+)\)', source)
assert len(deposits) == 33
rules = {name: (setting, values) for name, setting, values in deposits}
assert rules['peridot'] == ('PERIDOT', '9, 39, 0.18, 1, 3')
assert rules['sapphire'] == ('CORUNDUM', '7, 33, 0.11, 1, 2')
assert rules['violetsapphire'] == ('CORUNDUM', '6, 25, 0.045, 1, 2')
assert 'GeologyFamily.METAMORPHIC, GeologyFamily.IGNEOUS_VOLCANIC' in source
assert '.hostBlock(vanilla("deepslate")).hostBlock(vanilla("basalt"))' in source
tag = json.loads((root / 'src/main/resources/data/basegems/tags/block/ore_hosts/peridot.json').read_text())
assert {entry['id'] for entry in tag['values']} == {'mineralogy:peridotite', 'mineralogy:gabbro'}
assert all(entry['required'] is False for entry in tag['values'])
assert '.retrogen(false)' in source
print('Gem geology defaults: 33 rules, three corrected hosts, unchanged budgets, optional raw-rock tags passed')
