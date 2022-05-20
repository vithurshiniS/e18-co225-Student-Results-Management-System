package com.sharetechidea.srms;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FirebaseServices {

    public static void displayCourses(CourseList courses, Courses_RecycleViewAdapter adapter, ProgressDialog progressDialog, String sem){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("departments").document("co").collection(sem)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Success");


if(!task.getResult().isEmpty()){



                            for (QueryDocumentSnapshot doc : task.getResult()) {

                                if(!doc.exists()){
                                    System.out.println("error");

                                }
                                System.out.println(doc);
                                Course course = new Course();
                                course.setCourseId(doc.getId());

                                if (doc.get("name") != null) {
                                    course.setCourseName(doc.getString("name"));
                                }

                                if (doc.get("credits") != null) {
                                    course.setCourseCredits(Math.toIntExact(doc.getLong("credits")));
                                }

                                courses.courses.add(course);}
                                adapter.notifyDataSetChanged();}
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }




                            System.out.println(courses.courses);
                        } else {
                            System.out.println("error appeared");
                        }
                    }

                }
                );}



    public static void displayCourses(CourseList courses, FindGPA_RecycleViewAdapter adapter, ProgressDialog progressDialog,String sem){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final DocumentReference docRef = db.collection("students").document(User.name).collection("marks").document(sem);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {

                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    System.out.println("beast");
                     Map<String, Object> map =  snapshot.getData();
                    System.out.println(map.values());

                    for (Map.Entry<String, Object> entry : map.entrySet()) {

                        System.out.println(entry.getValue());
                            courses.idgrade.put(entry.getKey(), entry.getValue().toString());
                    }

                    System.out.println(courses.idgrade);
                    db.collection("departments").document("co").collection(sem)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                           if (task.isSuccessful()) {
                                                               System.out.println("Success");


                                                               if(!task.getResult().isEmpty()){



                                                                   for (QueryDocumentSnapshot doc : task.getResult()) {

                                                                       if(!doc.exists()){
                                                                           System.out.println("error");

                                                                       }

                                                                       System.out.println(doc);
                                                                       Course course = new Course();
                                                                       course.setCourseId(doc.getId());
                                                                       courses.grades.put(course,"");

                                                                       if (doc.get("name") != null) {
                                                                           course.setCourseName(doc.getString("name"));

                                                                       }

                                                                       if (doc.get("credits") != null) {
                                                                           course.setCourseCredits(Math.toIntExact(doc.getLong("credits")));
                                                                       }

                                                                       courses.courses.add(course);
                                                                   }
                                                                   courses.isInitial=false;

                                                                   adapter.notifyDataSetChanged();}
                                                               if (progressDialog.isShowing()) {
                                                                   progressDialog.dismiss();
                                                               }

                                                               System.out.println(courses.courses);
                                                           } else {
                                                               System.out.println("error appeared");
                                                           }
                                                       }

                                                   }
                            );
                } else {
                    db.collection("departments").document("co").collection(sem)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                           if (task.isSuccessful()) {
                                                               System.out.println("Success");
                                                               if(!task.getResult().isEmpty()){

                                                                   for (QueryDocumentSnapshot doc : task.getResult()) {

                                                                       if(!doc.exists()){
                                                                           System.out.println("error");

                                                                       }




                                                                       System.out.println(doc);
                                                                       Course course = new Course();
                                                                       course.setCourseId(doc.getId());
                                                                       courses.grades.put(course,"");

                                                                       if (doc.get("name") != null) {
                                                                           course.setCourseName(doc.getString("name"));
                                                                       }

                                                                       if (doc.get("credits") != null) {
                                                                           course.setCourseCredits(Math.toIntExact(doc.getLong("credits")));
                                                                       }

                                                                       courses.courses.add(course);}
                                                                   adapter.notifyDataSetChanged();}
                                                               if (progressDialog.isShowing()) {
                                                                   progressDialog.dismiss();
                                                               }




                                                               System.out.println(courses.courses);
                                                           } else {
                                                               System.out.println("error appeared");
                                                           }
                                                       }

                                                   }
                            );

                }
            }
        });
}
    //add course data to firebase
    public static void writeCourseDetails(Course course,String sem){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("name", course.getCourseName());
        user.put("credits", course.getCourseCredits());

        db.collection("departments").document("co").collection(sem)
                .document(course.getCourseId()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("success...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("failure...");
                    }
                });
    }


    public static void saveCourseGrades(Map<String,String> grades,String sem){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("students").document(User.name).collection("marks")
                .document(sem).set(grades)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        System.out.println("success...");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("failure...");
                    }
                });

    }


    public static void getUserAdmin(String name){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("admins").document(name);
        docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {


                if (snapshot != null && snapshot.exists()) {
                   User.isAdmin= true;
                } else {
                    User.isAdmin= false;
                }
            }

        });



    }
}

