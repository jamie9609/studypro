package com.jamie.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @PackageName: com.jamie.servlet
 * @ClassName: ServletDemo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 11:02 下午
 */
@WebServlet(name = "ServletDemo1", value = "/hello/*")
@Slf4j
public class ServletDemo1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("实现第一个servlet程序");
        log.info("req = {}", req.getRemoteAddr());
        log.info("resp = {}", resp.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log.info("config = {}", config.getServletContext().getClassLoader());
    }
}
