# Imagen
FROM eclipse-temurin:25-jdk

# Directorio de trabajo
WORKDIR /app

# Copiar proyecto
COPY . .

# Dar permisos a gradlew
RUN chmod +x gradlew
RUN sed -i 's/\r$//' gradlew

# Compilar el proyecto
RUN ./gradlew build -x test

# Exponer puerto
EXPOSE 6001

# Ejecutar
ENTRYPOINT ["java", "-jar", "build/libs/fairticket-0.0.1-SNAPSHOT.jar"]