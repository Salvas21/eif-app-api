# the first stage of our build will extract the layers
FROM maven:3.8.5-eclipse-temurin-17-alpine as builder
WORKDIR app
COPY . .
RUN mvn package
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} application.jar
#RUN java -Djarmode=layertools -jar application.jar extract
RUN java -Djarmode=layertools -jar target/eif-api-0.0.1-SNAPSHOT.jar extract

# the second stage of our build will copy the extracted layers
FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
WORKDIR app
COPY --from=builder app/dependencies/ ./
RUN true
COPY --from=builder app/spring-boot-loader/ ./
RUN true
COPY --from=builder app/snapshot-dependencies/ ./
RUN true
COPY --from=builder app/application/ ./
RUN true

ARG APP_SPACE_KEY
ENV SPACE_KEY $APP_SPACE_KEY

ARG APP_SPACE_SECRET
ENV SPACE_SECRET $APP_SPACE_SECRET

ARG APP_SPACE_BUCKET
ENV SPACE_BUCKET $APP_SPACE_BUCKET

ARG APP_SPACE_ENDPOINT
ENV SPACE_ENDPOINT $APP_SPACE_ENDPOINT

ARG APP_SPACE_REGION
ENV SPACE_REGION $APP_SPACE_REGION

ARG APP_SPACE_BUCKET
ENV SPACE_BUCKET $APP_SPACE_BUCKET

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]