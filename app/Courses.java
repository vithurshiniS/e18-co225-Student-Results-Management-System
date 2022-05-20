package com.sharetechidea.srms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Courses extends AppCompatActivity {
String sem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_courses);
        Bundle extras = getIntent().getExtras();
        sem= extras.getString("Semester");
        System.out.println(sem);


        CourseList courses = new CourseList();


        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

        RecyclerView recyclerView = findViewById(R.id.rvCourses);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Courses_RecycleViewAdapter adapter = new Courses_RecycleViewAdapter(this,courses);
        recyclerView.setAdapter(adapter);

      FirebaseServices.displayCourses(courses,adapter,progressDialog,sem);

        FloatingActionButton add = findViewById(R.id.add_new_course);




        }

    public void addNewCourse(View view) {
        Intent intent = new Intent(Courses.this,AddNewCourse.class);
        intent.putExtra("Semester",sem);
        startActivity(intent);


    }

    }
