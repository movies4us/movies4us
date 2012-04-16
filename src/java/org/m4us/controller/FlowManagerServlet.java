/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author arka
 */
public class FlowManagerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        try {
//            /*
//             * TODO output your page here. You may use following sample code.
//             */
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet FlowManagerServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FlowManagerServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        } finally {            
//            out.close();
//        }
        
        //System.out.println("current working dir------->"+ (new java.io.File(".")).getAbsolutePath());
        
        if(FlowContext.getServletContext()==null)
            FlowContext.setServletContext(getServletContext());
        Enumeration<String> requestParams = request.getParameterNames();
        FlowContext flowCtx = new FlowContext();
        String actionName = "";
        while(requestParams.hasMoreElements()){
            String param = requestParams.nextElement();
            if(param.startsWith("action")){
                 actionName = param.substring(param.indexOf(".")+1);
                flowCtx.put("currentAction", actionName);
            }else{
                flowCtx.put(param, request.getParameter(param));
            }
        }
        NavigationRule rule = NavigationLoader.getRuleDetails(actionName);
        Boolean flowSuccess = false;
        try {
            flowSuccess = new NavigationController().startFlow(rule, flowCtx);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlowManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FlowManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FlowManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(flowSuccess){
        request.setAttribute("flowContext", flowCtx);
        request.getRequestDispatcher(rule.getRuleSuccessJSP()).forward(request, response);

        }else{
           request.getRequestDispatcher(rule.getRuleErrorJSP()).forward(request, response); 
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
