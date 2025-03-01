package it.interno.ai.model;

/**
 * @author mirco.cennamo on 28/02/2025
 * @project spring-into-ai
 */
public  class Request {
    private String param1;
    private Integer param2;

    // Getters and setters
    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public Integer getParam2() {
        return param2;
    }

    public void setParam2(Integer param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "Request{" +
                "param1='" + param1 + '\'' +
                ", param2=" + param2 +
                '}';
    }
}
