# Usando uma imagem base do Maven que já inclui o JDK 21
FROM maven:3.9.8-amazoncorretto-21 AS builder

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando os arquivos pom.xml e src para o container
COPY pom.xml .
COPY src ./src

# Compilando a aplicação
RUN mvn clean package -DskipTests

# Usando uma imagem base do JDK 21 para rodar a aplicação
FROM openjdk:21-jdk-slim

# Definindo o diretório de trabalho
WORKDIR /app

# Copiando o JAR construído do estágio anterior
COPY --from=builder /app/target/*.jar app.jar

# Expondo a porta 8080 para a aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
