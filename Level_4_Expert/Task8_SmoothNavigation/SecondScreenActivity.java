package com.cognifyz.internship.level4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

public class SecondScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Target Screen");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvReceived = findViewById(R.id.tv_received_message);
        Button btnBack = findViewById(R.id.btn_back_animated);

        String received = getIntent().getStringExtra("NAV_MESSAGE");
        if (received != null && !received.isEmpty()) {
            tvReceived.setText(received);
        }

        btnBack.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
