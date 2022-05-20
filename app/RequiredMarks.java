package com.sharetechidea.srms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RequiredMarks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_required_marks);

        TextView expGradeView,assessmentMarksView,necessaryMarks,expectedGrade;
        EditText assessmentMarks;
        Button btn,grade;

        assessmentMarksView = findViewById(R.id.assessmentMarksViewId);
        assessmentMarks = findViewById(R.id.assessmentMarksId);
        necessaryMarks = findViewById(R.id.necessayMarksId);
        btn = findViewById(R.id.btnId);
        expGradeView = findViewById(R.id.expectedGradeViewId);
        grade = findViewById(R.id.expectedGradeSpinner);
        expectedGrade = findViewById(R.id.expectedGradeId);

        expectedGrade.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                necessaryMarks.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        String[] grades = new String[]{"","A+", "A", "A-","B+","B","B-","C+","C","C-","D+","D","E"};
        grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(RequiredMarks.this);
                alert1.setTitle("Select grade");
                alert1.setSingleChoiceItems(grades,-1, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        expectedGrade.setText(grades[which]);
                        dialog.dismiss();
                    }
                });
                alert1.setNeutralButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mAlert1 = alert1.create();
                mAlert1.show();

            }
        });

        assessmentMarks.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                necessaryMarks.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(expectedGrade.getText().toString().matches("") ||
                        assessmentMarks.getText().toString().matches("")) {
                    AlertDialog.Builder builder
                            = new AlertDialog
                            .Builder(RequiredMarks.this);
                    builder.setMessage("Some fields are empty!");
                    builder.setCancelable(false);
                    builder
                            .setPositiveButton(
                                    "Ok",
                                    new DialogInterface
                                            .OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which)
                                        {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                }else {
                    double marks = (Double.parseDouble(assessmentMarks.getText().toString()));
                    String grade = expectedGrade.getText().toString();

                    necessaryMarks.setText(Double.toString(CourseList.calculateRequiredMarks(marks,grade))+" marks needed");
                }
            }
        });
    }


}

