# -------- Build stage --------
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .
RUN mvn clean package -pl autoparts-marketplace-backend -am -DskipTests

# -------- Run stage --------
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /app/autoparts-marketplace-backend/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
