# 适用于 yudao-module-system-biz 等子模块
FROM harbor.genchuan.cn/library/eclipse-temurin:17-jdk-jammy

ARG MODULE_NAME=yudao-gateway
ARG JAR_PATH=yudao-gateway
ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms512m -Xmx512m"

COPY ${JAR_PATH}/target/${MODULE_NAME}.jar ${MODULE_NAME}.jar

CMD ["java", "-jar", "${MODULE_NAME}.jar"]