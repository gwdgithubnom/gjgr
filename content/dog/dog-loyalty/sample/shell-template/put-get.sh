#!/usr/bin/env bash
#/bin/bash
if [ ! -n "$1" ]; then
    echo should define the source path
    exit
fi
if [ ! -n "$2" ]; then
    echo did not define the target path, user default home location.
    wget http://domain:port/put-get/$1 -O ~/$1
    exit
fi
wget http://domain:port/put-get/$1 -O $2/$1