FROM openjdk:8-jdk-alpine

# Install Maven
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

COPY . /src
WORKDIR /src
RUN mvn clean install

# Two ways to run
# CMD ["java", "-cp", "target/classes", "com.okinskas.application.Application"]
CMD ["/usr/bin/java", "-jar", "target/hangman-1.0-SNAPSHOT.jar"]