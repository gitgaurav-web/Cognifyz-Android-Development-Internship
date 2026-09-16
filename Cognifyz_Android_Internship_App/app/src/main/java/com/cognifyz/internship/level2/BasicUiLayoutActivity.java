package com.cognifyz.internship.level2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

/**
 * Task 4: Basic UI Layout
 * Objective: Build an app with multiple activities and a basic UI layout in XML.
 */
public class BasicUiLayoutActivity extends AppCompatActivity {

    private EditText etName, etEmail, etDomain;
    private Button btnOpenProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_ui_layout);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 4: UI Layout");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etName = findViewById(R.id.et_intern_name);
        etEmail = findViewById(R.id.et_intern_email);
        etDomain = findViewById(R.id.et_intern_domain);
        btnOpenProfile = findViewById(R.id.btn_open_profile);

        btnOpenProfile.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String domain = etDomain.getText().toString().trim();

            Intent intent = new Intent(BasicUiLayoutActivity.this, ProfileDetailsActivity.class);
            intent.putExtra("EXTRA_NAME", name);
            intent.putExtra("EXTRA_EMAIL", email);
            intent.putExtra("EXTRA_DOMAIN", domain);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

