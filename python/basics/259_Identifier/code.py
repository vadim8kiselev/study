a = raw_input().replace('_', '')
print "YES" if len(a) == 0 or (a.isalnum() and a[0].isalpha()) else "NO"
