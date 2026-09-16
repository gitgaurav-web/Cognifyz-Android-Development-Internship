package com.cognifyz.internship.level4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Task 8: Implement Navigation
 * Objective: Enhance the app with smooth navigation and transition animations between screens.
 */
public class NavigationActivity extends AppCompatActivity {

    private TextInputEditText etCustomMessage;
    private Button btnSlide, btnFade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 8: Smooth Navigation");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        etCustomMessage = findViewById(R.id.et_nav_custom_message);
        btnSlide = findViewById(R.id.btn_navigate_slide);
        btnFade = findViewById(R.id.btn_navigate_fade);

        // Slide Animation Navigation
        btnSlide.setOnClickListener(v -> {
            String message = etCustomMessage.getText() != null ? etCustomMessage.getText().toString().trim() : "";
            Intent intent = new Intent(NavigationActivity.this, SecondScreenActivity.class);
            intent.putExtra("NAV_MESSAGE", message + " (via Slide Animation)");
            // Custom transition animation using ActivityOptionsCompat
            androidx.core.app.ActivityOptionsCompat options = androidx.core.app.ActivityOptionsCompat.makeCustomAnimation(
                    NavigationActivity.this, R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent, options.toBundle());
        });

        // Fade Animation Navigation
        btnFade.setOnClickListener(v -> {
            String message = etCustomMessage.getText() != null ? etCustomMessage.getText().toString().trim() : "";
            Intent intent = new Intent(NavigationActivity.this, SecondScreenActivity.class);
            intent.putExtra("NAV_MESSAGE", message + " (via Fade Animation)");
            // Custom transition animation using ActivityOptionsCompat
            androidx.core.app.ActivityOptionsCompat options = androidx.core.app.ActivityOptionsCompat.makeCustomAnimation(
                    NavigationActivity.this, R.anim.fade_in, R.anim.fade_out);
            startActivity(intent, options.toBundle());
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

