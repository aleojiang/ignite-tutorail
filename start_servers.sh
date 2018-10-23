#! /bin/bash -e

echo $*

for SERVICE_NAME in $* ; do
    docker-compose up --scale  ignite-server=2 -d ${SERVICE_NAME?}
done
