package com.ambrella.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LearningLeadersRecyclerAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.ViewHolder> {
    private List<TopLearner> mTopLearners;

    public LearningLeadersRecyclerAdapter(List<TopLearner> topLearners) {
        mTopLearners = topLearners;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.learning_leader_view_item, parent, false);

        return new LearningLeadersRecyclerAdapter.ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopLearner topLearner = mTopLearners.get(position);
        holder.bind(topLearner);
    }


    @Override
    public int getItemCount() {
        return mTopLearners.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvName;
        private TextView tvCountry;
        private TextView tvHours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvHours = itemView.findViewById(R.id.tvLearningHours);
        }

        public void bind(TopLearner topLearner){
            tvName.setText(topLearner.getName());
            tvCountry.setText(", " + topLearner.getCountry());
            tvHours.setText(String.valueOf(topLearner.getHours()) + " Learning Hours");
        }
    }
}
