# 使用官方的 OpenJDK 11 映像作為基礎映像
FROM openjdk:11-jre-slim

VOLUME /tmp

# 複製 Spring Boot 應用程序的 JAR 文件到容器中
# 這是你的 Spring Boot 應用程序構建的目錄中的 JAR 文件
COPY target/NFAC300ATimer.jar app.jar

# 定義容器啟動命令，使用 "java -jar" 執行應用程序
CMD ["java", "-jar", "/app.jar"]