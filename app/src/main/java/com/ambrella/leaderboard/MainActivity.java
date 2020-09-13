package com.ambrella.leaderboard;

import android.content.Context;
import android.os.Bundle;

import com.ambrella.leaderboard.services.LeadersService;
import com.ambrella.leaderboard.services.ServiceBuilder;
import com.ambrella.leaderboard.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{

    private TextView mTv;
    private RecyclerView mRv;
    private Context mContext;

    // (new Retrofit.Call.Callback<List<TopSkilled>>);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*mRv = findViewById(R.id.rvTestRecylerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);
        mContext = this;*/


        /*LeadersService mGetLeadersService = ServiceBuilder.buildService(LeadersService.class);
        Call<List<TopSkilled>> call = mGetLeadersService.getSkillIQLeaders();
        call.enqueue(new Callback<List<TopSkilled>>() {
            @Override
            public void onResponse(Call<List<TopSkilled>> call, Response<List<TopSkilled>> response) {
                mRv.setAdapter(new DefaultRecyclerAdapter(mContext, response.body()));
            }

            @Override
            public void onFailure(Call<List<TopSkilled>> call, Throwable t) {
                showToast("Failded to Load");
            }
        });*/

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_submit:
                Toast.makeText(this, "Submit Selected", Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}