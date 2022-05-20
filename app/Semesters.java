package com.sharetechidea.srms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class Semesters extends AppCompatActivity {
    boolean isFindGPA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_semesters);
        Bundle extras = getIntent().getExtras();
        isFindGPA= extras.getBoolean("isFindGPA");



        RecyclerView recyclerView = findViewById(R.id.rvSemesters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Semester_RecycleViewAdapter adapter = new Semester_RecycleViewAdapter(this,isFindGPA);
        recyclerView.setAdapter(adapter);

    }
}