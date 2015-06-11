s = raw_input()
print int(s[:s.find('k')])*1000 if 'k' in s else int(s[:s.find('m')])*1609
