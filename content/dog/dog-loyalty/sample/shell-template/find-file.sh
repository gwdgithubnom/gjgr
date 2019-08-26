#!/bin/bash
BASEDIR=`dirname $0`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`
readonly module="$BASEDIR/config"
for m in ${module};
    do
        for var in $(find ${m} -type f -regex ".*\.xml");
        do
            get  ${var##*/} $(dirname ${var}) &
        done
    done
exit