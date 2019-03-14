from PIL import Image
from tkinter import Tk, filedialog
root = Tk()
root.withdraw()
alpha = Image.open(filedialog.askopenfilename(filetypes = [("Alpha Image", " *.png;*.bmp;*.jpg")]))
alpha = alpha.getchannel("A")
inputs = filedialog.askopenfilenames(filetypes = [("Input Images", " *.png;*.bmp;*.jpg")])
for image in inputs:
    im = Image.open(image)
    im = Image.merge("RGBA", (im.getchannel("R"), im.getchannel("G"), im.getchannel("B"), alpha))
    im.save(image)
quit()
