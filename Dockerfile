FROM amazoncorretto:15
WORKDIR /app
COPY target/demo-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]