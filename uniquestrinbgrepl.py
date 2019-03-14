from re import sub
from tkinter import Tk, filedialog
root = Tk()
root.withdraw()
filepath = filedialog.askopenfilename(filetypes = [("Text file", "*.*")])
with open(filepath) as file:
    text = file.read()
    file.close()
i = 0
def iterator(unused = ""):
    global i
    i += 1
    return str(i)
out = sub("\$i\$", iterator, text)
with open(filepath, "w") as file:
    file.write(out)
    file.close()
