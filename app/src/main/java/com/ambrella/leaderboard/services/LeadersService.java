package com.ambrella.leaderboard.services;

import com.ambrella.leaderboard.TopLearner;
import com.ambrella.leaderboard.TopSkilled;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LeadersService {
    @GET("api/hours")
    Call<List<TopLearner>> getLearningHoursLeaders();

    @GET("api/skilliq")
    Call<List<TopSkilled>> getSkillIQLeaders();
}
