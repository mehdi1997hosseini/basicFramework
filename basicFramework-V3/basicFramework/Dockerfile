FROM openjdk:17-slim
LABEL montainer="mehdi hosseini "

WORKDIR /app

# for using when build time
ARG JAR_FILE=target/*.jar

# jar deployed location file address - name container in docker
COPY ${JAR_FILE} spring-boot-basic-framework-v3-1.0.jar

# app start in port - when using start app to other port
#EXPOSE 8080

# profile variable config
ENV PROFILE=dev
# database variable config
ENV DB_NET=${DB_NET}
ENV DB_PORT=${DB_PORT}
ENV DB_NAME=${DB_NAME}
ENV DB_USERNAME=${USERNAME}
ENV DB_PASSWORD=${PASSWORD}

VOLUME /log/springBootV3

# connect to run container start
ENTRYPOINT ["java" , "-jar" , "spring-boot-basic-framework-v3-1.0.jar"]
# other argoman for concat to ENTRYPOINT
#CMD = ["" , ""]

# docker build --tag=spring-boot-basic-framework-v3:latest .
# --tag or -t

# docker run -d -it -e USERNAME={database-username} -e PASSWORD={database-password} -p {port-in-the-localSystem}:{port-in-docker-container} spring-boot-basic-framework-v3:latest
# docker run -d -it -e USERNAME=sa -e PASSWORD=sa -p 8084:8080 spring-boot-basic-framework-v3:latest
# docker run -d -it -e USERNAME=sa -e PASSWORD=sa -e PROFILE=prod  -p 8084:8080 spring-boot-basic-framework-v3:latest
