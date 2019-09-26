FROM tomcat:8.0-jre8

#RUN rm -rf /usr/local/tomcat/webapps/*
COPY tomcat-users.xml /usr/local/tomcat/conf/

#RUN rm -rf /usr/local/tomcat/webapps/ROOT
#RUN ["rm", "-fr", "/usr/local/tomcat/webapps/springThymeleaf-1.0-SNAPSHOT"]
#RUN rm -fr /usr/local/tomcat/webapps/*

RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY target/springThymeleaf-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war


#CMD ["cd /usr/local/tomcat/webapps/"]
#CMD ["rm springThymeleaf-1.0-SNAPSHOT.war ROOT.war"]
CMD ["catalina.sh", "run"]

#COPY ROOT.xml /usr/local/tomcat/conf/Catalina/localhost/
#COPY server.xml /usr/local/tomcat/conf/

#COPY context.xml /usr/local/tomcat/webapps/manager/META-INF/


#https://medium.com/@pra4mesh/deploy-war-in-docker-tomcat-container-b52a3baea448
#docker image build -t your_name/some-app-image ./
#docker container run -it --publish 8087:8080 your_name/some-app-image




#https://forums.docker.com/t/solved-copy-files-into-a-tomcat-image/71728/12