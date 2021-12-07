package com.lamzone.maru;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.ui.maréu_list.AddMeetingActivity;
import com.lamzone.maru.ui.maréu_list.DatePickerFragment;
import com.lamzone.maru.ui.maréu_list.MeetingsListRecyclerViewAdapter;
import com.lamzone.maru.ui.maréu_list.RoomsListFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//TODO: check extend with Brahim. Is it possible to extend DateConvertor and AppCompatActivity instead?
public class MaReuActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, RoomsListFragment.RoomFilterListener {

    private MaReuApiService mApiService;
    private List<Meeting> mMeetingsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MeetingsListRecyclerViewAdapter mMeetingsListRecyclerViewAdapter;
    private FloatingActionButton addMeeting;

    //to manage the filters
    private static String strDateFiltered = "";//format: yyyy.MM.dd
    private static String strRoomFiltered = "";
    private static TextView filterText;
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
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "DatePicker");
                isRoomFilterActivated = false;
                break;
            case R.id.filterRoom:
                RoomsListFragment roomsListFragment = new RoomsListFragment();
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
        addMeeting = findViewById(R.id.activity_ma_reu_add_meeting_button);
        mRecyclerView = findViewById(R.id.activity_meetings_list);

        generateMeetings();
        loadRecyclerView();

        Toolbar toolbar = findViewById(R.id.activity_ma_reu_toolbar);
        setSupportActionBar(toolbar);
        filterText = findViewById(R.id.activity_ma_reu_filter_text);

        addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddMeetingActivity.class);
                ActivityCompat.startActivity(v.getContext(), intent, null);
            }
        });
    }

    public void generateMeetings() {
        mApiService = DI.getApiService();
        if (isDateFilterActivated) {
            mMeetingsList = mApiService.generateDateFilteredList(strDateFiltered);
        } else if (isRoomFilterActivated) {
            mMeetingsList = mApiService.generateRoomFilteredList(strRoomFiltered);
        } else {mMeetingsList = mApiService.getMeetings();}
    }

    public void loadRecyclerView() {
        mMeetingsListRecyclerViewAdapter = new MeetingsListRecyclerViewAdapter(mMeetingsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                                                    DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mMeetingsListRecyclerViewAdapter);
    }

    //to listen date of DatePicker - coming from the implement of DatePickerDialog
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //TODO: remove or standardize with all string "date"
        /*
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        strDateFiltered = DateFormat.getDateInstance().format(calendar.getTime());
        */
        strDateFiltered = year + "." + (month + 1) + "." + dayOfMonth;//date format: yyyy.MM.dd
        filterText.setText(convert_yyyy_MM_dd_to_dd_MMMM(strDateFiltered));
        isDateFilterActivated = true;
        generateMeetings();
        //TODO: check with Brahim, don't work - replaced by loadRecyclerView()
        mMeetingsListRecyclerViewAdapter.notifyDataSetChanged();
        loadRecyclerView();
    }

    //TODO: put in utility class
    public String convert_yyyy_MM_dd_to_dd_MMMM(String strDatePattern_yyyy_MM_dd) {
        Date date = new Date();
        SimpleDateFormat dateFormatter_yyyy_MM_dd = new SimpleDateFormat("yyyy.MM.dd", Locale.FRANCE);
        SimpleDateFormat dateFormatter_dd_MMMM = new SimpleDateFormat("dd MMMM", Locale.FRANCE);
        try {
            date = dateFormatter_yyyy_MM_dd.parse(strDatePattern_yyyy_MM_dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String reformatedDateTo_dd_MMMM = dateFormatter_dd_MMMM.format(date);
        return reformatedDateTo_dd_MMMM;
    }

    public static void setIsDateFilterActivated(boolean isDateFilterActivated) {
        MaReuActivity.isDateFilterActivated = isDateFilterActivated;
    }

    public static void setIsRoomFilterActivated(boolean isRoomFilterActivated) {
        MaReuActivity.isRoomFilterActivated = isRoomFilterActivated;
    }

    //TODO: to comment
    @Override
    public void getRoomFiltered(String roomName) {
        strRoomFiltered = roomName;
        filterText.setText(strRoomFiltered);
        isRoomFilterActivated = true;
        generateMeetings();
        mMeetingsListRecyclerViewAdapter.notifyDataSetChanged();
        loadRecyclerView();
    }
    //TODO: to comment
    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        if (fragment instanceof RoomsListFragment){
            RoomsListFragment roomsListFragment = (RoomsListFragment) fragment;
            roomsListFragment.setRoomListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecyclerView();
    }
}
