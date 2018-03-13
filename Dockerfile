FROM java:openjdk-8-jdk-alpine

MAINTAINER dwaip.chowdhury@corvesta.com

COPY . /tmp
RUN ls -la /tmp

COPY target/*.jar app/blueprint.jar

# Define working directory.
WORKDIR /app
CMD [ "java","-jar","blueprint.jar" ]
