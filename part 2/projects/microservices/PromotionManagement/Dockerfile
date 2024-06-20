###################
##  Build stage  ##
###################
FROM maven:3.8.3-openjdk-17 AS build

ENV spring.datasource.url jdbc:mysql://promotion-db:3306/promotiondb?autoreconnect=true&allowPublicKeyRetrieval=true&useSSL=false
ENV spring.datasource.username admin
ENV spring.datasource.password admin2223
ENV logging.level.org.springframework.web INFO

COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app

RUN mvn clean package -DskipTests

#####################
##  Package stage  ##
#####################
FROM openjdk:17

ENV ARTIFACT_NAME="PromotionManagement-0.0.1-SNAPSHOT.jar"
ARG JAR_FILE=target/$ARTIFACT_NAME
# ADD ${JAR_FILE} PromotionManagement.jar
COPY  --from=build /home/app/target/$ARTIFACT_NAME .

EXPOSE 8084
ENTRYPOINT java -jar $ARTIFACT_NAME