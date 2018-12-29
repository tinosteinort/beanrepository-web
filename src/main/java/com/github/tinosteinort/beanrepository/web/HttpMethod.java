package com.github.tinosteinort.beanrepository.web;

public enum HttpMethod {

    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    private final String name;

    HttpMethod(final String name) {
        this.name = name;
    }
}
