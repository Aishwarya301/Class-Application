package com.aishwarya.aclass;

public class QuestionInsertValues {
    String subject_name,que_no,question;

    public QuestionInsertValues() {
    }

    public QuestionInsertValues(String subject_name, String que_no, String question) {
        this.subject_name = subject_name;
        this.que_no = que_no;
        this.question = question;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getQue_no() {
        return que_no;
    }

    public void setQue_no(String que_no) {
        this.que_no = que_no;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
