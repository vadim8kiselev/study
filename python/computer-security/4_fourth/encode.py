import codecs

# Message file
source = raw_input('Enter the name of message file: ')

with codecs.open(source, 'r', 'utf-8') as f:
    message = f.read()[:-1]

# Source file
path = raw_input('Enter the name of source file: ')

with codecs.open(path, 'r', 'utf-8') as f:
    data = f.read()

rus = u'\u0430\u0441\u0435\u043e\u0440\u0445\u0410\u0412\u0421\u0415\u041d\u041a\u041c\u041e\u0420\u0422\u0425' 
eng = 'aceopxABCEHKMOPTX'

# Logic
bit_mask = ''
for byte in message:
    for bit in xrange(8):
        bit_mask += str((ord(byte) >> (7 - bit)) & 1)

index = 0
for char_pos in xrange(len(data)):
    symbol_pos = rus.find(data[char_pos])
    if symbol_pos != -1: 
        if bit_mask[index] == '1':
            data = data[:char_pos] + eng[symbol_pos] + data[char_pos + 1:]
        if index < len(bit_mask) - 1:
            index += 1

# Writting 
result_file = raw_input('Enter the name of result file: ')

with codecs.open(result_file, 'w', 'utf-8') as f:
    f.write(data)
