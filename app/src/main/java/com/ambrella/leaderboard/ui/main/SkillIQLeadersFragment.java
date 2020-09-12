package com.ambrella.leaderboard.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ambrella.leaderboard.ApiUtil;
import com.ambrella.leaderboard.R;
import com.ambrella.leaderboard.TopSkilled;
import com.ambrella.leaderboard.TopSkilledRecyclerAdapter;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.net.URL;
import java.util.ArrayList;

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
        mRvSkilledLeaders = getActivity().findViewById(R.id.rvSkillIQ);
        mIvLoader = getActivity().findViewById(R.id.pbarTopSkilled);
        new LoadTopSkilledLearners().doInBackground(ApiUtil.buildSkillIQUrl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_skill_i_q_leaders, container, false);
    }

    public class LoadTopSkilledLearners extends AsyncTask<URL, Void , String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = ApiUtil.buildSkillIQUrl();
            String result = null;
            try{
                result = ApiUtil.getTopSkillIQJson(url);

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            if(json != null){
                ArrayList<TopSkilled> topSkilleds = ApiUtil.getTopSkilledFromJson(json);
                mIvLoader.setVisibility(View.INVISIBLE);
                mRvSkilledLeaders.setVisibility(View.VISIBLE);
                TopSkilledRecyclerAdapter adapter = new TopSkilledRecyclerAdapter(topSkilleds);
                mRvSkilledLeaders.setAdapter(adapter);
            }else {
                Snackbar.make(mRvSkilledLeaders, "Failed to load from API", Snackbar.LENGTH_LONG).show();
            }


        }
    }
}