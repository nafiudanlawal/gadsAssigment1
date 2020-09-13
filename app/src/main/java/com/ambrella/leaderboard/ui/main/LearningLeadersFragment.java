package com.ambrella.leaderboard.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ambrella.leaderboard.LearningLeadersRecyclerAdapter;
import com.ambrella.leaderboard.R;
import com.ambrella.leaderboard.TopLearner;
import com.ambrella.leaderboard.TopSkilled;
import com.ambrella.leaderboard.TopSkilledRecyclerAdapter;
import com.ambrella.leaderboard.services.LeadersService;
import com.ambrella.leaderboard.services.ServiceBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LearningLeadersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LearningLeadersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Context mContext;
    private RecyclerView mRvLearningLeaders;
    private ProgressBar mProgressBar;
    private TextView mTvLaodStatus;

    public LearningLeadersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LearningLeadersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LearningLeadersFragment newInstance() {
        LearningLeadersFragment fragment = new LearningLeadersFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_learning_leaders, container, false);

        mContext = getContext();
        mRvLearningLeaders = view.findViewById(R.id.rvLearningLeaders);
        mProgressBar = view.findViewById(R.id.pbarTopLearner);
        mTvLaodStatus = view.findViewById(R.id.tvLearningLeadersLoadStatus);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvLearningLeaders.setLayoutManager(layoutManager);
        loadData();

        return view;
    }

    public void loadData(){
        LeadersService mGetLeadersService = ServiceBuilder.buildService(LeadersService.class);
        Call<List<TopLearner>> call = mGetLeadersService.getLearningHoursLeaders();
        call.enqueue(new Callback<List<TopLearner>>() {
            @Override
            public void onResponse(Call<List<TopLearner>> call, Response<List<TopLearner>> response) {
                mRvLearningLeaders.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                mRvLearningLeaders.setAdapter(new LearningLeadersRecyclerAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<List<TopLearner>> call, Throwable t) {
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