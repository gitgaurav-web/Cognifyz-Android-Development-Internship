package com.cognifyz.internship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.model.UserRecord;
import java.util.List;

public class UserRecordAdapter extends RecyclerView.Adapter<UserRecordAdapter.RecordViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(UserRecord record);
    }

    public interface OnItemClickListener {
        void onItemClick(UserRecord record);
    }

    private List<UserRecord> records;
    private OnDeleteClickListener deleteListener;
    private OnItemClickListener itemClickListener;

    public UserRecordAdapter(List<UserRecord> records, OnDeleteClickListener deleteListener, OnItemClickListener itemClickListener) {
        this.records = records;
        this.deleteListener = deleteListener;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_record, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordViewHolder holder, int position) {
        UserRecord item = records.get(position);
        holder.tvId.setText("#" + item.getId());
        holder.tvName.setText(item.getName());
        holder.tvEmail.setText(item.getEmail());
        holder.tvRole.setText(item.getRole());

        // Click whole item to Edit
        holder.itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(item);
            }
        });

        // Click delete button to Remove
        holder.btnDelete.setOnClickListener(v -> {
            if (deleteListener != null) {
                deleteListener.onDeleteClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void updateList(List<UserRecord> newRecords) {
        this.records = newRecords;
        notifyDataSetChanged();
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvEmail, tvRole;
        ImageButton btnDelete;

        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_record_id);
            tvName = itemView.findViewById(R.id.tv_record_name);
            tvEmail = itemView.findViewById(R.id.tv_record_email);
            tvRole = itemView.findViewById(R.id.tv_record_role);
            btnDelete = itemView.findViewById(R.id.btn_delete_record);
        }
    }
}
