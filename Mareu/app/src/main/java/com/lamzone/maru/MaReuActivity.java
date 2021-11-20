package com.lamzone.maru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.ui.maréu_list.MeetingsListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MaReuActivity extends AppCompatActivity {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetingsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MeetingsListRecyclerViewAdapter mMeetingsListRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getApiService();

        setContentView(R.layout.activity_ma_reu);

        mRecyclerView = findViewById(R.id.activity_meetings_list);
        mMeetingsList = mApiService.getMeetings();
        mMeetingsListRecyclerViewAdapter = new MeetingsListRecyclerViewAdapter(mMeetingsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMeetingsListRecyclerViewAdapter);

        Toolbar toolbar = findViewById(R.id.activity_ma_reu_toolbar);
        setSupportActionBar(toolbar);


    }



}