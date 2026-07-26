# =============================================
# ESTÁGIO 1: BUILD (Maven + JDK)
# =============================================
FROM maven:3.9-eclipse-temurin-21 AS builder

# Define o diretório de trabalho
WORKDIR /build

# Copia os arquivos do projeto
COPY pom.xml .
COPY src ./src

# Gera o JAR (pula testes para agilizar)
RUN mvn clean package -DskipTests

# =============================================
# ESTÁGIO 2: RUNTIME (Apenas JRE)
# =============================================
FROM openjdk:21-ea-1-jdk-slim

# Instala utilitários para debug
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR do estágio builder
COPY --from=builder /build/target/legado-soap-veiculos-*.jar app.jar

# Expõe a porta
EXPOSE 8080

# Saúde do container
HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
    CMD curl -f http://localhost:8080/soap/veiculo?wsdl || exit 1

# Comando de entrada com suporte a JAVA_OPTS
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]