package com.cognifyz.internship.level2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cognifyz.internship.R;

/**
 * Task 3: List Display
 * Objective: Develop an app that displays a list of items using an adapter.
 */
public class ListDisplayActivity extends AppCompatActivity {

    private ListView listViewTopics;

    // Static dataset for the list
    private final String[] topics = new String[]{
            "1. Android Activity Lifecycle & Intents",
            "2. User Interface Design with XML",
            "3. Views, ViewGroups & ConstraintLayout",
            "4. Adapters & ListView / RecyclerView",
            "5. User Input Handling & Validations",
            "6. SQLite Local Database Management",
            "7. Smooth Activity Transitions & Animations",
            "8. Background Processing & Services",
            "9. REST API Integration with Retrofit/Volley",
            "10. Material Design 3 Guidelines"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_display);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 3: List Display");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        listViewTopics = findViewById(R.id.list_view_topics);

        // Populate the list with static data using an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                topics
        );

        listViewTopics.setAdapter(adapter);

        // Handle item clicks
        listViewTopics.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTopic = topics[position];
            Toast.makeText(ListDisplayActivity.this, "Selected: " + selectedTopic, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
