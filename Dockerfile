
# para crear el .jar ejecutar en la terminal:
# mvn clean package (va completo)
# mvn clean package -DskipTests (salta tests)

# Dockerfile para el backend (Spring Boot)
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar archivos de Maven
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Descargar dependencias
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN ./mvnw clean package -DskipTests

# Exponer puerto
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/login-0.0.1-SNAPSHOT.jar"]