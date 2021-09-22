package com.aishwarya.aclass;

public class Anouncement {
    String no,statement;

    public Anouncement() {

    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Anouncement(String no, String statement) {
        this.no = no;
        this.statement = statement;
    }
}
