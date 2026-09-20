package com.cognifyz.internship;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.cognifyz.internship.ai.AiAssistantActivity;
import com.cognifyz.internship.level1.ButtonInteractionActivity;
import com.cognifyz.internship.level1.HelloWorldActivity;
import com.cognifyz.internship.level2.BasicUiLayoutActivity;
import com.cognifyz.internship.level2.ListDisplayActivity;
import com.cognifyz.internship.level3.FetchDisplayDataActivity;
import com.cognifyz.internship.level3.SimpleFormActivity;
import com.cognifyz.internship.level4.NavigationActivity;
import com.cognifyz.internship.level4.SqliteDatabaseActivity;
import com.cognifyz.internship.settings.SettingsActivity;
import com.cognifyz.internship.util.NetworkUtils;
import com.cognifyz.internship.util.SessionManager;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private MaterialButton btnThemeToggle, btnOpenAi, btnOpenSettings;
    private Button btnShareProject;
    private TextView tvNetworkBadge, tvInternNameHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sessionManager = new SessionManager(this);
        if (sessionManager.isDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnThemeToggle = findViewById(R.id.btn_theme_toggle);
        btnOpenAi = findViewById(R.id.btn_open_ai);
        btnOpenSettings = findViewById(R.id.btn_open_settings);
        btnShareProject = findViewById(R.id.btn_share_project);
        tvNetworkBadge = findViewById(R.id.tv_network_badge);
        tvInternNameHeader = findViewById(R.id.tv_intern_name_header);

        btnThemeToggle.setText(sessionManager.isDarkMode() ? "☀️ Light" : "🌙 Dark");
        tvInternNameHeader.setText("Intern: " + sessionManager.getUserName());

        // Open Settings & Profile
        btnOpenSettings.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });

        // Network Status Monitor
        updateNetworkStatus();

        // Open AI Assistant
        btnOpenAi.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            startActivity(new Intent(MainActivity.this, AiAssistantActivity.class));
        });

        // Toggle Dark/Light Mode
        btnThemeToggle.setOnClickListener(v -> {
            boolean currentMode = sessionManager.isDarkMode();
            boolean newMode = !currentMode;
            sessionManager.setDarkMode(newMode);

            if (newMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        // Share Project Intent
        btnShareProject.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareMessage = " Excited to showcase my Android Development Internship project at Cognifyz IT Solutions Pvt. Ltd.! All 8 tasks + AI Assistant completed by Gaurav Kumar. #cognifyz #cognifyztech #cognifyztechnologies";
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share Project via"));
        });

        // Setup Task Cards
        setupCard(R.id.card_task_1, HelloWorldActivity.class);
        setupCard(R.id.card_task_2, ButtonInteractionActivity.class);
        setupCard(R.id.card_task_3, ListDisplayActivity.class);
        setupCard(R.id.card_task_4, BasicUiLayoutActivity.class);
        setupCard(R.id.card_task_5, FetchDisplayDataActivity.class);
        setupCard(R.id.card_task_6, SimpleFormActivity.class);
        setupCard(R.id.card_task_7, SqliteDatabaseActivity.class);
        setupCard(R.id.card_task_8, NavigationActivity.class);

        // Setup View Code Buttons
        setupCodeButton(R.id.btn_code_task_1, "Task 1: Hello World", "TextView tv = findViewById(R.id.tv_hello_message);\ntv.setText(\"Hello World! Welcome to Cognifyz - Gaurav Kumar\");");
        setupCodeButton(R.id.btn_code_task_2, "Task 2: Button Interaction", "btnInteract.setOnClickListener(v -> {\n    clickCount++;\n    Toast.makeText(this, \"Clicked: \" + clickCount, Toast.LENGTH_SHORT).show();\n});");
        setupCodeButton(R.id.btn_code_task_3, "Task 3: List Display", "ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, topics);\nlistView.setAdapter(adapter);");
        setupCodeButton(R.id.btn_code_task_4, "Task 4: Basic UI Layout", "Intent intent = new Intent(this, ProfileDetailsActivity.class);\nintent.putExtra(\"EXTRA_NAME\", name);\nstartActivity(intent);");
        setupCodeButton(R.id.btn_code_task_5, "Task 5: REST API & RecyclerView", "executorService.execute(() -> {\n    URL url = new URL(\"https://api.adviceslip.com/advice\");\n    HttpURLConnection conn = (HttpURLConnection) url.openConnection();\n    // Parse JSON and post to mainHandler\n});");
        setupCodeButton(R.id.btn_code_task_6, "Task 6: Live Validation Form", "etEmail.addTextChangedListener(new TextWatcher() {\n    public void onTextChanged(...) {\n        if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) tilEmail.setError(\"Invalid\");\n    }\n});");
        setupCodeButton(R.id.btn_code_task_7, "Task 7: SQLite Full CRUD", "public class DatabaseHelper extends SQLiteOpenHelper {\n    db.insert(TABLE_USERS, null, values);\n    db.rawQuery(\"SELECT * FROM users WHERE name LIKE ?\", ...);\n    db.update(...); db.delete(...);\n}");
        setupCodeButton(R.id.btn_code_task_8, "Task 8: Smooth Navigation", "startActivity(intent);\noverridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);");
    }

    private void setupCard(int cardId, Class<?> activityClass) {
        MaterialCardView card = findViewById(cardId);
        card.setOnClickListener(v -> {
            v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
            startActivity(new Intent(MainActivity.this, activityClass));
        });
    }

    private void setupCodeButton(int viewId, String title, String codeSnippet) {
        TextView btn = findViewById(viewId);
        if (btn != null) {
            btn.setOnClickListener(v -> {
                v.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
                new AlertDialog.Builder(this)
                        .setTitle(" " + title)
                        .setMessage(codeSnippet)
                        .setPositiveButton("Close", null)
                        .show();
            });
        }
    }

    private void updateNetworkStatus() {
        boolean isOnline = NetworkUtils.isNetworkAvailable(this);
        if (isOnline) {
            tvNetworkBadge.setText("🟢 Online • REST API & All 8 Tasks Ready");
        } else {
            tvNetworkBadge.setText("🟠 Offline Mode • SQLite Cache Active");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sessionManager != null) {
            if (tvInternNameHeader != null) {
                tvInternNameHeader.setText("Intern: " + sessionManager.getUserName());
            }
            if (btnThemeToggle != null) {
                btnThemeToggle.setText(sessionManager.isDarkMode() ? "☀️ Light" : "🌙 Dark");
            }
        }
        updateNetworkStatus();
    }
}

