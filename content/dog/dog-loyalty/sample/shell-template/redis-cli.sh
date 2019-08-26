#!/usr/bin/env bash
echo "start list the redis key and value status:"
redis_server_list="redis host"
function info {
	value=$1
	echo "value name : $value"
	for one in $redis_server_list
	do
		sone=${one//:/ }
		arr=($sone)
		# read -a arr <<< ${one//:/ }
		host=${arr[0]}
		port=${arr[1]}
                # echo "start info host ${host} port ${port}."
		cmd="redis-cli -h ${host} -p ${port} -c"
		dbsize=$($cmd DBSIZE)
		# echo "find the $host:$port dbsize $dbsize"
		data=(`$cmd SCAN 0 MATCH "*${value:-}*count" COUNT $dbsize`)
		keys=${data[@]:1}
		if [ ! -z "$keys" ];then
			for line in ${keys}
			do
			    cc=`$cmd get "${line}"`
			    printf "%-80s : %5d\n" $line $cc
			done
		fi
		# echo "start list the value data:"
		data=(`$cmd SCAN 0 MATCH "*${value:-}*data:list" COUNT $dbsize`)
		keys=${data[@]:1}
		if [ ! -z "$keys" ];then
			for line in ${keys}
			do
			    cc=`$cmd llen "${line}"`
			    printf "%-80s : %5d\n" $line $cc
			done
		fi
		# pre mean the prefix of the key.
		data=(`$cmd SCAN 0 MATCH "pre*${value:-}*" COUNT $dbsize`)
		keys=${data[@]:1}
		if [ ! -z "$keys" ];then
			for line in ${keys}
			do
			    cc=`$cmd llen "${line}"`
			    printf "%-80s : %5d\n" $line $cc
			done
		fi
	done
}
info $@
