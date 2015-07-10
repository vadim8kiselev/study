#!/usr/bin/env python
import os
import json
import fnmatch

#Check GS running
os.system("bash ./gscheck.sh")


with open('jsondata.json') as file:
	data = file.read()
	jsondata = json.loads(data)

# check section 
if not 'general' in jsondata or not 'executor' in jsondata:
	print "Invalid key"; exit(0)
###########
###GENERAL#
###########

#default variables
restart_ui = 'no'
skip_directory = ''

#check artifacts: else should fail script
if not 'artifacts' in jsondata['general'] or jsondata['general']['artifacts']=="":
	print "No artifacts"; exit(0)
else:
	artifacts = jsondata['general']['artifacts'].split(', ') 

#check skip directory: defailt=""
if 'skip-directory' in jsondata['general']:
	skip_directory = jsondata['general']['skip-directory'].split(', ')

#find a way to artifacts
paths_to_artifacts = []
for artifact in artifacts:
        for root, dirs, files in os.walk('/home/student/test'): # path to .vagrant
                if not (files in skip_directory):
	                for filename in files:
        	                if fnmatch.fnmatch(filename,artifact):
                	                paths_to_artifacts.append(os.path.join(root, filename))

#check restart_ui: defautl=no
if 'restart-ui' in jsondata['general']:
	restart_ui = jsondata['general']['restart-ui']

#restart WebUi
if restart_ui == 'yes':
	os.system("bash ./gs-webui-restart.sh")

############
###EXECUTOR#
############

#default variables
command = 'deploy '
properties = ''
override_name = ''
sla = ''
tee = ''

#check command: default='deploy'
if 'command' in jsondata['executor']:
	command = jsondata['executor']['command'] + ' '

#check properties: default=""
if 'properties' in jsondata['executor']:
	properties = '"'+jsondata['executor']['properties'] + '" '

#check override-name: default=""
if 'override-name' in jsondata['executor']:
	override_name = jsondata['executor']['override-name'] + ' '

#check sla: default=""
if 'sla' in jsondata['executor']:
	sla = jsondata['executor']['sla'] + ' '

#check tee: default=""
if 'tee' in jsondata['executor']:
	tee = jsondata['executor']['tee']


commands = ['gs.sh '+ command + properties + override_name + sla + artifact + ' ' + tee for artifact in paths_to_artifacts ]

#Start command with os.system()

os.system("bash ./success_deploy.sh")
