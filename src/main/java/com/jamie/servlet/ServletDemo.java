package com.jamie.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @PackageName: com.jamie.servlet
 * @ClassName: ServletDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 10:22 下午
 */
public class ServletDemo implements Servlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("HELLO");
    }


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }



    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
