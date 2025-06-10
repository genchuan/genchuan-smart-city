# 适用于 yudao-module-system-biz 等子模块
FROM harbor.genchuan.cn/library/eclipse-temurin:21-jre

ARG MODULE_NAME=yudao-gateway
ARG JAR_PATH=yudao-gateway
ENV TZ=Asia/Shanghai 
ENV JAVA_OPTS="-Xms512m -Xmx512m"
ENV NACOS_SERVER_ADDR=nacos-server:8848
ENV NACOS_NAMESPACE=public
ENV NACOS_USERNAME=
ENV NACOS_PASSWORD=
COPY ${JAR_PATH}/target/${MODULE_NAME}.jar app.jar

# 使用 exec 形式传递所有参数
CMD exec java $JAVA_OPTS \
    -Dspring.cloud.nacos.server-addr=${NACOS_SERVER_ADDR} \
    -Dspring.cloud.nacos.config.namespace=${NACOS_NAMESPACE} \
    -Dspring.cloud.nacos.discovery.namespace=${NACOS_NAMESPACE} \
    -Dspring.cloud.nacos.username=${NACOS_USERNAME} \
    -Dspring.cloud.nacos.password=${NACOS_PASSWORD} \
    -jar app.jar
