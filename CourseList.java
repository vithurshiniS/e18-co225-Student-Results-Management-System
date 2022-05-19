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
                        else if(entry.getValue().equals("A")) points=3.7;
                        else if(entry.getValue().equals("A-")) points=3.3;
                        else if(entry.getValue().equals("B+")) points=3;
                        else points=3;
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




}
