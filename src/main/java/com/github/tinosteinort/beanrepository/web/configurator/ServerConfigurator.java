package com.github.tinosteinort.beanrepository.web.configurator;

import org.apache.catalina.startup.Tomcat;

public interface ServerConfigurator {

    void configure(Tomcat tomcat);
}
