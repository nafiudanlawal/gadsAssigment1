package com.ambrella.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DefaultRecyclerAdapter extends RecyclerView.Adapter<DefaultRecyclerAdapter.ViewHolder> {
    private List<TopSkilled> mTopSkilleds;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public DefaultRecyclerAdapter(Context context, List<TopSkilled> topSkilleds) {
        mContext = context;
        mTopSkilleds = topSkilleds;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DefaultRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = mLayoutInflater.inflate(R.layout.test_view_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TopSkilled topSkilled = mTopSkilleds.get(position);
        holder.mTvTest.setText(topSkilled.getName());
    }


    @Override
    public int getItemCount() {
        return mTopSkilleds.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mTvTest;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTest = (TextView) itemView.findViewById(R.id.tvTest);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Course Selected", Snackbar.LENGTH_LONG ).show();
                }
            });
        }
    }
}
