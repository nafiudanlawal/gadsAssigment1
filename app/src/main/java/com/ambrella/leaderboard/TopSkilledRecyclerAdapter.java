package com.ambrella.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class TopSkilledRecyclerAdapter extends RecyclerView.Adapter<TopSkilledRecyclerAdapter.TopSkilledViewVolder> {

    private final ArrayList<TopSkilled> mTopSkilled;

    public TopSkilledRecyclerAdapter(ArrayList<TopSkilled> topSkilled) {
        mTopSkilled = topSkilled;
    }

    @NonNull
    @Override
    public TopSkilledRecyclerAdapter.TopSkilledViewVolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.skill_iq_leader_view_item, parent, false);

        return new TopSkilledRecyclerAdapter.TopSkilledViewVolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TopSkilledRecyclerAdapter.TopSkilledViewVolder holder, int position) {
        TopSkilled topSkilled = mTopSkilled.get(position);
        holder.bind(topSkilled);
    }



    @Override
    public int getItemCount() {
        return mTopSkilled.size();
    }

    public class TopSkilledViewVolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvScore, tvCountry;
        public TopSkilledViewVolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvScore = itemView.findViewById(R.id.tvScore);
        }
        public void bind(TopSkilled topSkilled){
            tvName.setText(topSkilled.getName());
            tvCountry.setText(topSkilled.getCountry());
            tvScore.setText(topSkilled.getScore());
        }
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Item Clicked", Snackbar.LENGTH_LONG).show();
        }
    }
}
