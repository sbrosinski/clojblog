FROM java:openjdk-8-jre
MAINTAINER Stephan Brosinski

ADD target/clojblog-0.1.0-SNAPSHOT-standalone.jar /app/clojblog.jar
ADD resources/config.edn /app/config.edn

EXPOSE 8181

ENV CLOJBLOG_CFG /app/config.edn
CMD ["java", "-jar", "/app/clojblog.jar"]