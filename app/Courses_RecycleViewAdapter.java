package com.sharetechidea.srms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Courses_RecycleViewAdapter extends RecyclerView.Adapter<Courses_RecycleViewAdapter.MyViewHolder> {
    Context context;
    CourseList courseList;

    public Courses_RecycleViewAdapter(Context context, CourseList courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item,parent,false);

        return new Courses_RecycleViewAdapter.MyViewHolder(view) ;

    }

    @Override
    public void onBindViewHolder(@NonNull  Courses_RecycleViewAdapter.MyViewHolder holder, int position) {
        Course course = courseList.courses.get(position);
        holder.name.setText(course.getCourseName());
        holder.id.setText(course.getCourseId());
        holder.credits.setText(String.valueOf(course.getCourseCredits()));



    }

    @Override
    public int getItemCount() {
        return courseList.courses.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,credits;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.course_id);
            name = itemView.findViewById(R.id.course_name);
            credits = itemView.findViewById(R.id.course_credit);
            System.out.println("MyViewHolder accessing");
        }
    }
}
