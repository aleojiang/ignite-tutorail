# commonly used docker command line

---
## get the ip address of one container
docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' [container_id]

## prune all docker networks
Even if the containers are down the networks are still persist. 
With no container running [docker network prune] did it. 
Guess restarting the service returns the same result.

docker network prune

## get log of container
docker logs [container_id]

## start up with multi services
docker-compose up --scale  ignite-client=3 -d

## run command line into container
if this does not work
docker exec -it [container_id] bash
then try this
docker exec -ti [container_id] /bin/sh

## list docker networks
docker network ls



 
