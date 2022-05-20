package com.sharetechidea.srms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Semester_RecycleViewAdapter extends RecyclerView.Adapter<Semester_RecycleViewAdapter.MyViewHolder> {


    Context context;
    boolean isFindGPA;
    Map<String,String> semesterMap = new HashMap<String,String>(){{
        put("Semester3","sem3");
        put("Semester4","sem4");
        put("Semester5","sem5");
        put("Semester6","sem6");
        put("Semester7","sem7");
        put("Semester8","sem8");
    }};
    List<String> semesterList = Arrays.asList("Semester3","Semester4","Semester5","Semester6","Semester7","Semester8");



    public Semester_RecycleViewAdapter(Context context,boolean isFindGPA) {
        this.context = context;
        this.isFindGPA = isFindGPA;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView semester;
        TextView sem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            semester = itemView.findViewById(R.id.semester_button);
            sem = itemView.findViewById(R.id.sem);
            System.out.println("MyViewHolder accessing");
        }
    }

    @NonNull
    @Override
    public Semester_RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.semester_item,parent,false);

        return new Semester_RecycleViewAdapter.MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull Semester_RecycleViewAdapter.MyViewHolder holder, int position) {
        String semester = semesterList.get(position);
        holder.sem.setText(semester);

        holder.semester.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if(isFindGPA){
                    intent = new Intent(context, FindGPA.class);


                }else{
                    intent = new Intent(context, Courses.class);
                }
                intent.putExtra("Semester",semesterMap.get(semester));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }
}
