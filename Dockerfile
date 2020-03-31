FROM java:8
VOLUME /tmp
COPY target/big1-0.0.1-SNAPSHOT.jar big1.jar
RUN bash -c "touch /big1.jar"
EXPOSE 7003
ENTRYPOINT ["java","-jar","big1.jar"]
