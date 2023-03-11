# For Java, try this
FROM openjdk:17-jdk-slim
EXPOSE 8080
# Refer to Gradlew build -> finalName
ARG JAR_FILE=build/libs/springboot-0.0.1-SNAPSHOT.jar
# cd App/Spring
WORKDIR App/Spring
# cp build/libs/springboot-0.0.1-SNAPSHOT.jar App/Spring/app.jar
COPY ${JAR_FILE} app.jar

RUN apt-get update && \
    apt-get install net-tools && \
    apt-get install iputils-ping -y


# java -jar App/Spring/app.jar
#ENTRYPOINT ["java","-jar","app.jar"]
CMD java -jar app.jar