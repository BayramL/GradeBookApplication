package com.techelevator;

abstract class Assignment implements Weight{
    private double grade;
    private String subject;

    public Assignment(double grade, String subject) {
        this.grade = grade;
        this.subject = subject;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
