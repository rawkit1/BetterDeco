from PIL import Image, ImageChops
t = [1, 2, 3, 4, 12, 23, 34, 41, 13, 24, 123, 234, 341, 412, 0, 1234]
t = [str(x) for x in t]
grid = Image.open("grid.png").convert("RGB")
models = Image.open("rug_majestic.png")
tiles = []
winds = ["n","e", "s", "w"]
subwinds = ["nw", "ne", "se", "sw"]
def equals(im1, im2):
    return ImageChops.difference(im1, im2).getbbox() is None
def rotate(wind, clock):
    n = winds if wind in winds else subwinds
    clock = (clock%4)
    clock = clock-4 if clock>0 else clock
    return n[n.index(wind)+clock]
for x in range(0, grid.width, 16):
    for y in range(0, grid.width, 16):
        model = models.crop((x, y, x+16, y+16))
        if all([not equals(model, z) for z in tiles]):
            tiles.append(model)
mod = [[] for x in tiles]
def getindex(model):
    for x in tiles:
        if equals(model, x):
            return tiles.index(x)
for x in range(0, grid.width, 16):
    for y in range(0, grid.height, 16):
        model = models.crop((x, y, x+16, y+16))
        state = {x : 0 for x in winds+subwinds}
        for i in t[x//16]:
            if i == "0": continue
            i = int(i)-1
            state[winds[i]]=1
        for i in t[y//16]:
            if i == "0": continue
            i = int(i)-1
            state[subwinds[i]]=1
        for w in subwinds:
            sw = winds[subwinds.index(w)]
            if state[w] and state[sw] and state[rotate(sw, -1)]:
                state[w]=2
        for w in winds:
            sw = subwinds[winds.index(w)]
            if state[w] and (state[sw]==2 or state[rotate(sw, 1)]==2):
                state[w]=2
        mod[getindex(model)].append(state)
h = [[{x: y for x, y in z.items() if len(x)==1} if not all([y==2 for x, y in z.items() if len(x)==1]) else {x: y for x, y in z.items() if len(x)!=1} for z in g] for g in mod]
h = [list(set([tuple(g.items()) for g in z])) for z in h]
h = sum(h, [])
names = []
jmodels = []
import json
for index in range(len(tiles)):
    x=(index%8)*16
    y=(index//8)*16
    jmodel = {"credit": "Model by Merlijn (aka merren2306/DutchM)",
              "parent": "block/thin_block",
              "textures": {"base": "blocks/wool_colored_black",
                           "pattern": "btdc:blocks/rug_majestic/rug"},
              "elements": [{"from": [0, 0, 0],
                            "to": [16, 1, 16],
                            "faces":{"north": {"uv": [0, 15, 16, 16], "texture": "#base", "cullface": "north"},
                                     "east": {"uv": [0, 15, 16, 16], "texture": "#base", "cullface": "east"},
                                     "south": {"uv": [0, 15, 16, 16], "texture": "#base", "cullface": "south"},
                                     "west": {"uv": [0, 15, 16, 16], "texture": "#base", "cullface": "west"},
                                     "up": {"uv": [x, y, x+16, y+16], "texture": "#pattern"},
                                     "down": {"uv": [x, y, x+16, y+16], "texture": "#pattern", "cullface": "down"}
			    }}]}
    jmodels.append(json.dumps(jmodel))
def stringstate(state):
	sort = sorted(state, key=lambda x: x[0])
	return ",".join(["{}={}".format(x[0], x[1]) for x in state])
for x in h:
	names.append(input(stringstate(x)+"\n"))
