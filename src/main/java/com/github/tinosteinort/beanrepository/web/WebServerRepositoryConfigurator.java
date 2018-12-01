package com.github.tinosteinort.beanrepository.web;

import com.github.tinosteinort.beanrepository.BeanRepository;
import com.github.tinosteinort.beanrepository.application.spi.BeanRepositoryConfiguratorSpi;
import com.github.tinosteinort.beanrepository.web.configurator.DefaultServerConfigurator;
import com.github.tinosteinort.beanrepository.web.configurator.DefaultServerConfiguratorProvider;
import com.github.tinosteinort.beanrepository.web.dispatcher.DispatcherServlet;

public class WebServerRepositoryConfigurator implements BeanRepositoryConfiguratorSpi {

    @Override public void configure(final BeanRepository.BeanRepositoryBuilder builder) {

        builder.singleton(DispatcherServlet.class, DispatcherServlet::new);
        builder.singleton(DefaultServerConfigurator.class, DefaultServerConfigurator::new, DispatcherServlet.class);
        builder.singleton(DefaultServerConfiguratorProvider.class, DefaultServerConfiguratorProvider::new);
        builder.singleton(TomcatStarter.class, TomcatStarter::new, DefaultServerConfiguratorProvider.class);
    }
}
