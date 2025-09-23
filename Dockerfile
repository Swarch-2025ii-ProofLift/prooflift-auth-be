FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/login-0.0.1-SNAPSHOT.jar 
COPY ${JAR_FILE} login.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/login.jar"]

# para crear el .jar ejecutar en la terminal:
# mvn clean package (va completo)
# mvn clean package -DskipTests (salta tests)
