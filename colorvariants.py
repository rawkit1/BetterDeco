from PIL import Image
from tkinter import Tk, filedialog
from os import path, listdir, makedirs
root = Tk()
root.withdraw()
def findsubpathsnamed(parent, name):
    if not path.isdir(parent): return []
    dirs = [path.join(parent, x) for x in listdir(parent) if path.isdir(path.join(parent, x))]
    names = [path.join(parent, x) for x in listdir(parent) if x==name]
    for child in dirs:
        names += findsubpathsnamed(child, name)
    return names
source = filedialog.askopenfilename(filetypes = [("White variant", " *.png;*.bmp;*.jpg")])
imsource = Image.open(source).convert("RGBA")
alpha = imsource.getchannel("A")
imsource = imsource.convert("RGB")
t = findsubpathsnamed(path.join(path.dirname(__file__), "src", "main", "resources"), path.split(path.dirname(source))[-1])
if t:
    t = t[0]
else:
    t = path.dirname(source)
output = filedialog.askdirectory(initialdir=t, title="Output Directory")
naming = input("How should the files be named? %c for color name.\n%i for input filename.\nAlways saves as .png.\nDefaults to %i/%c\n")
if not naming:
    naming = "%i/%c"
colors = {'white': (234, 236, 237),
          'silver': (142, 142, 135),
          'gray': (63, 68, 72),
          'black': (21, 21, 26),
          'red': (161, 39, 35),
          'orange': (241, 118, 20),
          'yellow': (249, 198, 40),
          'lime': (112, 185, 26),
          'green': (85, 110, 28),
          'lightblue': (58, 175, 217),
          'cyan': (21, 138, 145),
          'blue': (53, 57, 157),
          'purple': (122, 42, 173),
          'magenta': (190, 69, 180),
          'pink': (238, 141, 172),
          'brown': (114, 72, 41)}
images = {}
size = imsource.size
imsource = list(imsource.getdata())
imsource = [[z-y for z, y in zip(x, colors["white"])] for x in imsource]
for name, rgb in colors.items():
    imdata = [tuple([z+y for z, y in zip(x, rgb)]) for x in imsource]
    im = Image.new("RGB", size)
    im.putdata(imdata)
    im = im.convert("RGBA")
    im.putalpha(alpha)
    images[name] = im
for color, image in images.items():
    p = path.normpath(path.join(output, naming.replace("%c", color).replace("%i", path.splitext(path.basename(source))[0])) + ".png")
    print(p)
    makedirs(path.dirname(p), exist_ok=True) 
    image.save(p)
