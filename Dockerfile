FROM bellsoft/liberica-openjdk-alpine:21.0.3

WORKDIR /app

COPY target/mcsv-calificaciones.jar /app/mcsv-calificaciones.jar

EXPOSE 8091

ENTRYPOINT ["java", "-jar", "mcsv-calificaciones.jar"]
