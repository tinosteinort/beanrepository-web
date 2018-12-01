package com.github.tinosteinort.beanrepository.web;

import com.github.tinosteinort.beanrepository.application.event.ApplicationStartedEvent;
import com.github.tinosteinort.beanrepository.application.event.ApplicationStartedListener;
import com.github.tinosteinort.beanrepository.web.configurator.DefaultServerConfiguratorProvider;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class TomcatStarter extends ApplicationStartedListener {

    private final DefaultServerConfiguratorProvider configuratorProvider;

    public TomcatStarter(final DefaultServerConfiguratorProvider configuratorProvider) {
        this.configuratorProvider = configuratorProvider;
    }

    @Override public void onEvent(ApplicationStartedEvent event) {

        Tomcat tomcat = new Tomcat();

        configuratorProvider.getBean().configure(tomcat);

        try {
            tomcat.start();
        }
        catch (LifecycleException ex) {
            throw new RuntimeException(ex);
        }
        tomcat.getServer().await();
    }
}
