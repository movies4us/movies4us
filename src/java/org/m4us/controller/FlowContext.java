/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.controller;

import java.util.HashMap;
import javax.servlet.ServletContext;

/**
 *
 * @author arka
 */
public class FlowContext {
    private static ServletContext servletContext;
    private HashMap contextCache = new HashMap();

    public static ServletContext getServletContext() {
        return servletContext;
    }

    public static void setServletContext(ServletContext servletContext) {
        FlowContext.servletContext = servletContext;
    }
    
    public void put(Object key, Object value){
        contextCache.put(key, value);
    }
    
    public Object get(Object key){
        return contextCache.get(key);
    }
    
    public void clear(){
        contextCache.clear();
    }
}
