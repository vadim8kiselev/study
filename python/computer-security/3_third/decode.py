import codecs
import re

path = raw_input('Enter the name of source file: ')

with open(path, 'r') as f:
    data = f.read()

data = data[:data.find('   ')]
spaces = re.findall(' +', data) 

bit_mask = ''
for space in spaces:
    bit_mask += str(len(space) - 1)


message = []
for index in xrange(len(bit_mask) / 8):
    message += [str(int(bit_mask[:8], 2))]
    bit_mask = bit_mask[8:]

result_file = raw_input('Enter the name of result file: ')

with open(result_file, 'wb') as f:
    for byte in message:
        f.write(chr(int(byte)))


