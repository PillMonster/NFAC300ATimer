FROM openjdk:11-jre-slim
# 设置工作目录
WORKDIR /app
COPY target/NFAC300ATimer.jar /app/NFAC300ATimer.jar
ENTRYPOINT ["java","-jar","NFAC300ATimer.jar"]
