package com.techelevator;

import java.math.BigDecimal;

public class HomeWork extends Assignment{
    private int weight = 1;
    private String homeworkNum;

    public HomeWork(double grade, String subject, String num) {
        super(grade, subject);
        this.homeworkNum = num;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        String str = "";
        str += "    Homework Number " + this.homeworkNum + "\n";
        str += "    Homework Grade: " + super.getGrade() + "\n";
        str += "    Homework Subject: " + super.getSubject() + "\n";
        return str;
    }
}
