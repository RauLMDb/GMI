FROM maven:3.6.3-jdk-11

EXPOSE 8080

COPY . .

COPY settings.xml /root/.m2/settings.xml

CMD ["mvn","spring-boot:run"]
