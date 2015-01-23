FROM mlarq/grails:2.4.4
CMD grails refresh-dependencies --non-interactive && echo $APP_VERSION >> grails-app/views/index.gsp && grails war --non-interactive /output/ROOT.war

