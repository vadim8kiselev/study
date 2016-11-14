import codecs

# Message file
source = raw_input('Enter the name of message file: ')

with codecs.open(source, 'r') as f:
    message = f.read()


# Source file
path = raw_input('Enter the name of source file: ')

with codecs.open(path, 'r', 'utf-8') as f:
    data = f.read()


# Logic
bit_mask = ''
for symbol in message:
    bit_mask += format(ord(symbol), 'b').zfill(8)

state = 0
for bit in bit_mask:
    position = data.find(' ', state)
    if bit == '1':
        data = data[:position + 1] + ' ' + data[position + 1:]
    state = position + 2


# Writting 
result_file = raw_input('Enter the name of result file: ')

with codecs.open(result_file, 'w', 'utf-8') as f:
    f.write(data)

