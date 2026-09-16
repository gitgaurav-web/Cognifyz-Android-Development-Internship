package com.cognifyz.internship.ai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.adapter.ChatMessageAdapter;
import com.cognifyz.internship.model.ChatMessage;
import com.google.android.material.button.MaterialButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AiAssistantActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatMessageAdapter adapter;
    private List<ChatMessage> messageList;
    private EditText etInput;
    private MaterialButton btnSend;
    private Button chipQ1, chipQ2, chipQ3;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_assistant);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Cognifyz AI Assistant");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_view_chat);
        etInput = findViewById(R.id.et_chat_input);
        btnSend = findViewById(R.id.btn_chat_send);
        chipQ1 = findViewById(R.id.chip_q1);
        chipQ2 = findViewById(R.id.chip_q2);
        chipQ3 = findViewById(R.id.chip_q3);

        messageList = new ArrayList<>();
        messageList.add(new ChatMessage(
                "Hello Gaurav!  I'm your Cognifyz AI Assistant. I can help explain your 8 internship tasks, Android architecture, SQLite, REST API, or interview questions. Ask me anything!",
                false,
                getCurrentTime()
        ));

        adapter = new ChatMessageAdapter(messageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        btnSend.setOnClickListener(v -> sendMessage());

        chipQ1.setOnClickListener(v -> sendPredefinedMessage("Explain SQLite CRUD"));
        chipQ2.setOnClickListener(v -> sendPredefinedMessage("How does REST API work in Android?"));
        chipQ3.setOnClickListener(v -> sendPredefinedMessage("Explain Android Activity Lifecycle"));
    }

    private void sendPredefinedMessage(String text) {
        etInput.setText(text);
        sendMessage();
    }

    private void sendMessage() {
        String userText = etInput.getText() != null ? etInput.getText().toString().trim() : "";
        if (userText.isEmpty()) return;

        etInput.setText("");
        messageList.add(new ChatMessage(userText, true, getCurrentTime()));
        adapter.notifyItemInserted(messageList.size() - 1);
        recyclerView.scrollToPosition(messageList.size() - 1);

        // Simulated AI response with natural delay
        handler.postDelayed(() -> {
            String aiReply = generateAiResponse(userText);
            messageList.add(new ChatMessage(aiReply, false, getCurrentTime()));
            adapter.notifyItemInserted(messageList.size() - 1);
            recyclerView.scrollToPosition(messageList.size() - 1);
        }, 700);
    }

    private String generateAiResponse(String query) {
        String q = query.toLowerCase(Locale.ROOT);

        if (q.contains("sqlite") || q.contains("crud") || q.contains("database")) {
            return " SQLite in Android:\n" +
                    "? SQLiteOpenHelper manages database creation and schema versioning.\n" +
                    "? Create: db.insert() saves ContentValues.\n" +
                    "? Read: db.rawQuery() or db.query() returns a Cursor.\n" +
                    "? Update: db.update() modifies rows by ID.\n" +
                    "? Delete: db.delete() removes rows matching WHERE clauses.\n" +
                    "In Task 7, we also added live search filter and edit modal!";
        } else if (q.contains("api") || q.contains("rest") || q.contains("fetch") || q.contains("network")) {
            return " REST API in Android:\n" +
                    "? Network operations must run on background worker threads (ExecutorService or Coroutines).\n" +
                    "? We use HttpURLConnection to send GET requests to public JSON endpoints.\n" +
                    "? We parse the response using JSONObject / JSONArray.\n" +
                    "? Results are posted back to the main UI thread using Handler(Looper.getMainLooper()) to update the RecyclerView smoothly!";
        } else if (q.contains("lifecycle") || q.contains("activity")) {
            return " Activity Lifecycle:\n" +
                    "1. onCreate(): Initialize UI & ViewBinding\n" +
                    "2. onStart(): Activity becomes visible\n" +
                    "3. onResume(): Active & interactive with user\n" +
                    "4. onPause(): Partially obscured or navigating away\n" +
                    "5. onStop(): Activity no longer visible\n" +
                    "6. onDestroy(): Cleanup before Activity is cleared from memory.";
        } else if (q.contains("task") || q.contains("internship") || q.contains("cognifyz")) {
            return " Cognifyz Internship Progress:\n" +
                    "? Level 1: Hello World & Button Interaction (Toast)\n" +
                    "? Level 2: ListView & Multi-Activity Profile Layout\n" +
                    "? Level 3: Live REST API + RecyclerView & Validated Form\n" +
                    "? Level 4: Full SQLite CRUD & Animated Navigation\n" +
                    "All 8 tasks are 100% complete in your project!";
        } else if (q.contains("intent") || q.contains("navigation")) {
            return " Intents & Navigation:\n" +
                    "? Explicit Intents navigate between known activities: Intent(this, SecondActivity.class)\n" +
                    "? Data passing: intent.putExtra(\"KEY\", value)\n" +
                    "? Animations: overridePendingTransition(enterAnim, exitAnim) creates smooth slide and fade transitions!";
        } else {
            return " Great question! In professional Android development, we focus on Separation of Concerns, responsive XML layouts, background threading for I/O tasks, and persistent data storage (SQLite/Room). Let me know if you want code examples for any task!";
        }
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(new Date());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

