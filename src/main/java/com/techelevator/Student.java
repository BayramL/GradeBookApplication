package com.techelevator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String firstName;
    private String lastName;
    private String classNumber;
    private List<Assignment> assignments = new ArrayList<>();
    private BigDecimal averageGrade;

    public Student(String firstName, String lastName, String classNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.classNumber = classNumber;
    }

    public Student() {

    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }


    public void setAverageGrade(BigDecimal averageGrade) {
        this.averageGrade = averageGrade;
    }

    public void addAssignment(Assignment assignment) {
        this.assignments.add(assignment);
    }

    public BigDecimal calculateAverageGrade() {
        int count = 0;
        double total = 0;
        for (int i = 0; i < assignments.size(); i++) {
            total += assignments.get(i).getGrade() * assignments.get(i).getWeight();
            count += assignments.get(i).getWeight();
        }
        if (count == 0)
            return new BigDecimal("0");

        BigDecimal average = BigDecimal.valueOf(total/count);
        return average;
    }

    public String toString() {
        String str = "";

        str += this.firstName + " " + this.lastName + " in class " + this.classNumber + ":\n";
        if (new BigDecimal("0").compareTo(this.calculateAverageGrade()) == 0) {
            str += "There is no average because there are no assignments\n";
        }
        else {
            str += "The average grade for this student is: " + this.calculateAverageGrade() + "\n";
        }
        for (int i = 0; i < assignments.size(); i++) {
            str += assignments.get(i) + "\n";
        }

        return str;
    }


}
