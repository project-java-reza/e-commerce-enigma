# Base Image dari openJDK
FROM openjdk:11-jre-slim

# Direktory untuk aplikasi/project spring-boot
WORKDIR /app

# Salin file jar springboot dari direktori target kedalam app
COPY target/tokonyareza-0.0.1-SNAPSHOT.jar /app/tokonyareza-0.0.1-SNAPSHOT.jar

# CMD ini akan dijalankan ketika container di start
CMD ["java", "-jar", "/app/tokonyareza-0.0.1-SNAPSHOT.jar"]

# MAINTAINER atau AUTHOR
LABEL authors="fullstack-jay"

