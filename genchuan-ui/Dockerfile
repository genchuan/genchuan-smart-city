#基础可用的未做任何安全配置
# FROM harbor.genchuan.cn/library/nginx:1.25.4-alpine
FROM nginx:1.28.0-alpine3.21-slim

ENV LANG=zh_CN.UTF-8 \
    TZ=Asia/Shanghai 
RUN mkdir -p /var/www

COPY dist/ /var/www/dist/

COPY kubernetes/nginx/default.conf /etc/nginx/conf.d/default.conf