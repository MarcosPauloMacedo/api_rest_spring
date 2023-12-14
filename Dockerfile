# Use a imagem base com o JDK para compilar o código
FROM maven:3.8.4-openjdk-11-slim AS builder

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar apenas o arquivo pom.xml primeiro, para aproveitar o cache do Docker
COPY pom.xml .

# Baixar as dependências
RUN mvn dependency:go-offline

# Copiar todo o código fonte
COPY src src

# Compilar o código
RUN mvn package

# Use a imagem base com o JRE para executar o aplicativo
FROM openjdk:11-jre-slim

# Configurar o diretório de trabalho
WORKDIR /app

# Copiar o JAR construído a partir da fase anterior
COPY --from=builder /app/target/*.jar /app/atendimento.jar

# Comando para executar o aplicativo
CMD ["java", "-jar", "atendimento.jar"]
