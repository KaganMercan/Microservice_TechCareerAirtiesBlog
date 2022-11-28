FROM adoptopenjdk/openjdk11
ARG JAR_FILE=Microservice_TechCareerAirtiesBlog-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} blog.jar.jar
ENTRYPOINT [ "java","-jar","/blog.jar.jar" ]
