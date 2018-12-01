package com.github.tinosteinort.beanrepository.web.dispatcher;

import com.github.tinosteinort.beanrepository.BeanRepository;
import com.github.tinosteinort.beanrepository.PostConstructible;
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

    @Override protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        WebController controller = findController(req);
        if (controller != null) {
            invokeController(controller, req, resp);
        }
    }

    private WebController findController(final HttpServletRequest request) {
        for (WebController controller : webControllers) {
            if (matcher.pathMatches(controller.getPath(), request.getPathInfo())) {
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
