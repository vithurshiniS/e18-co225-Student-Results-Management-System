package com.sharetechidea.srms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

public class FindGPA extends AppCompatActivity {
String sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        CourseList courses = new CourseList();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_gpa);

        Bundle extras = getIntent().getExtras();
        sem= extras.getString("Semester");
        System.out.println(sem);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data....");
        progressDialog.show();

        RecyclerView recyclerView = findViewById(R.id.rvFindGPA);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FindGPA_RecycleViewAdapter adapter = new FindGPA_RecycleViewAdapter(this,courses,sem);
        recyclerView.setAdapter(adapter);

        FirebaseServices.displayCourses(courses,adapter,progressDialog,sem);


    }
}