# !/usr/bin/python
import json
import os

in_file = ''
import fnmatch
for root, dirs, files in os.walk('C:Users'):
    for name in files:
        if fnmatch.fnmatch(name, 'Bookmarks'):
            in_file = os.path.join(root, name)

out_file = os.path.expanduser('output.html')

with open(in_file, 'r') as f:
    j = json.loads(f.read())

for i in xrange(len(j['roots']['bookmark_bar']['children'])):
    try:
        if 'access_token' in j['roots']['bookmark_bar']['children'][i]['url']:
            command = j['roots']['bookmark_bar']['children'][i]['url']
            token = command[command.find('=')+1: command.find('&', command.find('='))]
    except KeyError:
        pass

with open(out_file, 'w') as out:
    out.write(token)
