package com.sharetechidea.srms;

import java.util.ArrayList;
import java.util.List;

public class Course {

        private String courseName,courseId;
        private int courseCredits;
        public Course(){

        }
        public Course(String courseName, String courseId,int courseCredits) {
            this.courseName=courseName;
            this.courseId=courseId;
            this.courseCredits=courseCredits;
        }

        public Course(String courseCode,String courseNum,String courseName,String credits ){
            courseId = courseCode+courseNum;
            courseCredits = Integer.parseInt(credits);
            this.courseName=courseName;

        }




    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getCourseCredits() {
        return courseCredits;
    }

    public void setCourseCredits(int courseCredits) {
        this.courseCredits = courseCredits;
    }







}


