/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.handlers;

import org.m4us.controller.FlowContext;
import org.m4us.controller.NavigationRule;

/**
 *
 * @author arka
 */
public interface IHandler {
    public void handleRequest(FlowContext flowCtx);
}
