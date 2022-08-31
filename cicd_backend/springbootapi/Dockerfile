#
# Build stage
#
FROM maven:3.8.5-openjdk-17-slim AS build
COPY src /home/app/src
RUN rm -rf /home/app/src/main/resources/application.properties
COPY ./applicationProd.properties /home/app/src/main/resources/application.properties
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:17-slim
COPY --from=build /home/app/target/springbootapi-1.0.0.jar /usr/local/lib/springbootapi.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/springbootapi.jar"]