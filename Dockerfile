FROM openjdk:8
EXPOSE 8084
ADD target/plan.jar plan.jar
ENTRYPOINT ["java", "-jar", "plan.jar"]
