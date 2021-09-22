package com.aishwarya.aclass;

public class MCQInsertValues {
    String subject_name,mcq_no,link;

    public MCQInsertValues() {
    }

    public MCQInsertValues(String subject_name, String mcq_no, String link) {
        this.subject_name = subject_name;
        this.mcq_no = mcq_no;
        this.link = link;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getMcq_no() {
        return mcq_no;
    }

    public void setMcq_no(String mcq_no) {
        this.mcq_no = mcq_no;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
