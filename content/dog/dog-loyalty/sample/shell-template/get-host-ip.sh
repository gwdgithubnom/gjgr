#!/usr/bin/env bash
DIR="$0"
echo $DIR
touch hosts.txt
echo "# sgp host file list" > hosts.txt
i=1
while [ $i -le 4 ]; do
  echo "# sgp"$i" host max 300" >> hosts.txt
  j=1
  while [ $j -le 10 ]; do
	  printf "domain%d-%d\n"  $i $j  >> hosts.txt
      j=$((j + 1))
  done
  j=11
  while [ $j -le 300 ]; do
	  printf "domain%d-%d\n" $i $j >> hosts.txt
      j=$((j + 1))
  done
  i=$((i + 1))
done
echo "# update hosts at `date`" > hosts
cat hosts.txt | while read host
do
	if [ "${host:0:1}" = "#" ];then
		echo $host >> hosts
	elif [ "$host" = "" ];then
		echo $host >> hosts
	else
		ip=$(ping6 $host -c1 | grep from | cut -d '(' -f2 | cut -d ')' -f1)
		if [ "$ip" = "" ];then
			ip=$(ping $host -c1 | grep from | cut -d '(' -f2 | cut -d ')' -f1)
		fi
		if [ "$ip" = "" ];then
			:
		# echo "# "$host >> hosts
		else
			echo $ip" "$host >> hosts
		fi
	fi
done