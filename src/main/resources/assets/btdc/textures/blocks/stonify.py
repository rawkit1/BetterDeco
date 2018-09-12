from tkinter import Tk, filedialog
from PIL import Image
root = Tk()
root.withdraw()
colors = (71, 143)
im = Image.open(filedialog.askopenfilename(filetypes = [("Image", " *.png;*.bmp;*.jpg")])).convert("L")
ocolors = im.getextrema()
def stonify(color):
    global colors
    global ocolors
    multiply = (colors[1]-colors[0])/(ocolors[1]-ocolors[0])
    return (color-ocolors[0])*multiply+colors[0]
new = Image.eval(im, stonify)
new.save(filedialog.asksaveasfilename(filetypes = [("Image", "*.png;*.bmp;*.jpg")]))
