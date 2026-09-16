package com.cognifyz.internship.level3;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;
import com.cognifyz.internship.util.NotificationHelper;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

/**
 * Task 6: Simple Form (Super-Advanced Edition)
 * Objective: Real-time TextWatcher validation, DatePicker integration, and System Push Notification.
 */
public class SimpleFormActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPhone;
    private TextInputEditText etName, etEmail, etPhone, etDate, etLink;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 6: Simple Form");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilPhone = findViewById(R.id.til_phone);

        etName = findViewById(R.id.et_form_name);
        etEmail = findViewById(R.id.et_form_email);
        etPhone = findViewById(R.id.et_form_phone);
        etDate = findViewById(R.id.et_form_date);
        etLink = findViewById(R.id.et_form_link);
        btnSubmit = findViewById(R.id.btn_submit_form);

        // Pre-fill initial defaults
        etName.setText("Gaurav Kumar");
        etEmail.setText("ama.gauravkumar@gmail.com");
        etPhone.setText("9876543210");
        etDate.setText("31/08/2026");
        etLink.setText("https://www.linkedin.com/company/cognifyz-techonologies/");

        // Real-Time Live Validation (TextWatchers)
        etName.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().trim().isEmpty()) {
                    tilName.setError("Name is required");
                } else {
                    tilName.setError(null);
                }
            }
        });

        etEmail.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val = s.toString().trim();
                if (val.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(val).matches()) {
                    tilEmail.setError("Please enter a valid email format");
                } else {
                    tilEmail.setError(null);
                }
            }
        });

        etPhone.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String val = s.toString().trim();
                if (val.length() < 10) {
                    tilPhone.setError("Phone must be at least 10 digits (" + val.length() + "/10)");
                } else {
                    tilPhone.setError(null);
                }
            }
        });

        // DatePicker integration
        etDate.setOnClickListener(v -> showDatePicker());

        btnSubmit.setOnClickListener(v -> submitForm());
    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(this, (view, y, m, d) -> {
            String selectedDate = String.format("%02d/%02d/%d", d, (m + 1), y);
            etDate.setText(selectedDate);
        }, year, month, day);

        datePicker.show();
    }

    private void submitForm() {
        String name = etName.getText() != null ? etName.getText().toString().trim() : "";
        String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
        String phone = etPhone.getText() != null ? etPhone.getText().toString().trim() : "";
        String date = etDate.getText() != null ? etDate.getText().toString().trim() : "";
        String link = etLink.getText() != null ? etLink.getText().toString().trim() : "";

        if (name.isEmpty()) {
            tilName.setError("Name cannot be empty");
            return;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("Valid email required");
            return;
        }

        if (phone.length() < 10) {
            tilPhone.setError("10-digit phone number required");
            return;
        }

        // Trigger System Push Notification
        NotificationHelper.sendNotification(
                this,
                "Cognifyz Internship: Task 6 Form Submitted! ??",
                "Successfully registered intern details for " + name
        );

        Toast.makeText(this, "? Form validated & submitted successfully!", Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Submission Summary & Notification Sent ??")
                .setMessage("Intern Name: " + name + 
                           "\nEmail: " + email + 
                           "\nPhone: " + phone + 
                           "\nStart Date: " + date + 
                           "\nProject Link: " + link)
                .setPositiveButton("Awesome!", null)
                .show();
    }

    private abstract static class SimpleTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        @Override
        public void afterTextChanged(Editable s) {}
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
