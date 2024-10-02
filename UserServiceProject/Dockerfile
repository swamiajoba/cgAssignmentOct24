FROM openjdk:11
EXPOSE 9091
EXPOSE 5432
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT [ "java","-jar","/spring-boot-docker.jar" ]