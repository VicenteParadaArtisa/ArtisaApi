# Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

# Copiar solo pom y descargar dependencias primero (mejor cacheo)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el resto del proyecto
COPY src ./src

# Compilar el proyecto
RUN mvn clean package -DskipTests -B -e

# Etapa final
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Verifica si el JAR realmente se generó
# Usa *.jar si el nombre cambia por algún motivo (recomendado)
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
