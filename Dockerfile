# 基础镜像
FROM openjdk:17-oracle


# 拷贝jar包
COPY springboot-project-backend-0.0.1-SNAPSHOT.jar /app.jar



ENTRYPOINT ["java", "-jar", "/app.jar"]