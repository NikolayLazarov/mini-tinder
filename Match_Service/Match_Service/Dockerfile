FROM openjdk:17
EXPOSE 8080
ADD target/matching-service.jar matching-service.jar
ENTRYPOINT ["java","-jar","/matching-service.jar"]