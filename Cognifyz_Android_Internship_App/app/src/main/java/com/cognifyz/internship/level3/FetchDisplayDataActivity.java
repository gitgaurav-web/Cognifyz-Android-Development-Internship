package com.cognifyz.internship.level3;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.adapter.TechSkillAdapter;
import com.cognifyz.internship.model.TechSkill;
import com.cognifyz.internship.util.NetworkUtils;
import com.google.android.material.snackbar.Snackbar;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Task 5: Fetch and Display Data (Advanced Upgrade)
 * Objective: Dynamically display data from predefined sources AND live online REST API.
 */
public class FetchDisplayDataActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TechSkillAdapter adapter;
    private List<TechSkill> skillList;
    private Button btnAddDynamic, btnFetchApi;
    private ProgressBar progressLoading;
    private LinearLayout llEmptyState;
    private int dynamicCounter = 1;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_display_data);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 5: Fetch & Display Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        recyclerView = findViewById(R.id.recycler_view_skills);
        btnAddDynamic = findViewById(R.id.btn_add_dynamic_item);
        btnFetchApi = findViewById(R.id.btn_fetch_api);
        progressLoading = findViewById(R.id.progress_loading);
        llEmptyState = findViewById(R.id.ll_skills_empty_state);

        // Predefined data list
        skillList = new ArrayList<>();
        skillList.add(new TechSkill("Java & Kotlin Architecture", "Core Programming Languages", "?"));
        skillList.add(new TechSkill("Android SDK & Jetpack Components", "Native Android Development", ""));
        skillList.add(new TechSkill("ConstraintLayout & Material 3", "Modern Responsive UI", ""));
        skillList.add(new TechSkill("RecyclerView with ViewBinding", "Optimized List Rendering", ""));
        skillList.add(new TechSkill("SQLite CRUD Database", "Local Offline Persistence", ""));
        skillList.add(new TechSkill("REST API & JSON Parsing", "Network Communication", ""));

        adapter = new TechSkillAdapter(skillList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Dynamic local addition
        btnAddDynamic.setOnClickListener(v -> {
            TechSkill newSkill = new TechSkill(
                    "Dynamic Skill #" + dynamicCounter,
                    "Dynamically added via User Interaction",
                    ""
            );
            skillList.add(0, newSkill);
            adapter.notifyItemInserted(0);
            recyclerView.scrollToPosition(0);
            checkEmptyState();
            Toast.makeText(FetchDisplayDataActivity.this, "Added Skill #" + dynamicCounter, Toast.LENGTH_SHORT).show();
            dynamicCounter++;
        });

        // Live REST API fetch
        btnFetchApi.setOnClickListener(v -> fetchLiveApiData());
        checkEmptyState();
    }

    private void fetchLiveApiData() {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Snackbar.make(recyclerView, "⚠️ No Internet Connection. Please check your network and retry.", Snackbar.LENGTH_LONG)
                    .setAction("RETRY", v -> fetchLiveApiData())
                    .show();
            return;
        }

        progressLoading.setVisibility(View.VISIBLE);
        btnFetchApi.setEnabled(false);

        executorService.execute(() -> {
            String fetchedTip = null;
            boolean requestFailed = false;
            try {
                // Public REST API returning JSON advice/tip
                URL url = new URL("https://api.adviceslip.com/advice");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    JSONObject jsonObject = new JSONObject(response.toString());
                    JSONObject slip = jsonObject.getJSONObject("slip");
                    fetchedTip = slip.getString("advice");
                } else {
                    requestFailed = true;
                    fetchedTip = "Focus on software architecture, clean patterns, and error handling.";
                }
            } catch (Exception e) {
                requestFailed = true;
                fetchedTip = "Focus on software architecture, clean patterns, and error handling.";
            }

            final String result = fetchedTip;
            final boolean wasFailed = requestFailed;
            mainHandler.post(() -> {
                progressLoading.setVisibility(View.GONE);
                btnFetchApi.setEnabled(true);

                if (result != null) {
                    TechSkill apiSkill = new TechSkill(
                            result,
                            wasFailed ? "Offline Fallback Cache" : "Live REST API Response (AdviceSlip)",
                            wasFailed ? "⚠️" : "🌐"
                    );
                    skillList.add(0, apiSkill);
                    adapter.notifyItemInserted(0);
                    recyclerView.scrollToPosition(0);
                    checkEmptyState();

                    if (wasFailed) {
                        Snackbar.make(recyclerView, "⚠️ Server response error. Fallback tip loaded.", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", v -> fetchLiveApiData())
                                .show();
                    } else {
                        Toast.makeText(FetchDisplayDataActivity.this, "Live API Data Received! 🚀", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });
    }

    private void checkEmptyState() {
        if (llEmptyState != null && recyclerView != null) {
            if (skillList.isEmpty()) {
                llEmptyState.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            } else {
                llEmptyState.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

