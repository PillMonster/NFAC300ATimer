FROM openjdk:11-jre-slim
# 设置工作目录
WORKDIR /app
# 複製應用程式(.jar)到容器的工作目錄
COPY target/NFAC300ATimer.jar /app/NFAC300ATimer.jar
CMD ["java","-jar","NFAC300ATimer.jar"]

