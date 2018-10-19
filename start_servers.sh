#! /bin/bash -e

echo $*

for SERVICE_NAME in $* ; do
    docker-compose up -d ${SERVICE_NAME?}
done
