FROM openjdk:11-jdk
MAINTAINER DISQO Inc

VOLUME /tmp
EXPOSE 8080
ADD build/libs/NotesService-0.0.1-SNAPSHOT.jar NotesService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/NotesService-0.0.1-SNAPSHOT.jar"]
