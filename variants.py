from PIL import Image, ImageChops
t = [1, 2, 3, 4, 12, 23, 34, 41, 13, 24, 123, 234, 341, 412, 0, 1234]
t = [str(x) for x in t]
grid = Image.open("grid.png")
models = Image.open("rug_majestic.png")
def getmodel(state):
    pass
def equals(im1, im2):
    return ImageChops.difference(im1, im2).getbbox() is None
for x in range(0, grid.width, 16):
    for y in range(0, grid.width, 16):
        for i in t[x//16]:
            pass
        for i in t[y//16]:
            pass
