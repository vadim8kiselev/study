import codecs
import re

source = raw_input('Enter the name of message file: ')

with open(source, 'rb') as f:
    message = bytearray(f.read())

path = raw_input('Enter the name of source file: ')
safelycontainer = raw_input('Enter name of second container: ')

with codecs.open(path, 'r', 'utf-8') as f:
    data = f.read()

data1 = ' '.join(filter(len, data.split(' ')))

with codecs.open(safelycontainer, 'w', 'utf-8') as f:
    f.write(data1)

bit_mask = ''
for symbol in message:
    bit_mask += format(symbol, 'b').zfill(8)

state = 0
for bit in bit_mask:
    position = data1.find(' ', state)
    if bit == '1':
        data1 = data1[:position + 1] + ' ' + data1[position + 1:]
    state = position + 2

position = data1.find(' ', state)
data1 = data1[:position + 1] + '  ' + data1[position + 1:]

result_file = raw_input('Enter the name of result file: ')

with codecs.open(result_file, 'w', 'utf-8') as f:
    f.write(data1)

