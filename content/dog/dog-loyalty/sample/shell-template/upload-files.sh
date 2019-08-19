#!/usr/bin/env bash
#!/bin/bash
BASEDIR=`dirname $0`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`
readonly module="$BASEDIR/diretory"
for m in ${module};
    do
        for var in $(find ${m} -iname regex1-*regex2*.jar);
        do
            echo ${var##*/} ${var%/*}
        done
    done
for m in ${module};
    do
        for var in $(find ${m} -iname regex1-*regex2*.jar);
        do
           echo ${var}
           put ${var}
        done
    done
# find . -iname regex1-*regex2*.jar | xargs -L 1 basename