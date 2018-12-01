package com.github.tinosteinort.beanrepository.example._01_basicwebexample;

import com.github.tinosteinort.beanrepository.web.WebController;

public class GetDataController implements WebController {

    @Override public String getPath() {
        return "/data/";
    }

    @Override public String invoke(final String pathInfo) {
        return "GetDataController: " + pathInfo;
    }
}
