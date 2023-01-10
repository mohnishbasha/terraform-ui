FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8090
ADD target/*.jar terraform-ui.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java -jar terraform-ui.jar" ]