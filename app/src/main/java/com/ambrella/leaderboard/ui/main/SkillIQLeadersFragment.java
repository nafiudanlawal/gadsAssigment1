package com.ambrella.leaderboard.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ambrella.leaderboard.DefaultRecyclerAdapter;
import com.ambrella.leaderboard.R;
import com.ambrella.leaderboard.TopSkilled;
import com.ambrella.leaderboard.TopSkilledRecyclerAdapter;
import com.ambrella.leaderboard.services.LeadersService;
import com.ambrella.leaderboard.services.ServiceBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillIQLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillIQLeadersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private TopSkilled mTopSkilled;
    private TopSkilledRecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRvSkilledLeaders;
    private ImageView mIvLoader;
    private static Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTvLaodStatus;

    public SkillIQLeadersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SkillIQLeadersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SkillIQLeadersFragment newInstance() {
        SkillIQLeadersFragment fragment = new SkillIQLeadersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
        mContext = getContext();
        mRvSkilledLeaders = view.findViewById(R.id.rvSkillIQ);
        mProgressBar = view.findViewById(R.id.pbarTopSkilled);
        mTvLaodStatus = view.findViewById(R.id.tvLoadStatus);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvSkilledLeaders.setLayoutManager(layoutManager);
        loadData();
        return view;
    }


    public void loadData(){
        LeadersService mGetLeadersService = ServiceBuilder.buildService(LeadersService.class);
        Call<List<TopSkilled>> call = mGetLeadersService.getSkillIQLeaders();
        call.enqueue(new Callback<List<TopSkilled>>() {
            @Override
            public void onResponse(Call<List<TopSkilled>> call, Response<List<TopSkilled>> response) {
                mRvSkilledLeaders.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mRvSkilledLeaders.setAdapter(new TopSkilledRecyclerAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<TopSkilled>> call, Throwable t) {
                showToast("Failded to Load");
                mProgressBar.setVisibility(View.INVISIBLE);
                mTvLaodStatus.setText("Failed to Load Data\nCheck Your Internet Connection.");
                mTvLaodStatus.setVisibility(View.VISIBLE);
            }
        });
    }
    public void showToast(String msg){
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }
}