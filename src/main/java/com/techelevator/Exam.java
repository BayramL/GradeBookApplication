package com.techelevator;

import java.math.BigDecimal;

public class Exam extends Assignment{
    private int weight = 3;
    private boolean isFinal = false;

    public Exam(double grade, String subject, boolean isFinal) {
        super(grade, subject);
        this.isFinal = isFinal;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public int getWeight() {
        return weight;
    }

    public String toString() {
        String str = "";
        str += "    Exam Grade: " + super.getGrade() + "\n";
        str += "    Exam Subject: " + super.getSubject() + "\n";
        if (this.isFinal) {
            str += "    This exam is a final" + "\n";
        }
        else {
            str += "    This exam is a mid-term" + "\n";
        }
        return str;
    }
}
