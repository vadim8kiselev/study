# Message file
source = raw_input('Enter the name of message file: ')

with open('data/' + source, 'r') as f:
    message = f.read()[:-1]


# Second way is reading message from terminal
#message = raw_input('Enter the message which you wanna encode: ')


# Source file
path = raw_input('Enter the name of source file: ')

with open('data/' + path, 'r') as f:
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

with open('data/' + result_file, 'w') as f:
    for line in lines:
        f.write(line)

