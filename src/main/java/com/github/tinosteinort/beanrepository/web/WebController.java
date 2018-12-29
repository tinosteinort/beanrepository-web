package com.github.tinosteinort.beanrepository.web;

public interface WebController {

    String path();
    HttpMethod method();

    <T> T invoke(String pathInfo);
}
