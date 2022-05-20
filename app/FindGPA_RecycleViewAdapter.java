package com.sharetechidea.srms;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

public class FindGPA_RecycleViewAdapter extends RecyclerView.Adapter<FindGPA_RecycleViewAdapter.MyViewHolder> {

    Context context;
    String sem;
    View rootView;
    CourseList courses;



    public FindGPA_RecycleViewAdapter(Context context, CourseList courses,String sem) {
        this.context = context;
        this.courses = courses;
        this.sem =sem;


    }

    @NonNull
    @Override
    public FindGPA_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.editable_unit,parent,false);

        return new FindGPA_RecycleViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindGPA_RecycleViewAdapter.MyViewHolder holder, int position) {

        DecimalFormat df = new DecimalFormat("###.###");

        Course course = courses.courses.get(position);
        rootView = ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        TextView findGPA = (TextView) rootView.findViewById(R.id.findGPA);
        Button save = (Button) rootView.findViewById(R.id.save_course_grades);

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                courses.saveGrades(sem);
                Intent intent = new Intent(context,Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);


            }

            });


        System.out.println(course.getCourseName());

        holder.name.setText(course.getCourseName());
        holder.id.setText(course.getCourseId());


        String[] grades = new String[]{"","A+", "A", "A-","B+","B","B-","C+","C","C-","D+","D","E"};


        holder.spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert1 = new AlertDialog.Builder(context);
                alert1.setTitle("Select grade");
                alert1.setSingleChoiceItems(grades,-1, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        holder.grade.setText(grades[which]);
                        courses.findGPA(course,grades[which]);
                        findGPA.setText(df.format(courses.totalGPA));
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

//
//        holder.spinner.setAdapter(
//                new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, grades));
//        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//
//int check =0;
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view,
//                                       int position, long id) {
//
//                if(++check > 1) {
//                Object item = adapterView.getItemAtPosition(position);
//                if (item != null)  {
//                    Toast.makeText(context, item.toString(),
//                            Toast.LENGTH_SHORT).show();
//                    System.out.println("Clicked");
//                    holder.grade.setText(item.toString());

//
//
//
//
//                }}
//
////                Toast.makeText(context, "Selected",
////                        Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                holder.grade.setText("");
//
//                // TODO Auto-generated method stub
//
//            }


        //});
        if(!courses.isInitial){
            holder.grade.setText(courses.idgrade.get(course.getCourseId()));

            courses.findGPA(course,courses.idgrade.get(course.getCourseId()));
            findGPA.setText(df.format(courses.totalGPA));

        }





    }

    @Override
    public int getItemCount() {
        return courses.courses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,grade;
        Button spinner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.findgpa_code);
            name = itemView.findViewById(R.id.findgpa_name);
            spinner = itemView.findViewById(R.id.findgpa_spinner);
            grade = itemView.findViewById(R.id.findgpa_grade);

            System.out.println("MyViewHolder accessing");
        }
    }
}
