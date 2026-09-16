package com.cognifyz.internship.level1;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

/**
 * Task 2: Button Interaction
 * Objective: Enhance the app with button interaction (OnClickListener + Toast).
 */
public class ButtonInteractionActivity extends AppCompatActivity {

    private int clickCount = 0;
    private TextView tvCounterStatus;
    private Button btnInteract;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_interaction);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 2: Button Interaction");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvCounterStatus = findViewById(R.id.tv_counter_status);
        btnInteract = findViewById(R.id.btn_interact);
        btnReset = findViewById(R.id.btn_reset);

        // Step 2 & 3: Implement Click Listener & Toast message
        btnInteract.setOnClickListener(v -> {
            clickCount++;
            tvCounterStatus.setText("Button Click Count: " + clickCount);
            Toast.makeText(ButtonInteractionActivity.this, "Button Clicked! Count: " + clickCount, Toast.LENGTH_SHORT).show();
        });

        btnReset.setOnClickListener(v -> {
            clickCount = 0;
            tvCounterStatus.setText("Button Click Count: 0");
            Toast.makeText(ButtonInteractionActivity.this, "Counter Reset!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

