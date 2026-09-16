package com.cognifyz.internship.level4;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.adapter.UserRecordAdapter;
import com.cognifyz.internship.database.DatabaseHelper;
import com.cognifyz.internship.model.UserRecord;
import com.cognifyz.internship.util.NotificationHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

/**
 * Task 7: Basic Database Usage (Super-Advanced Pro Max Edition)
 * Objective: Full SQLite CRUD, Live Search, Edit Modal, CSV Export, and Push Notifications.
 */
public class SqliteDatabaseActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextInputEditText etName, etEmail, etRole, etSearch;
    private Button btnInsert, btnExportCsv;
    private TextView tvHeader;
    private RecyclerView recyclerView;
    private UserRecordAdapter adapter;
    private List<UserRecord> recordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_database);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Task 7: SQLite CRUD & Manager");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        dbHelper = new DatabaseHelper(this);

        etName = findViewById(R.id.et_db_name);
        etEmail = findViewById(R.id.et_db_email);
        etRole = findViewById(R.id.et_db_role);
        etSearch = findViewById(R.id.et_db_search);
        btnInsert = findViewById(R.id.btn_db_insert);
        btnExportCsv = findViewById(R.id.btn_db_export_csv);
        tvHeader = findViewById(R.id.tv_db_records_header);
        recyclerView = findViewById(R.id.recycler_view_db);

        // Pre-fill initial entry if database is empty
        recordList = dbHelper.getAllUsers();
        if (recordList.isEmpty()) {
            dbHelper.insertUser("Gaurav Kumar", "ama.gauravkumar@gmail.com", "Android Development Intern");
            dbHelper.insertUser("Cognifyz Mentor", "support@cognifyz.com", "Technical Lead");
            recordList = dbHelper.getAllUsers();
        }

        adapter = new UserRecordAdapter(
                recordList,
                record -> {
                    boolean deleted = dbHelper.deleteUser(record.getId());
                    if (deleted) {
                        Toast.makeText(SqliteDatabaseActivity.this, "Deleted record #" + record.getId(), Toast.LENGTH_SHORT).show();
                        refreshList();
                    }
                },
                this::showEditDialog
        );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        updateHeaderCount();

        // Insert record
        btnInsert.setOnClickListener(v -> {
            String name = etName.getText() != null ? etName.getText().toString().trim() : "";
            String email = etEmail.getText() != null ? etEmail.getText().toString().trim() : "";
            String role = etRole.getText() != null ? etRole.getText().toString().trim() : "";

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(SqliteDatabaseActivity.this, "Please enter Name and Email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (role.isEmpty()) role = "Cognifyz Intern";

            boolean success = dbHelper.insertUser(name, email, role);
            if (success) {
                // Trigger System Push Notification
                NotificationHelper.sendNotification(
                        this,
                        "Cognifyz DB: Record Saved ",
                        "New user record for " + name + " stored in SQLite."
                );

                Toast.makeText(SqliteDatabaseActivity.this, "Record saved into SQLite! ", Toast.LENGTH_SHORT).show();
                etName.setText("");
                etEmail.setText("");
                etRole.setText("");
                refreshList();
            } else {
                Toast.makeText(SqliteDatabaseActivity.this, "Failed to insert record", Toast.LENGTH_SHORT).show();
            }
        });

        // Export to CSV / Share
        btnExportCsv.setOnClickListener(v -> exportToCsvAndShare());

        // Live Search Filter
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (query.isEmpty()) {
                    recordList = dbHelper.getAllUsers();
                } else {
                    recordList = dbHelper.searchUsers(query);
                }
                adapter.updateList(recordList);
                updateHeaderCount();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void exportToCsvAndShare() {
        List<UserRecord> all = dbHelper.getAllUsers();
        if (all.isEmpty()) {
            Toast.makeText(this, "No records to export", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder csv = new StringBuilder();
        csv.append("ID,Name,Email,Role\n");
        for (UserRecord r : all) {
            csv.append(r.getId()).append(",")
               .append("\"").append(r.getName()).append("\",")
               .append("\"").append(r.getEmail()).append("\",")
               .append("\"").append(r.getRole()).append("\"\n");
        }

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Cognifyz Internship SQLite Records Export");
        shareIntent.putExtra(Intent.EXTRA_TEXT, csv.toString());
        startActivity(Intent.createChooser(shareIntent, "Export & Share SQLite Records via"));
    }

    private void showEditDialog(UserRecord record) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_basic_ui_layout, null);
        EditText editName = dialogView.findViewById(R.id.et_intern_name);
        EditText editEmail = dialogView.findViewById(R.id.et_intern_email);
        EditText editRole = dialogView.findViewById(R.id.et_intern_domain);
        Button btnSave = dialogView.findViewById(R.id.btn_open_profile);

        editName.setText(record.getName());
        editEmail.setText(record.getEmail());
        editRole.setText(record.getRole());
        btnSave.setText("Update SQLite Record");

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Edit Record #" + record.getId())
                .setView(dialogView)
                .setNegativeButton("Cancel", null)
                .create();

        btnSave.setOnClickListener(v -> {
            String newName = editName.getText().toString().trim();
            String newEmail = editEmail.getText().toString().trim();
            String newRole = editRole.getText().toString().trim();

            if (newName.isEmpty() || newEmail.isEmpty()) {
                Toast.makeText(this, "Name and Email cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean updated = dbHelper.updateUser(record.getId(), newName, newEmail, newRole);
            if (updated) {
                Toast.makeText(this, "Record #" + record.getId() + " updated in SQLite! ?", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                refreshList();
            } else {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void refreshList() {
        String currentQuery = etSearch.getText() != null ? etSearch.getText().toString().trim() : "";
        if (currentQuery.isEmpty()) {
            recordList = dbHelper.getAllUsers();
        } else {
            recordList = dbHelper.searchUsers(currentQuery);
        }
        adapter.updateList(recordList);
        updateHeaderCount();
    }

    private void updateHeaderCount() {
        tvHeader.setText("Records (" + recordList.size() + ") ? Tap card to Edit");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

