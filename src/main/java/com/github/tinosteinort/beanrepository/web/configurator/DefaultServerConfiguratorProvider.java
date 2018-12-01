package com.github.tinosteinort.beanrepository.web.configurator;

import com.github.tinosteinort.beanrepository.DefaultBeanProvider;

public class DefaultServerConfiguratorProvider extends DefaultBeanProvider<ServerConfigurator> {

    public DefaultServerConfiguratorProvider() {
        super(ServerConfigurator.class, DefaultServerConfigurator.class);
    }
}
