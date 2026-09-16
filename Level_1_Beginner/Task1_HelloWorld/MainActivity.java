package com.cognifyz.internship.level1;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

/**
 * Task 1: Hello World App
 * Objective: Build a basic Android app with a welcome message.
 */
public class HelloWorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_world);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 1: Hello World");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvWelcome = findViewById(R.id.tv_hello_message);
        tvWelcome.setText("Hello World!\n\nWelcome to Android Development Internship at Cognifyz IT Solutions Pvt. Ltd.\n\nCompleted by Gaurav Kumar");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
