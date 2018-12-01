package com.github.tinosteinort.beanrepository.web.configurator;

import com.github.tinosteinort.beanrepository.web.dispatcher.DispatcherServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;

public class DefaultServerConfigurator implements ServerConfigurator {

    private final DispatcherServlet dispatcherServlet;

    public DefaultServerConfigurator(final DispatcherServlet dispatcherServlet) {
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override public void configure(final Tomcat tomcat) {

        int port = 8877;

        tomcat.setPort(port);

        final Context context = tomcat.addContext("", createTempDir(port));
        tomcat.addServlet("", "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/*", "dispatcher");
    }

    private String createTempDir(final int port) {
        try {
            File tempDir = File.createTempFile("tomcat.", "." + port);
            tempDir.delete();
            tempDir.mkdir();
            tempDir.deleteOnExit();

            return tempDir.getAbsolutePath();
        }
        catch (IOException ex) {
            throw new RuntimeException(
                    "Unable to create tempDir:" + System.getProperty("java.io.tmpdir"), ex);
        }
    }
}
