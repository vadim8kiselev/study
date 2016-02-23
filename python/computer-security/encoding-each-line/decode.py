# Source file
path = raw_input('Enter the name of source file: ')

with open('data/' + path, 'r') as f:
    lines = f.readlines()


# Initialization
byte_array = [0] * len(lines)


# Logic
for index in xrange(len(lines)):
    if lines[index][-2] == ' ':
        byte_array[index / 8] |= (1 << (index % 8))

# Converting
message = ''

for byte in byte_array:
    message += chr(byte)


# First way is writting into file 
result_file = raw_input('Enter the name of result file: ')

with open('data/' + result_file, 'w') as f:
        f.write(message)


# Second way is wriiting into terminal
#print message
