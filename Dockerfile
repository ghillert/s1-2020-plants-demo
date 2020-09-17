FROM adoptopenjdk:11.0.8_10-jdk-hotspot

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ADD target/plants-1.0.0.SNAPSHOT.jar /app.jar