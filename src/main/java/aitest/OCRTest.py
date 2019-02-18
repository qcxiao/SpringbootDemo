# https://www.jianshu.com/p/c52e39053dcf
#!/usr/bin/env python3
# -*- coding: utf-8 -*-

import pytesseract
from PIL import Image

# open image
image = Image.open('/Users/qcxiao/Desktop/2.png')
code = pytesseract.image_to_string(image, lang='chi_sim')
print(code)