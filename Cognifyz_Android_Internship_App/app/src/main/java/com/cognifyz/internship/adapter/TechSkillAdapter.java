package com.cognifyz.internship.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cognifyz.internship.R;
import com.cognifyz.internship.model.TechSkill;
import java.util.List;

public class TechSkillAdapter extends RecyclerView.Adapter<TechSkillAdapter.SkillViewHolder> {

    private List<TechSkill> skillList;

    public TechSkillAdapter(List<TechSkill> skillList) {
        this.skillList = skillList;
    }

    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tech_skill, parent, false);
        return new SkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        TechSkill skill = skillList.get(position);
        holder.tvSkillBadge.setText(skill.getIcon());
        holder.tvSkillTitle.setText(skill.getTitle());
        holder.tvSkillCategory.setText(skill.getCategory());
    }

    @Override
    public int getItemCount() {
        return skillList.size();
    }

    public static class SkillViewHolder extends RecyclerView.ViewHolder {
        TextView tvSkillBadge, tvSkillTitle, tvSkillCategory;

        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSkillBadge = itemView.findViewById(R.id.tv_skill_badge);
            tvSkillTitle = itemView.findViewById(R.id.tv_skill_title);
            tvSkillCategory = itemView.findViewById(R.id.tv_skill_category);
        }
    }
}

