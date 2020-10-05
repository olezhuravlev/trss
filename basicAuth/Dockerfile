FROM tomcat:8.5.57-jdk11-corretto
LABEL maintainer="olezhuravlev@gmail.com"
ADD /out/artifacts/basicAuth_war_empty/basicAuth_war_empty.war /usr/local/tomcat/webapps
EXPOSE 8080
CMD ["catalina.sh", "run"]
