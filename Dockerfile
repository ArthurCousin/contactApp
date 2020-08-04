
FROM maven:3.6.3-openjdk-14 AS MAVEN_BUILD
MAINTAINER Arthur Cousin
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests
FROM openjdk:14-jdk
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/contactsapp-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "contactsapp-0.0.1-SNAPSHOT.jar"]