package com.github.tinosteinort.beanrepository;

import com.github.tinosteinort.beanrepository.web.configurator.DefaultServerConfigurator;

import java.util.Set;

public abstract class DefaultBeanProvider<T> implements PostConstructible {

    private final Class<T> interfaceClass;
    private final Class<? extends T> defaultImplementationClass;
    private T bean;

    public DefaultBeanProvider(final Class<T> interfaceClass, final Class<? extends T> defaultImplementationClass) {
        this.interfaceClass = interfaceClass;
        this.defaultImplementationClass = defaultImplementationClass;
    }

    public T getBean() {
        return bean;
    }

    @Override public void onPostConstruct(BeanRepository repository) {
        final Set<T> beans = repository.getBeansOfType(interfaceClass);
        if (beans.isEmpty()) {
            throw new RuntimeException(DefaultServerConfigurator.class.getName() + " expected");
        }
        if (beans.size() > 2) {
            throw new RuntimeException("only one server configurator allowed");
        }

        findAndSetBeanOverridingDefault(beans);
    }

    private void findAndSetBeanOverridingDefault(final Set<T> beans) {
        for (T bean : beans) {
            this.bean = bean;
            if (overridesDefault(this.bean)) {
                break;
            }
        }
    }

    private boolean overridesDefault(final T bean) {
        return bean.getClass() != defaultImplementationClass;
    }
}
