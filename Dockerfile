# 1. Imagen base con Java
FROM eclipse-temurin:21-jdk-alpine

# 2. Directorio de trabajo
WORKDIR /app

# 3. Copiar el jar
COPY target/taskmanager-0.0.1-SNAPSHOT.jar app.jar

# 4. Exponer puerto
EXPOSE 8080

# 5. Ejecutar app
ENTRYPOINT ["java", "-jar", "app.jar"]
