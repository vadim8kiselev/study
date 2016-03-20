import codecs

# Source file
path = raw_input('Enter the name of source file: ')

with codecs.open(path, 'r', 'utf-8') as f:
    data = f.read()

rus = u'\u0430\u0441\u0435\u043e\u0440\u0445\u0410\u0412\u0421\u0415\u041d\u041a\u041c\u041e\u0420\u0422\u0425' 
eng = 'aceopxABCEHKMOPTX'

# Logic
bit_mask = ''
for char in data:
    if char in rus:
        bit_mask += '0'
    elif char in eng:
        bit_mask += '1'

message = ''
for index in xrange(len(bit_mask) / 8):
    if int(bit_mask[:8], 2) != 0:
        message += chr(int(bit_mask[:8], 2))
        bit_mask = bit_mask[8:]

# Writting 
result_file = raw_input('Enter the name of result file: ')

with codecs.open(result_file, 'w', 'utf-8') as f:
    f.write(message)
