package com.cognifyz.internship.level2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

public class ProfileDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 4: Profile Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        TextView tvName = findViewById(R.id.tv_profile_name);
        TextView tvEmail = findViewById(R.id.tv_profile_email);
        TextView tvDomain = findViewById(R.id.tv_profile_domain);
        Button btnBack = findViewById(R.id.btn_back_to_form);

        String name = getIntent().getStringExtra("EXTRA_NAME");
        String email = getIntent().getStringExtra("EXTRA_EMAIL");
        String domain = getIntent().getStringExtra("EXTRA_DOMAIN");

        if (name != null && !name.isEmpty()) tvName.setText(name);
        if (email != null && !email.isEmpty()) tvEmail.setText(email);
        if (domain != null && !domain.isEmpty()) tvDomain.setText(domain);

        btnBack.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
