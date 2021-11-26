package com.lamzone.maru;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.ui.maréu_list.AddMeetingActivity;
import com.lamzone.maru.ui.maréu_list.MeetingFilterActivity;
import com.lamzone.maru.ui.maréu_list.MeetingsListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MaReuActivity extends AppCompatActivity {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetingsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MeetingsListRecyclerViewAdapter mMeetingsListRecyclerViewAdapter;
    private FloatingActionButton addMeeting;
    private ImageButton filterButton;

    //to manage the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.filterDate:
                Toast.makeText(this, "date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.filterRoom:
                Toast.makeText(this, "salle", Toast.LENGTH_SHORT).show();
                break;
        }
                return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        generateMeetings();
        setContentView(R.layout.activity_ma_reu);
        addMeeting = (FloatingActionButton) findViewById(R.id.activity_ma_reu_add_meeting);

        mRecyclerView = findViewById(R.id.activity_meetings_list);

        mMeetingsListRecyclerViewAdapter = new MeetingsListRecyclerViewAdapter(mMeetingsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMeetingsListRecyclerViewAdapter);


        Toolbar toolbar = findViewById(R.id.activity_ma_reu_toolbar);
        setSupportActionBar(toolbar);

        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddMeetingActivity.class);
                ActivityCompat.startActivity(v.getContext(), intent, null);
            }
        });


    }

    public void generateMeetings(){
        mApiService = DI.getApiService();
        mMeetingsList = mApiService.getMeetings();
    }
}