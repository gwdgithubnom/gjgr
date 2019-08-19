#!/usr/bin/env bash
#/bin/bash
if [ ! -n "$1" ]; then
    echo should define the source path
    exit
fi
if [ -d "$1" ];   then
    for file in $1/*
    do
    	echo $file
   		curl -XPOST  http://domain:port/path -F "file=@$file"
   	done
elif [ -f "$1" ];  then
	curl -XPOST  http://domain:port/path -F "file=@$1"
fi

exit
if [ ! -n "$2" ]; then
    echo did not define the target path, user default home location.
fi
# echo  curl -XPOST http://10.43.13.68:8090/put-get/ -F "file=@$1"