const {test}=require('node:test');
const assert=require('node:assert/strict');
const fs=require('node:fs'),path=require('node:path');
const templates=JSON.parse(fs.readFileSync(path.join(__dirname,'../gradle/model-templates.json'),'utf8'));
test('bar parts reference an existing texture for every gem',()=>{
 const assets=path.join(__dirname,'../src/main/resources/assets/basegems');
 for(const file of fs.readdirSync(path.join(assets,'blockstates')).filter(n=>n.endsWith('_ore.json'))){
  const gem=file.slice(0,-9);
  for(const part of ['post','post_ends','cap','cap_alt','side','side_alt'])
   for(const texture of Object.values(templates['models/block/{gem}_bars_'+part+'.json'].textures)){
    const actual=texture.replace('{gem}',gem).replace('basegems:','');
    assert(fs.existsSync(path.join(assets,'textures',actual+'.png')),gem+' '+part+' '+actual);
   }
 }
});
test('bars use vanilla post ends, isolated posts and single-connection caps',()=>{
 const json=templates['blockstates/{gem}_bars.json'];
 for(let mask=0;mask<16;mask++){
  const directions=['north','east','south','west'];
  const state=Object.fromEntries(directions.map((d,i)=>[d,String(Boolean(mask&(1<<i)))]));
  const parts=json.multipart.filter(p=>!p.when||Object.entries(p.when).every(([k,v])=>String(v)===state[k])).map(p=>p.apply.model);
  const connections=directions.filter(d=>state[d]==='true').length;
  assert.equal(parts.filter(n=>n.endsWith('_post_ends')).length,1,'post ends '+mask);
  assert.equal(parts.filter(n=>n.endsWith('_post')).length,connections===0?1:0,'centre '+mask);
  assert.equal(parts.filter(n=>/_cap(_alt)?$/.test(n)).length,connections===1?1:0,'caps '+mask);
  assert.equal(parts.filter(n=>/_side(_alt)?$/.test(n)).length,connections,'sides '+mask);
 }
 assert.equal(templates['models/block/{gem}_bars_post_ends.json'].parent,'minecraft:block/iron_bars_post_ends');
});
