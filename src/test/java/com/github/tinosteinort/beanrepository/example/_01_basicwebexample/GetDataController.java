package com.github.tinosteinort.beanrepository.example._01_basicwebexample;

import com.github.tinosteinort.beanrepository.web.HttpMethod;
import com.github.tinosteinort.beanrepository.web.WebController;

public class GetDataController implements WebController {

    @Override public String path() {
        return "/data/";
    }
    @Override public HttpMethod method() {
        return HttpMethod.GET;
    }

    @Override public String invoke(final String pathInfo) {
        return "GetDataController: " + pathInfo;
    }
}
