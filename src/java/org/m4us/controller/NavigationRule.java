/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.m4us.controller;

import java.util.List;

/**
 *
 * @author arka
 */
public class NavigationRule {
    String ruleName;
    String ruleSuccessJSP;
    String ruleErrorJSP;
    List<String> ruleHandlers;

    public String getRuleErrorJSP() {
        return ruleErrorJSP;
    }

    public void setRuleErrorJSP(String ruleFailureJSP) {
        this.ruleErrorJSP = ruleFailureJSP;
    }

    public List<String> getRuleHandlers() {
        return ruleHandlers;
    }

    public void setRuleHandlers(List<String> ruleHandlers) {
        this.ruleHandlers = ruleHandlers;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleSuccessJSP() {
        return ruleSuccessJSP;
    }

    public void setRuleSuccessJSP(String ruleSuccessJSP) {
        this.ruleSuccessJSP = ruleSuccessJSP;
    }

    public NavigationRule(String ruleName, String ruleSuccessJSP, String ruleErrorJSP, List<String> ruleHandlers) {
        this.ruleName = ruleName;
        this.ruleSuccessJSP = ruleSuccessJSP;
        this.ruleErrorJSP = ruleErrorJSP;
        this.ruleHandlers = ruleHandlers;
    }
    
    
}
