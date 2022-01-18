package com.lamzone.maru;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.service.Util;
import com.lamzone.maru.ui.maréu_list.AddMeetingActivity;
import com.lamzone.maru.ui.maréu_list.DatePickerFragment;
import com.lamzone.maru.ui.maréu_list.MeetingsListRecyclerViewAdapter;
import com.lamzone.maru.ui.maréu_list.RoomsListFragment;

import java.util.ArrayList;
import java.util.List;

public class MaReuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, RoomsListFragment.RoomFilterListener {

    private List<Meeting> mMeetingsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MeetingsListRecyclerViewAdapter mMeetingsListRecyclerViewAdapter;

    //to manage the filters
    private static String strDateFiltered = "";//format: yyyy.MM.dd
    private static String strRoomFiltered = "";
    private TextView filterText;
    private static boolean isDateFilterActivated = false;
    private static boolean isRoomFilterActivated = false;

    RoomsListFragment roomsListFragment = new RoomsListFragment();

    //to manage the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    //to manage the menu
    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.filterDate:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "DatePicker");
                isRoomFilterActivated = false;
                break;
            case R.id.filterRoom:
                roomsListFragment.setRoomListener(this);
                roomsListFragment.show(getSupportFragmentManager(), "RoomsListFragment");
                isDateFilterActivated = false;
                break;
            case R.id.reset_filter:
                Toast.makeText(this, "Filtre réinitialisé", Toast.LENGTH_SHORT).show();
                filterText.setText("Filtres actifs: aucun");
                isDateFilterActivated = false;
                isRoomFilterActivated = false;
                generateMeetings();
                loadRecyclerView();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ma_reu);
        FloatingActionButton addMeeting = findViewById(R.id.activity_ma_reu_add_meeting_button);
        mRecyclerView = findViewById(R.id.activity_meetings_list);

        generateMeetings();
        loadRecyclerView();

        Toolbar toolbar = findViewById(R.id.activity_ma_reu_toolbar);
        setSupportActionBar(toolbar);
        filterText = findViewById(R.id.activity_ma_reu_filter_text);

        addMeeting.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddMeetingActivity.class);
            ActivityCompat.startActivity(v.getContext(), intent, null);
        });
    }

    public void generateMeetings() {
        MaReuApiService apiService = DI.getApiService();
        if (isDateFilterActivated) {
            mMeetingsList = apiService.generateDateFilteredList(strDateFiltered);
        } else if (isRoomFilterActivated) {
            mMeetingsList = apiService.generateRoomFilteredList(strRoomFiltered);
        } else {
            mMeetingsList = apiService.getMeetings();
        }
    }

    public void loadRecyclerView() {
        mMeetingsListRecyclerViewAdapter = new MeetingsListRecyclerViewAdapter(mMeetingsList, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMeetingsListRecyclerViewAdapter);
    }

    //to listen date of DatePicker - coming from the implement of DatePickerDialog
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        strDateFiltered = year + "." + (month + 1) + "." + dayOfMonth;//date format: yyyy.MM.dd
        filterText.setText(Util.convertYearMonthNumberDayToDayMonthName(strDateFiltered));
        isDateFilterActivated = true;
        generateMeetings();
        loadRecyclerView();
    }


    public static void setIsDateFilterActivated(boolean isDateFilterActivated) {
        MaReuActivity.isDateFilterActivated = isDateFilterActivated;
    }

    public static void setIsRoomFilterActivated(boolean isRoomFilterActivated) {
        MaReuActivity.isRoomFilterActivated = isRoomFilterActivated;
    }

    @Override
    public void getRoomFiltered(String roomName) {
        strRoomFiltered = roomName;
        filterText.setText(strRoomFiltered);
        isRoomFilterActivated = true;
        generateMeetings();
        loadRecyclerView();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();
        updateMeetingList();
    }

    public void updateMeetingList() {
        generateMeetings();
        mMeetingsListRecyclerViewAdapter.notifyDataSetChanged();
    }
}
