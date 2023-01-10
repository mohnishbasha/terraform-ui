#!/bin/bash

echo "Waiting for mysql"
until mysql -h"$1" -P"$1" -uroot -p"$MYSQL_ROOT_PASSWORD" &> /dev/null
do
  printf "."
  sleep 1
done

echo -e "\nmysql ready"