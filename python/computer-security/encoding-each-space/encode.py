import codecs

# Message file
source = raw_input('Enter the name of message file: ')

f = codecs.open('data/' + source, 'r', 'utf-8')
message = f.read()[:-1]
f.close()


# Source file
path = raw_input('Enter the name of source file: ')

f = codecs.open('data/' + path, 'r', 'utf-8')
data = f.read()
f.close()



# Logic
bit_mask = ''
for byte in message:
    for bit in xrange(8):
        bit_mask += str((ord(byte) >> (7 - bit)) & 1)

state = 0
for bit in bit_mask:
    position = data.find(' ', state)
    if bit == '1':
        data = data[:position + 1] + ' ' + data[position + 1:]
    state = position + 2


# Writting 
result_file = raw_input('Enter the name of result file: ')

f = codecs.open('data/' + result_file, 'w', 'utf-8')
f.write(data)
f.close()

