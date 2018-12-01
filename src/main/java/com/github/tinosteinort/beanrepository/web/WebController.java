package com.github.tinosteinort.beanrepository.web;

public interface WebController {

    String getPath();

    <T> T invoke(String pathInfo);
}
