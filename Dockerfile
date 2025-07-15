FROM openjdk:17
WORKDIR /app
COPY target/fruit-shop-1.0.0.jar app.jar
COPY wait-db.sh wait-db.sh
RUN chmod +x wait-db.sh
EXPOSE 8080
ENTRYPOINT ["./wait-db.sh"]
