package com.sharetechidea.srms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

public class AddNewCourse extends AppCompatActivity {
    TextInputEditText courseName,courseNum,courseCredit;
    Chip courseId;
    String sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);

        Bundle extras = getIntent().getExtras();
        sem= extras.getString("Semester");
        System.out.println(sem);

        ChipGroup chipGroup = findViewById(R.id.chipGroupId);
        chipGroup.setOnCheckedChangeListener((chipgroup, id) -> {

            courseId = findViewById(id);

        });

        courseName = findViewById(R.id.add_course_name);
        courseCredit = findViewById(R.id.add_course_credit);
        courseNum = findViewById(R.id.add_course_num);








      //  String subCode = chipGroup.checkedChipId.getChipText();




    }
    public void createNewCourse(View view) {
        String code = (String) courseId.getText();
        String num = String.valueOf(courseNum.getText());
        String name = String.valueOf(courseName.getText());
        String credits = String.valueOf(courseCredit.getText());

//        if(courseId.getText().toString().matches("")||
//                courseCredit.getText().toString().matches("")||
//                courseName.getText().toString().matches("") ||
//        courseNum.getText().toString().matches("")
//        ){
//            AlertDialog.Builder builder
//                    = new AlertDialog
//                    .Builder(this);
//            builder.setMessage("Some fields are empty!");
//            builder.setCancelable(false);
//            builder
//                    .setPositiveButton(
//                            "Ok",
//                            new DialogInterface
//                                    .OnClickListener() {
//
//                                @Override
//                                public void onClick(DialogInterface dialog,
//                                                    int which)
//                                {
//                                    dialog.cancel();
//                                }
//                            });
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//
//        }


      Course course = new Course(code,num,name,credits);
      FirebaseServices.writeCourseDetails(course,sem);
      showDialog();

    }

    private void showDialog(){
        new MaterialAlertDialogBuilder(this )
                .setTitle("Success")
                .setMessage("The Course is Added Successfully")
                .setCancelable(false)
                .setPositiveButton("Add New Course", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AddNewCourse.this, AddNewCourse.class);

                        startActivity(intent);
                        finish();

                    }
                })
                .setNegativeButton("Home", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AddNewCourse.this,Home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                })
                .show();
    }
}