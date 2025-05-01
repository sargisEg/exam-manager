FROM amazoncorretto:17-alpine

COPY ["build/libs/exam-manager-0.0.1-SNAPSHOT.jar", "/exam.jar"]
ENTRYPOINT ["java","-jar","/exam.jar"]