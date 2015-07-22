#!/usr/bin/python
"""
Command manager for gigaspace service
"""
import sys
import argparse
sys.path.append('/isodenv/scripts')
from deployer_lib import *

parser = argparse.ArgumentParser()
subparsers = parser.add_subparsers(dest='unit')
all_subparsers = [ 'start', 'stop', 'status', \
                   'start-gs', 'kill-gs', 'health-check', \
                   'start-ui', 'kill-ui', 'redeploy-app']
for subp in all_subparsers:
    subparsers.add_parser(subp)
command = parser.parse_args(sys.argv[1:])

command_list = {'start' : init, 'stop' : stop, 'status' : status, \
                'start-gs' : start_gs, 'kill-gs' : kill_gs, 'health-check' : health_check, \
                'start-ui' : start_ui, 'kill-ui' : kill_ui, 'redeploy-app' : redeploy_app}

if command.unit in all_subparsers:
    command_list[command.unit]()
else:
    print 'wrong command'
