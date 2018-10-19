#! /bin/bash -e

. ./set-env.sh

./gradlew assemble

docker-compose build

. ./set-env.sh

## with removing  orphans after changing service name
docker-compose down -v --remove-orphans

docker network prune

docker-compose up --scale  ignite-server=2 --scale  ignite-client=3 -d
