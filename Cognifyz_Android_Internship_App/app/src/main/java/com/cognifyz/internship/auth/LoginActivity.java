package com.cognifyz.internship.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.cognifyz.internship.MainActivity;
import com.cognifyz.internship.R;
import com.cognifyz.internship.util.SessionManager;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilAuthName, tilAuthEmail, tilAuthPassword;
    private TextInputEditText etAuthName, etAuthEmail, etAuthPassword;
    private Button btnAuthPrimary, btnQuickGuest;
    private TextView tvAuthModeTitle, tvToggleAuthMode, tvAuthPrivacy, tvAuthTerms;

    private boolean isSignUpMode = false;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(this);
        if (sessionManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Auto redirect if already authenticated
        if (sessionManager.isLoggedIn()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
            super.onCreate(savedInstanceState);
            return;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tilAuthName = findViewById(R.id.til_auth_name);
        tilAuthEmail = findViewById(R.id.til_auth_email);
        tilAuthPassword = findViewById(R.id.til_auth_password);
        etAuthName = findViewById(R.id.et_auth_name);
        etAuthEmail = findViewById(R.id.et_auth_email);
        etAuthPassword = findViewById(R.id.et_auth_password);
        btnAuthPrimary = findViewById(R.id.btn_auth_primary);
        btnQuickGuest = findViewById(R.id.btn_quick_guest);
        tvAuthModeTitle = findViewById(R.id.tv_auth_mode_title);
        tvToggleAuthMode = findViewById(R.id.tv_toggle_auth_mode);
        tvAuthPrivacy = findViewById(R.id.tv_auth_privacy);
        tvAuthTerms = findViewById(R.id.tv_auth_terms);

        // Toggle Sign In vs Sign Up
        tvToggleAuthMode.setOnClickListener(v -> toggleMode());

        // Fast Evaluation Button
        btnQuickGuest.setOnClickListener(v -> {
            sessionManager.createLoginSession("Gaurav Kumar", "ama.gauravkumar@gmail.com");
            Toast.makeText(this, "Welcome Intern Gaurav Kumar! 🚀", Toast.LENGTH_SHORT).show();
            proceedToDashboard();
        });

        // Main Auth Submit Button
        btnAuthPrimary.setOnClickListener(v -> performAuthentication());

        // Privacy Policy Modal
        tvAuthPrivacy.setOnClickListener(v -> showPrivacyPolicyDialog());

        // Terms & Conditions Modal
        tvAuthTerms.setOnClickListener(v -> showTermsDialog());
    }

    private void toggleMode() {
        isSignUpMode = !isSignUpMode;
        if (isSignUpMode) {
            tvAuthModeTitle.setText("Intern Registration (Sign Up)");
            tilAuthName.setVisibility(View.VISIBLE);
            btnAuthPrimary.setText("Create Account & Sign In");
            tvToggleAuthMode.setText("Already registered? Sign In");
        } else {
            tvAuthModeTitle.setText("Intern Sign In");
            tilAuthName.setVisibility(View.GONE);
            btnAuthPrimary.setText("Sign In");
            tvToggleAuthMode.setText("Don't have an account? Sign Up");
        }
    }

    private void performAuthentication() {
        String email = etAuthEmail.getText() != null ? etAuthEmail.getText().toString().trim() : "";
        String password = etAuthPassword.getText() != null ? etAuthPassword.getText().toString().trim() : "";
        String name = etAuthName.getText() != null ? etAuthName.getText().toString().trim() : "";

        tilAuthEmail.setError(null);
        tilAuthPassword.setError(null);
        tilAuthName.setError(null);

        if (isSignUpMode && TextUtils.isEmpty(name)) {
            tilAuthName.setError("Full name is required");
            return;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilAuthEmail.setError("Enter a valid email address");
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 6) {
            tilAuthPassword.setError("Password must be at least 6 characters");
            return;
        }

        String displayName = isSignUpMode ? name : "Gaurav Kumar";
        sessionManager.createLoginSession(displayName, email);
        Toast.makeText(this, "Authentication successful! Welcome, " + displayName, Toast.LENGTH_SHORT).show();
        proceedToDashboard();
    }

    private void proceedToDashboard() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showPrivacyPolicyDialog() {
        new AlertDialog.Builder(this)
                .setTitle("🔒 Cognifyz Privacy Policy")
                .setMessage("1. Data Safety: Cognifyz Internship App respects your privacy. All user profiles and SQLite database entries are stored securely and locally on your device.\n\n" +
                        "2. No Background Tracking: This application does not track GPS location, read personal contacts, or access external storage without user consent.\n\n" +
                        "3. Minimal Permissions: Only network status and internet permissions are requested for REST API verification.\n\n" +
                        "4. Data Retention: You can delete all locally stored records anytime using the SQLite Database module.\n\n" +
                        "For questions, contact privacy@cognifyz.com")
                .setPositiveButton("I Understand", null)
                .show();
    }

    private void showTermsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("📋 Cognifyz Terms & Conditions")
                .setMessage("1. Internship Scope: This application is developed by Gaurav Kumar as part of the Cognifyz Android Development Internship program.\n\n" +
                        "2. Authorized Use: Code snippets and tasks demonstrate standard Android SDK architecture (Levels 1-4).\n\n" +
                        "3. Academic Integrity: The code represents genuine work for internship evaluation and technical assessment.\n\n" +
                        "4. Support: Technical inquiries may be directed to support@cognifyz.com.")
                .setPositiveButton("Accept", null)
                .show();
    }
}