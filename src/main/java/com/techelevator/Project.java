package com.techelevator;

import java.math.BigDecimal;

public class Project extends Assignment{
    private int weight = 2;
    private String description;

    public Project(double grade, String subject, String description) {
        super(grade, subject);
        this.description = description;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getWeight() {
        return weight;
    }

    public String toString() {
        String str = "";
        str += "    Project Grade: " + super.getGrade() + "\n";
        str += "    Project Subject: " + super.getSubject() + "\n";
        str += "    Project Description: " + this.getDescription() + "\n";
        return str;
    }
}
