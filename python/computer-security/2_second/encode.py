import codecs

# Message file
path = raw_input('Enter the name of message file: ')

with codecs.open(path , 'r', 'utf-8') as f:
    message = f.read()[:-1]


# Source file
path = raw_input('Enter the name of source file: ')

with open(path, 'r') as f:
    lines = f.readlines()


# Logic
for index in xrange(len(message)):
    byte = ord(message[index])
    for bit_position in xrange(8):
        if (byte >> (7 - bit_position)) & 1 == 1:
            position = index * 8 + bit_position
            lines[position] = lines[position][:-1] + ' \n'


# Writting 
result_file = raw_input('Enter the name of result file: ')

with open(result_file, 'w') as f:
    for line in lines:
        f.write(line)

