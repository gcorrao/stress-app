FROM ubuntu:14.04

#Install java
RUN apt-get update && apt-get install --yes curl
RUN mkdir -p /apps && curl -SL https://s3-us-west-2.amazonaws.com/dario-deploys2/jdk.tar.gz | tar xz -C /apps
ENV JAVA_HOME /apps/jdk
ENV PATH $PATH:$JAVA_HOME/bin

#Install grails
RUN apt-get --yes --force-yes install unzip && curl --silent --location --show-error --verbose "http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/grails-2.4.0.zip" >/tmp/grails.zip && unzip /tmp/grails.zip -d /apps && mv /apps/grails-2.4.0/ /apps/grails
ENV GRAILS_HOME /apps/grails
ENV PATH $PATH:$GRAILS_HOME/bin

#Install grails basic dependencies
WORKDIR /
RUN grails create-app --non-interactive app && cd app && grails refresh-dependencies --non-interactive
ADD . /sources
WORKDIR /sources
CMD grails refresh-dependencies --non-interactive && echo $APP_VERSION >> grails-app/views/index.gsp && grails war --non-interactive /output/ROOT.war

