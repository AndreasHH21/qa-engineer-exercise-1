FROM openjdk:8
COPY ./src/main/java/com.statista.exercises/CompanySeleniumApplication/ /tmp
WORKDIR /tmp
ENTRYPOINT ["java","CompanySeleniumApplication"]