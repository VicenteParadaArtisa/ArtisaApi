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
# -DskipTests para saltar los tests durante la construcción
RUN ./mvnw clean package -DskipTests

# Segunda etapa: imagen de producción
FROM eclipse-temurin:17-jre-jammy

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR desde la etapa de construcción
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

# Configuración de opciones de JVM para optimizar el uso de memoria en contenedores
# Ajusta el tamaño de la memoria según las necesidades de tu aplicación y el plan de Render.
ENV JAVA_TOOL_OPTIONS="-Xmx512m -Xms256m"

# Instala 'curl' para el HEALTHCHECK. Esto aumentará ligeramente el tamaño de la imagen.
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Comando para ejecutar la aplicación
# Usa el formato 'exec' para una mejor gestión de señales por Docker
ENTRYPOINT ["java", "-jar", "app.jar"]

# Añade un HEALTHCHECK para que Render pueda monitorizar la salud de tu aplicación
# Ajusta el endpoint y el puerto según tu aplicación (normalmente /actuator/health si usas Spring Boot Actuator)
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD curl --fail http://localhost:8080/actuator/health || exit 1
