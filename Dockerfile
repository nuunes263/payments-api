# syntax=docker/dockerfile:1

# ---------- build ----------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copia so o necessario para resolver dependencias primeiro,
# assim mudanca em src/ nao invalida o cache do Maven.
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw

COPY src/ src/

RUN --mount=type=cache,target=/root/.m2 \
    ./mvnw -B -DskipTests clean package

# ---------- runtime ----------
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

RUN addgroup -S spring && adduser -S spring -G spring

COPY --from=build /app/target/*.jar app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
