FROM openjdk:8
MAINTAINER Vinod <vinod@vinod.co>
ADD ./basic-rest.jar /app.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=8080", "/app.jar"]
EXPOSE 8080