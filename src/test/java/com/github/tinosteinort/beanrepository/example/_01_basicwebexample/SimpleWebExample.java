package com.github.tinosteinort.beanrepository.example._01_basicwebexample;

import com.github.tinosteinort.beanrepository.BeanRepository;
import com.github.tinosteinort.beanrepository.BeanRepositoryApplication;
import com.github.tinosteinort.beanrepository.BeanRepositoryConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SimpleWebExample implements BeanRepositoryConfigurator {

    private static final Logger LOG = LogManager.getLogger(SimpleWebExample.class);

    public static void main(String[] args) {

        BeanRepositoryApplication.run(new String[0], new SimpleWebExample());
    }

    @Override
    public void configure(final BeanRepository.BeanRepositoryBuilder builder) {
        builder.singleton(GetDataController.class, GetDataController::new);
    }
}
