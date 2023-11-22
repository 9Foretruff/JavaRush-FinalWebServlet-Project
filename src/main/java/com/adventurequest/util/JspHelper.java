package com.adventurequest.util;

public class JspHelper {

    private static final String JSP_FORMAT = "/WEB-INF/jsp/%s.jsp";

    public static String get(String path) {
        return String.format(JSP_FORMAT, path);
    }

}