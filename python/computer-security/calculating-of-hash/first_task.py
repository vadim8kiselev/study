
path = raw_input('Enter the path to file: ')

hash_sum = 0

with open('data/' + path, 'r') as f:
    while True:
        byte = f.read(1)
        if byte:
           hash_sum += ord(byte)
        else:
            break

hash_sum %= 2**16

print hash_sum
