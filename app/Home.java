package com.sharetechidea.srms;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Home extends AppCompatActivity {






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Button editSemesters = findViewById(R.id.editSemesters);
        Button findGPA = findViewById(R.id.gpa);
        Button requiredMarks = findViewById(R.id.requiredMarks);
        Button viewGPAchart = findViewById(R.id.viewGPAchart);
        if(!User.isAdmin){
            editSemesters.setVisibility(View.GONE);

        }


        findGPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Semesters.class);
                intent.putExtra("isFindGPA",true);
                startActivity(intent);

            }
        });


        editSemesters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Semesters.class);
                intent.putExtra("isFindGPA",false);
                startActivity(intent);
            }
        });

        viewGPAchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,GpaReference.class);
                startActivity(intent);
            }
        });

        requiredMarks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,RequiredMarks.class);
                startActivity(intent);

            }
        });




    }


    


}

