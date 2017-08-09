package com.sports.limitsport.model;

/**
 * Created by liuworkmac on 17/8/9.
 */

public class ReObject {
    private String startRule = "@";
    private String endRule = " ";
    private String text = "";
    private String id;

    public String getStartRule() {
        return startRule;
    }

    public void setStartRule(String startRule) {
        this.startRule = startRule;
    }

    public String getEndRule() {
        return endRule;
    }

    public void setEndRule(String endRule) {
        this.endRule = endRule;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = startRule + text + endRule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
