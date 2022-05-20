package com.sharetechidea.srms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseList {
        List<Course> courses = new ArrayList<Course>();
        double totalGPA;
        boolean isInitial = true;


        Map<Course,String> grades = new HashMap<>();
        Map<String,String> idgrade = new HashMap<>();


        void findGPA(Course course,String grade){
                grades.put(course,grade);
                totalGPA = 0;
                int courseCredits=0;
                double points;


                for (Map.Entry<Course, String> entry : grades.entrySet()) {
                        System.out.println(entry.getValue());
                        if(entry.getValue().equals("")) points=0;
                        else if(entry.getValue().equals("A+")) points=4;
                        else if(entry.getValue().equals("A")) points=4;
                        else if(entry.getValue().equals("A-")) points=3.7;
                        else if(entry.getValue().equals("B+")) points=3.3;
                        else if(entry.getValue().equals("B")) points=3;
                        else if(entry.getValue().equals("B-")) points=2.7;
                        else if(entry.getValue().equals("C+")) points=2.3;
                        else if(entry.getValue().equals("C")) points=2.0;
                        else if(entry.getValue().equals("C-")) points=1.7;
                        else if(entry.getValue().equals("D+")) points=1.3;
                        else if(entry.getValue().equals("D-")) points=1.0;
                        else points=0;
                        if(points!=0){
                                courseCredits=courseCredits+entry.getKey().getCourseCredits();
                        }
                        points = points*entry.getKey().getCourseCredits();
                        totalGPA=totalGPA+points;
                        System.out.println("Total Gpa is "+ totalGPA);
                        System.out.println("Course Credit is "+ courseCredits);

                }

                totalGPA=totalGPA/courseCredits;
        }

        void saveGrades(String sem){


                for (Map.Entry<Course, String> entry : grades.entrySet()) {



                                idgrade.put(entry.getKey().getCourseId(),entry.getValue());


                }

                        FirebaseServices.saveCourseGrades(idgrade,sem);
                

        }

        public static double calculateRequiredMarks(double totSubMarks, String expectedGrade) {
                //double totSubMarks = quizzes + assignments + projects;
                double minExpectedMarks = 0.0;
                double requiredmarks = 0.0;
                switch (expectedGrade) {
                        case "A+":
                                minExpectedMarks = 85.0;
                                break;
                        case "A":
                                minExpectedMarks = 75.0;
                                break;
                        case "A-":
                                minExpectedMarks = 70.0;
                                break;
                        case "B+":
                                minExpectedMarks = 65.0;
                                break;
                        case "B":
                                minExpectedMarks = 60.0;
                                break;
                        case "B-":
                                minExpectedMarks = 55.0;
                                break;
                        case "C+":
                                minExpectedMarks = 50.0;
                                break;
                        case "C":
                                minExpectedMarks = 45.0;
                                break;
                        case "C-":
                                minExpectedMarks = 40.0;
                                break;
                        case "D+":
                                minExpectedMarks = 35.0;
                                break;
                        case "D":
                                minExpectedMarks = 30.0;
                                break;
                        default:
                                minExpectedMarks = 0.0;
                                break;
                }
                if (minExpectedMarks > totSubMarks) {
                        requiredmarks = minExpectedMarks - totSubMarks;
                } else {
                        requiredmarks = 0.0;
                }
                return requiredmarks;
        }





}
