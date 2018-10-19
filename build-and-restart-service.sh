#!/usr/bin/env bash

for SERVICE_NAME in $* ; do
    ./gradlew :${SERVICE_NAME?}:assemble
    docker-compose build ${SERVICE_NAME?}
    docker-compose up -d ${SERVICE_NAME?}
done

#docker-compose logs -f $*

