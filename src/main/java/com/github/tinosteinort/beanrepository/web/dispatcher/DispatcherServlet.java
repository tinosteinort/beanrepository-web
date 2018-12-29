package com.github.tinosteinort.beanrepository.web.dispatcher;

import com.github.tinosteinort.beanrepository.BeanRepository;
import com.github.tinosteinort.beanrepository.PostConstructible;
import com.github.tinosteinort.beanrepository.web.HttpMethod;
import com.github.tinosteinort.beanrepository.web.WebController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends HttpServlet implements PostConstructible {

    private final List<WebController> webControllers = new ArrayList<>();
    private final PathMatcher matcher = new PathMatcher();

    /**
     * Do not override service() method on purpose, so the GET/HEAD method handling with the lastModified date
     *  will work.
     */
    @Override protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.GET, req, resp);
    }
    @Override protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.HEAD, req, resp);
    }
    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.POST, req, resp);
    }
    @Override protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.PUT, req, resp);
    }
    @Override protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.DELETE, req, resp);
    }
    @Override protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.OPTIONS, req, resp);
    }
    @Override protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        findAndInvoke(HttpMethod.TRACE, req, resp);
    }

    private void findAndInvoke(final HttpMethod method, final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {

        WebController controller = findController(req, method);
        if (controller != null) {
            invokeController(controller, req, resp);
        }
    }

    private WebController findController(final HttpServletRequest request, final HttpMethod method) {
        for (WebController controller : webControllers) {
            if (controller.method() == method
                    && matcher.pathMatches(controller.path(), request.getPathInfo())) {
                return controller;
            }
        }
        return null;
    }

    private void invokeController(final WebController controller, final HttpServletRequest request,
                                  final HttpServletResponse response) throws IOException {

        response.getOutputStream().print("<html>" + controller.invoke(request.getPathInfo()) + "</html>");
        response.getOutputStream().close();
    }

    @Override public void onPostConstruct(final BeanRepository repository) {
        webControllers.addAll(repository.getBeansOfType(WebController.class));
    }
}
