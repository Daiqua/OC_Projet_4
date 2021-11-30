package com.lamzone.maru;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.DateConvertor;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.ui.maréu_list.AddMeetingActivity;
import com.lamzone.maru.ui.maréu_list.DatePickerFragment;
import com.lamzone.maru.ui.maréu_list.MeetingsListRecyclerViewAdapter;
import com.lamzone.maru.ui.maréu_list.RoomsListFragment;

import java.util.ArrayList;
import java.util.List;

//TODO: check extend with Brahim. Is it possible to extend DateConvertor and AppCompatActivity instead?
public class MaReuActivity extends DateConvertor {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetingsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MeetingsListRecyclerViewAdapter mMeetingsListRecyclerViewAdapter;
    private FloatingActionButton addMeeting;

    //to manage the date filter
    private static String strDateFiltered="";//format: yyyy.MM.dd
    private static String strRoomFiltered="";
    private static TextView filterText;
    private static String strFilterTextToShow;
    private static boolean isDateFilterActivated = false;
    private static boolean isRoomFilterActivated = false;

    //to manage the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    //to manage the menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.filterDate:
                DatePickerFragment datePickerFragment = DatePickerFragment.newInstance();
                datePickerFragment.show(getSupportFragmentManager(),"");
                break;
            case R.id.filterRoom:
                RoomsListFragment roomsListFragment = RoomsListFragment.newInstance();
                roomsListFragment.show(getSupportFragmentManager(),"");
                break;
            case R.id.reset_filter:
                Toast.makeText(this, "Filtre réinitialisé", Toast.LENGTH_SHORT).show();
                isDateFilterActivated = false;
                isRoomFilterActivated = false;
                setFilterText();
                MaReuActivityNewInstance();
                break;
        }
        return true;
    }

    private void MaReuActivityNewInstance(){
        Intent intent = new Intent(this, MaReuActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ma_reu);
        addMeeting = findViewById(R.id.activity_ma_reu_add_meeting);
        mRecyclerView = findViewById(R.id.activity_meetings_list);

        setFilterText();
        generateMeetings();
        loadRecyclerView();

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
        if (isRoomFilterActivated) {
            mMeetingsList = mApiService.generateRoomFilteredList(strRoomFiltered);
        }else if(isDateFilterActivated){
            mMeetingsList = mApiService.generateDateFilteredList(strDateFiltered);
        }else{mMeetingsList = mApiService.getMeetings();}
    }

    public void setFilterText() {
        filterText = findViewById(R.id.activity_ma_reu_filter_text);
        filterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "taille"+mMeetingsList.size(),Toast.LENGTH_SHORT ).show();
            }
        });
        if ((!isDateFilterActivated && !isRoomFilterActivated)) {
            strFilterTextToShow = "Filtres actifs: aucun";
        } else if (isDateFilterActivated){
            strFilterTextToShow = convert_yyyy_MM_dd_to_dd_MMMM(strDateFiltered);
        } else if (isRoomFilterActivated){
            strFilterTextToShow = strRoomFiltered;
        }
        filterText.setText(strFilterTextToShow);
    }

    public void loadRecyclerView(){
        mMeetingsListRecyclerViewAdapter = new MeetingsListRecyclerViewAdapter(mMeetingsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMeetingsListRecyclerViewAdapter);
    }

    public static void setIsDateFilterActivated(boolean dateFilterActivated) {
        isDateFilterActivated = dateFilterActivated;
        if (isDateFilterActivated == true){isRoomFilterActivated = false;}
    }

    public static void setISRoomFilterActivated(boolean roomFilterActivated) {
        isRoomFilterActivated = roomFilterActivated;
        if (isRoomFilterActivated == true){isDateFilterActivated = false;}
    }

    public static String getStrDateFiltered() {
        return strDateFiltered;
    }

    public static void setStrDateFiltered(String dateFiltered) {
        strDateFiltered = dateFiltered;
    }

    public static void setStrRoomFiltered(String RoomFiltered) {
        strRoomFiltered = RoomFiltered;
    }
}