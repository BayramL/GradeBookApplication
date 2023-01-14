package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.FileNameMap;
import java.util.*;

public class GradeBookManager {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Student> studentList = new ArrayList<>();

        printMainMenu();

        boolean done = false;
        while (!done) {
            String firstMenuAnswer = input.nextLine();
            switch (firstMenuAnswer) {
                case "1":
                    studentList.addAll(importStudentsFromFile(input));
                    break;
                case "2":
                    studentList.add(addStudentToGradeBook(input));
                    break;
                case "3":
                    addAssignmentToSelectStudent(input, studentList);
                    break;
                case "4":
                    printToOutputFile(studentList);
                    System.out.println("All information is displayed on the output file.");
                    break;
                case "5":
                    for (int i = 0; i < studentList.size(); i++) {
                        System.out.println(i+1 + ": " + studentList.get(i).getFullName() + " "
                                + studentList.get(i).getClassNumber());
                    }
                    break;
                case "9":
                    printMainMenu();
                    break;
                case "0":
                    System.out.println("** TERMINATING PROGRAM **");
                    done = true;
                    break;
                default:
                    System.out.println("INVALID INPUT ENTERED!\n");
            }
        }

    }

    public static void printMainMenu() {
        System.out.println("**** Menu ****");
        System.out.println("1. Import student text file");
        System.out.println("2. Add student to grade book");
        System.out.println("3. Choose student to add assignments");
        System.out.println("4: Output all information on text file");
        System.out.println("5: List of all the students in the grade book");
        System.out.println("9. Reprint menu");
        System.out.println("0. Exit program");
    }

    public static List<Student> importStudentsFromFile(Scanner input) {
        List<Student> list = new ArrayList<>();
        System.out.println("Enter the name of the file that contains the students.");
        String filePath = input.nextLine();
        File file = new File(filePath);
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String data[] = fileReader.nextLine().split("\\|");
                String firstName = data[0];
                String lastName = data[1];
                String classNum = data[2];
                Student newStudent = new Student(firstName, lastName, classNum);
                list.add(newStudent);
                System.out.println("Student named " + firstName + " " + lastName + " has been added to Grade Book");
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File Was Not Found... Back to Main Menu!");
        }
        return list;
    }

    public static Student addStudentToGradeBook(Scanner input) {
        System.out.println("Enter the first name of the student");
        String firstName = input.nextLine();
        System.out.println("Enter the last name ");
        String lastName = input.nextLine();
        System.out.println("Enter the class number");
        String classNum = input.nextLine();
        Student newStudent = new Student(firstName, lastName, classNum);
        System.out.println("Student " + firstName + " " + lastName + " in class " + classNum + " was added to Grade Book");
        return newStudent;
    }

    public static void addAssignmentToSelectStudent(Scanner input, List<Student> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i+1 + ": " + list.get(i).getFirstName() + " " + list.get(i).getLastName()
                    + " in class: " + list.get(i).getClassNumber());
        }
        boolean done;
        try {
            int selectedStudent = Integer.parseInt(input.nextLine()) - 1;
            if (selectedStudent < 0 || selectedStudent > list.size()) {
                throw new IllegalArgumentException();
            }
            done = false;
            while (!done) {
                System.out.println("Enter whether this assignment is an (E)xam, (P)roject or (H)omework." +
                        " Enter 0 to go back to main menu.");
                String assignmentType = input.nextLine();
                switch (assignmentType.toUpperCase()) {
                    case "E":
                        System.out.println("Enter the grade of the assignment 0 - 100: ");
                        try {
                            double grade = Double.parseDouble(input.nextLine());
                            if (grade < 0 || grade > 100)
                                throw new IllegalArgumentException();
                            System.out.println("Enter the subject");
                            String subject = input.nextLine();
                            System.out.println("Is this exam a final. (Y)es or (N)o");
                            String isFinal = input.nextLine().toUpperCase();
                            if (isFinal.equals("Y")) {
                                Exam exam = new Exam(grade, subject, true);
                                list.get(selectedStudent).addAssignment(exam);
                                System.out.println("Exam added to student " + list.get(selectedStudent).getFirstName());
                            }
                            else if (isFinal.equals("N")) {
                                Exam exam = new Exam(grade, subject, false);
                                list.get(selectedStudent).addAssignment(exam);
                                System.out.println("Exam added to student " + list.get(selectedStudent).getFirstName());
                            }
                            else {
                                throw new IllegalArgumentException();
                            }

                        }
                        catch (NumberFormatException e) {
                            System.out.println("This is not a number. Exiting...!");
                            break;
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println("Illegal Input. Exiting...!");
                            break;
                        }

                        break;
                    case "P":
                        System.out.println("Enter the grade of the assignment 0 - 100:");
                        try {
                            double grade = Double.parseDouble(input.nextLine());
                            if (grade < 0 || grade > 100)
                                throw new IllegalArgumentException();
                            System.out.println("Enter the subject");
                            String subject = input.nextLine();
                            System.out.println("Enter the description for the project");
                            String description = input.nextLine();
                            Project project = new Project(grade, subject, description);
                            list.get(selectedStudent).addAssignment(project);
                            System.out.println("Project added to student " + list.get(selectedStudent).getFirstName());
                        }
                        catch (NumberFormatException e) {
                            System.out.println("This is not a number. Exiting...!");
                            break;
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println("Grade can only be between 0 - 100");
                        }
                        break;
                    case "H":
                        System.out.println("Enter the grade of the assignment 0 - 100");
                        try {
                            double grade = Double.parseDouble(input.nextLine());
                            if (grade < 0 || grade > 100)
                                throw new IllegalArgumentException();
                            System.out.println("Enter the subject");
                            String subject = input.nextLine();
                            System.out.println("Enter the number of the homework");
                            String number = input.nextLine();
                            HomeWork hw = new HomeWork(grade, subject, number);
                            list.get(selectedStudent).addAssignment(hw);
                            System.out.println("Homework number " + number + " added to student " + list.get(selectedStudent).getFirstName());
                        }
                        catch (NumberFormatException e) {
                            System.out.println("This is not a number. Exiting...!");
                            break;
                        }
                        catch (IllegalArgumentException e) {
                            System.out.println("Grade must be between 0 - 100");
                        }
                        break;
                    case "0":
                        System.out.println("Back to main menu...");
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid Input. Choices are only E, P or H");
                }
            }
        }
        catch (IllegalArgumentException e) {
            System.err.println("Incorrect input!");
        }

    }

    public static void printToOutputFile(List<Student> studentList) {
        File file = new File("output.txt");
        try (PrintWriter fileWriter = new PrintWriter(file)) {
            for (int i = 0; i < studentList.size(); i++) {
                fileWriter.println(studentList.get(i));
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("File was not found.");
        }
    }
}
