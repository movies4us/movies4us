/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.controller;

import org.m4us.handlers.IHandler;

/**
 *
 * @author arka
 */
public class NavigationController {
    public Boolean startFlow(NavigationRule rule,FlowContext flowCtx) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
        flowCtx.put("SUCCESS", false);
        for(String handlerClass:rule.getRuleHandlers()){
            Class c = Class.forName(handlerClass);
            IHandler handler = (IHandler)c.newInstance();
            handler.handleRequest(flowCtx);
        }
        return (Boolean)flowCtx.get("SUCCESS");
    }
}
