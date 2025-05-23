# Utiliza la imagen oficial de OpenJDK con Java 17
FROM eclipse-temurin:17-jdk-jammy as builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de construcción y genera el wrapper de Maven si no existe
COPY pom.xml .
COPY src src  

# Copia los archivos del wrapper de Maven
COPY mvnw .
COPY .mvn .mvn


# Construye la aplicación con Maven
RUN ./mvnw clean package -DskipTests

# Segunda etapa: imagen de producción
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Variable de entorno para el perfil de Spring (opcional)
ENV SPRING_PROFILES_ACTIVE=prod

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
