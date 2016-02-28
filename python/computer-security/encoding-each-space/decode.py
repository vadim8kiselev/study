import codecs
import re

# Source file
path = raw_input('Enter the name of source file: ')

with open('data/' + path, 'r') as f:
    data = f.read()


# Logic
spaces = re.findall(' +', data) 

bit_mask = ''
for space in spaces:
    bit_mask += str(len(space) - 1)


message = ''
for index in xrange(len(bit_mask) / 8):
    message += chr(int(bit_mask[:8], 2))
    bit_mask = bit_mask[8:]

message += '\n'


# Writting 
result_file = raw_input('Enter the name of result file: ')

with open('data/' + result_file, 'w') as f:
    f.write(message)

