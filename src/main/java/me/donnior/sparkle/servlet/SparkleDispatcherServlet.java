package me.donnior.sparkle.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.donnior.sparkle.HTTPMethod;
import me.donnior.sparkle.engine.SparkleEngine;

public class SparkleDispatcherServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    
    private SparkleEngine sparkle;
    
    public SparkleDispatcherServlet() {
        this(new SparkleEngine(new ServletSpecific()));
    }

    public SparkleDispatcherServlet(SparkleEngine engine) {
        this.sparkle = engine;
    }

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
       //if ... doService(req, resp, Method.DELETE);
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.GET);
    }
    
    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.HEAD);
    }
    
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.DELETE);
    }
    
    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.OPTIONS);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.POST);
    }
    
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.PUT);
    }
    
    
    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.sparkle.doService(new ServletWebRequest(req, resp), HTTPMethod.TRACE);
    }
    
    

}
