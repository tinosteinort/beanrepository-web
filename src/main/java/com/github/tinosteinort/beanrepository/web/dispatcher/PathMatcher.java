package com.github.tinosteinort.beanrepository.web.dispatcher;

import java.util.Optional;

public class PathMatcher {

    boolean pathMatches(final String controllerPath, final String incomingPath) {

        final String[] incoming = Optional.of(incomingPath)
                .map(this::normalise)
                .map(this::asArray)
                .get();

        final String[] controller = Optional.of(controllerPath)
                .map(this::normalise)
                .map(this::asArray)
                .get();

        // TODO

        return incomingPath.startsWith(controllerPath);
    }

    String normalise(final String path) {
        if (path == null ||path.isEmpty()) {
            return "";
        }

        int firstIndex = 0;
        if (path.startsWith("/")) {
            firstIndex = 1;
        }

        int lastIndex = path.length();
        if (path.endsWith("/") && path.length() > 1) {
            lastIndex = path.length() - 1;
        }
        return path.substring(firstIndex, lastIndex);
    }

    String[] asArray(final String path) {
        if (path == null || path.isEmpty() || path.trim().isEmpty()) {
            return new String[0];
        }
        return path.trim().split("/");
    }
}
