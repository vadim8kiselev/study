
path = raw_input('Enter the path to file: ')

hash_sum = 0

with open(path) as f:
    while True:
        byte = f.read(1)
        if not byte:
            break
        else:
            hash_sum += ord(byte)

hash_sum %= 2**16

print hash_sum
