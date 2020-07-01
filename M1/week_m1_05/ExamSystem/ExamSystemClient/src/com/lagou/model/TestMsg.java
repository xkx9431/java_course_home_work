package com.lagou.model;

public class TestMsg  implements java.io.Serializable {
    private static final long serialVersionUID = -4468675509774698527L;
    private String type;
    private Test test;


    public TestMsg (String type, Test test) {
        this.type = type;
        this.test = test;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {

        this.test = test;
    }

    @Override
    public String toString() {
        return "TestMsg{" +
                "type='" + type + '\'' +
                ", test=" + test +
                '}';
    }
}