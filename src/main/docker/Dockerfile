FROM java:8
EXPOSE 80

RUN mkdir -p /usr/upload/

VOLUME /usr/upload/

ADD run.sh /run.sh

RUN chmod +x /run.sh

ADD school-blog-*.jar /app.jar

ENTRYPOINT sh /run.sh