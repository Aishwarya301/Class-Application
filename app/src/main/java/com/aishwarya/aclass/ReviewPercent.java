package com.aishwarya.aclass;

public class ReviewPercent {
    String subject_name,unit_no;
    int spercent1;
    int spercent2;
    int spercent3;
    int spercent4;
    int spercent5;
    int spercent6;
    int studentCount;

    public ReviewPercent() {
    }

    public ReviewPercent(String subject_name, String unit_no, int spercent1, int spercent2, int spercent3, int spercent4, int spercent5, int spercent6, int studentCount) {
        this.subject_name = subject_name;
        this.unit_no = unit_no;
        this.spercent1 = spercent1;
        this.spercent2 = spercent2;
        this.spercent3 = spercent3;
        this.spercent4 = spercent4;
        this.spercent5 = spercent5;
        this.spercent6 = spercent6;
        this.studentCount = studentCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getUnit_no() {
        return unit_no;
    }

    public void setUnit_no(String unit_no) {
        this.unit_no = unit_no;
    }

    public int getSpercent1() {
        return spercent1;
    }

    public void setSpercent1(int spercent1) {
        this.spercent1 += spercent1;
    }

    public int getSpercent2() {
        return spercent2;
    }

    public void setSpercent2(int spercent2) {
        this.spercent2 += spercent2;
    }

    public int getSpercent3() {
        return spercent3;
    }

    public void setSpercent3(int spercent3) {
        this.spercent3 += spercent3;
    }

    public int getSpercent4() {
        return spercent4;
    }

    public void setSpercent4(int spercent4) {
        this.spercent4 += spercent4;
    }

    public int getSpercent5() {
        return spercent5;
    }

    public void setSpercent5(int spercent5) {
        this.spercent5 += spercent5;
    }

    public int getSpercent6() {
        return spercent6;
    }

    public void setSpercent6(int spercent6) {
        this.spercent6 += spercent6;
    }
}