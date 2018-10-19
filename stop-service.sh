#!/usr/bin/env bash

echo $*

for SERVICE_NAME in $* ; do
    docker-compose stop ${SERVICE_NAME?}
done

