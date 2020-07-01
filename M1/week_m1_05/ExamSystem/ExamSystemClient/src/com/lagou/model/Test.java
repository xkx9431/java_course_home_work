package com.lagou.model;

public class Test  implements  java.io.Serializable {
    private static final long serialVersionUID = -2557696091150136252L;
    private  String testNo;
    private String content;
    private  int score;
    private  String answer;


    public Test(String testNo, String content, String answer,int score) {
        this.testNo = testNo;
        this.content = content;
        this.score = score;
        this.answer = answer;
    }

    public String getTestNo() {
        return testNo;
    }

    public void setTestNo(String testNo) {
        this.testNo = testNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Test{" +
                "testNo=" + testNo +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", answer='" + answer + '\'' +
                '}';
    }
}
