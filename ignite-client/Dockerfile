FROM openjdk:8u171-jre-alpine
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar ignite-client-1.0-SNAPSHOT.jar
COPY build/libs/ignite-client-1.0-SNAPSHOT.jar .
