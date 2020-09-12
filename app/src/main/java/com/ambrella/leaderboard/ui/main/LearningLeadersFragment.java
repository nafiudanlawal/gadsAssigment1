package com.ambrella.leaderboard.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.content.AsyncTaskLoader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ambrella.leaderboard.R;

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
        return inflater.inflate(R.layout.fragment_learning_leaders, container, false);
    }

    public class LoadLearningLeaders extends AsyncTaskLoader{

        public LoadLearningLeaders(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public Object loadInBackground() {
            return null;
        }


    }
}