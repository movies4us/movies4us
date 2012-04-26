/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.m4us.controller.FlowContext;

/**
 *
 * @author arka
 */
public class LogoutHandler implements IHandler{

    @Override
    public void handleRequest(FlowContext flowCtx) {
        flowCtx.clear();
    }
    
}
