FROM maven:3.8.7-eclipse-temurin-19-alpine
WORKDIR /app
COPY ./src /app
COPY ./pom.xml /app
RUN mvn clean package
RUN cp ./target/controleestoque-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
