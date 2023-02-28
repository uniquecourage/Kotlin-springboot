# For Java, try this
FROM openjdk:17-jdk
# Refer to Gradlew build -> finalName
ARG JAR_FILE=build/libs/springboot-0.0.1-SNAPSHOT.jar
# cd App/Spring
WORKDIR App/Spring
# cp build/libs/springboot-0.0.1-SNAPSHOT.jar App/Spring/app.jar
COPY ${JAR_FILE} app.jar
# java -jar App/Spring/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8080