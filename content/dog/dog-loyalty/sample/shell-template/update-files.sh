#!/usr/bin/env bash
BASEDIR=`dirname $0`/..
BASEDIR=`(cd "$BASEDIR"; pwd)`
readonly module="$BASEDIR/lib"
for m in ${module};
    do
        for var in $(find ${m} -iname *.jar -o -iname regex*.jar -o -iname *regex*.jar -o -iname pig-*.jar);
        do
            get  ${var##*/} ${module} &
        done
    done
exit
