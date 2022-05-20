package com.sharetechidea.srms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class GpaReference extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_reference);

        ImageView gpaImage = findViewById(R.id.gpaImageView);
        int imageResource = getResources().getIdentifier("@drawable/gpa", null, this.getPackageName());
        gpaImage.setImageResource(imageResource);
    }
}