package com.cognifyz.internship.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import com.cognifyz.internship.R;
import com.cognifyz.internship.auth.LoginActivity;
import com.cognifyz.internship.util.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private TextView tvSettingsName, tvSettingsEmail;
    private SwitchCompat switchDarkMode, switchNotifications;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("App Settings & Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvSettingsName = findViewById(R.id.tv_settings_name);
        tvSettingsEmail = findViewById(R.id.tv_settings_email);
        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchNotifications = findViewById(R.id.switch_notifications);
        btnLogout = findViewById(R.id.btn_logout);

        // Load profile data
        tvSettingsName.setText(sessionManager.getUserName());
        tvSettingsEmail.setText(sessionManager.getUserEmail());

        // Toggle Dark Mode
        switchDarkMode.setChecked(sessionManager.isDarkMode());
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sessionManager.setDarkMode(isChecked);
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Toggle Notifications
        switchNotifications.setChecked(sessionManager.isNotificationsEnabled());
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            sessionManager.setNotificationsEnabled(isChecked);
            String msg = isChecked ? "Push notifications enabled" : "Push notifications disabled";
            Toast.makeText(SettingsActivity.this, msg, Toast.LENGTH_SHORT).show();
        });

        // Support Email Action
        findViewById(R.id.ll_contact_email).setOnClickListener(v -> sendSupportEmail());

        // Visit Website Action
        findViewById(R.id.ll_visit_website).setOnClickListener(v -> openOfficialWebsite());

        // Privacy Policy
        findViewById(R.id.ll_privacy_policy).setOnClickListener(v -> showPrivacyPolicyDialog());

        // Terms & Conditions
        findViewById(R.id.ll_terms_conditions).setOnClickListener(v -> showTermsDialog());

        // Logout
        btnLogout.setOnClickListener(v -> confirmLogout());
    }

    private void sendSupportEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:support@cognifyz.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Cognifyz Android Internship Query - Gaurav Kumar");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello Cognifyz Team,\n\nI have a question regarding the Android Development Internship project...\n\nIntern Name: " 
                + sessionManager.getUserName() + "\nEmail: " + sessionManager.getUserEmail());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email via..."));
        } catch (Exception e) {
            Toast.makeText(this, "No email app found. Please reach support@cognifyz.com", Toast.LENGTH_LONG).show();
        }
    }

    private void openOfficialWebsite() {
        try {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cognifyz.com"));
            startActivity(webIntent);
        } catch (Exception e) {
            Toast.makeText(this, "Unable to open browser.", Toast.LENGTH_SHORT).show();
        }
    }

    private void showPrivacyPolicyDialog() {
        new AlertDialog.Builder(this)
                .setTitle("🔒 Cognifyz Privacy Policy")
                .setMessage("1. Data Safety: Cognifyz Internship App respects your privacy. All user profiles and SQLite database entries are stored securely and locally on your device.\n\n" +
                        "2. No Background Tracking: This application does not track GPS location, read personal contacts, or access external storage without user consent.\n\n" +
                        "3. Minimal Permissions: Only network status and internet permissions are requested for REST API verification.\n\n" +
                        "4. Data Retention: You can delete all locally stored records anytime using the SQLite Database module.\n\n" +
                        "For inquiries, contact privacy@cognifyz.com")
                .setPositiveButton("OK", null)
                .show();
    }

    private void showTermsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("📋 Terms & Conditions")
                .setMessage("1. Internship Scope: This application is developed by Gaurav Kumar as part of the Cognifyz Android Development Internship program.\n\n" +
                        "2. Authorized Use: Code snippets and tasks demonstrate standard Android SDK architecture (Levels 1-4).\n\n" +
                        "3. Academic Integrity: The code represents genuine work for internship evaluation and technical assessment.\n\n" +
                        "4. Support: Technical inquiries may be directed to support@cognifyz.com.")
                .setPositiveButton("OK", null)
                .show();
    }

    private void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Sign Out")
                .setMessage("Are you sure you want to sign out?")
                .setPositiveButton("Sign Out", (dialog, which) -> {
                    sessionManager.logout();
                    Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}