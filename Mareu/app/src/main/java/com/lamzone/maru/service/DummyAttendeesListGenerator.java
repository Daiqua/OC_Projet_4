package com.lamzone.maru.service;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyAttendeesListGenerator {

    //call the API to access to the Attendees
    private static MaReuApiService mApiService = DI.getApiService();
    private static List<Attendee> attendeesList = mApiService.getAttendees();

    //To simulate already recorded Attendees Lists for planned meetings
    private static List<Attendee> DUMMY_ATTENDEES_LIST_1 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_2 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_3 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_4 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_5 = new ArrayList<>();



    public static List<List<Attendee>> DUMMY_ATTENDEES_LISTS = Arrays.asList(
            DUMMY_ATTENDEES_LIST_1,
            DUMMY_ATTENDEES_LIST_2,
            DUMMY_ATTENDEES_LIST_3,
            DUMMY_ATTENDEES_LIST_4,
            DUMMY_ATTENDEES_LIST_5
    );

    //TODO: find a best way to load attendees lists
    static List<List<Attendee>> generateAttendeesLists() {
        DUMMY_ATTENDEES_LIST_1.clear();
        DUMMY_ATTENDEES_LIST_1.add(attendeesList.get(0));
        DUMMY_ATTENDEES_LIST_1.add(attendeesList.get(1));
        DUMMY_ATTENDEES_LIST_1.add(attendeesList.get(2));
        DUMMY_ATTENDEES_LIST_1.add(attendeesList.get(3));

        DUMMY_ATTENDEES_LIST_2.clear();
        DUMMY_ATTENDEES_LIST_2.add(attendeesList.get(4));
        DUMMY_ATTENDEES_LIST_2.add(attendeesList.get(5));
        DUMMY_ATTENDEES_LIST_2.add(attendeesList.get(6));
        DUMMY_ATTENDEES_LIST_2.add(attendeesList.get(7));

        DUMMY_ATTENDEES_LIST_3.clear();
        DUMMY_ATTENDEES_LIST_3.add(attendeesList.get(8));
        DUMMY_ATTENDEES_LIST_3.add(attendeesList.get(9));
        DUMMY_ATTENDEES_LIST_3.add(attendeesList.get(1));

        DUMMY_ATTENDEES_LIST_4.clear();
        DUMMY_ATTENDEES_LIST_4.add(attendeesList.get(3));
        DUMMY_ATTENDEES_LIST_4.add(attendeesList.get(5));
        DUMMY_ATTENDEES_LIST_4.add(attendeesList.get(7));

        DUMMY_ATTENDEES_LIST_5.clear();
        DUMMY_ATTENDEES_LIST_5.add(attendeesList.get(2));
        DUMMY_ATTENDEES_LIST_5.add(attendeesList.get(4));

        return new ArrayList<>(DUMMY_ATTENDEES_LISTS);
    }

    static String getAttendeesListEmailAddresses(List<Attendee> attendeesList){
        String strAttendeesListEmailAddresses;

        StringBuilder commaSeparatedEmailAddressBuilder = new StringBuilder();

        for (Attendee attendee: attendeesList){
            commaSeparatedEmailAddressBuilder.append(attendee.getStrAttendeeEmailAddress());
            commaSeparatedEmailAddressBuilder.append(", ");
        }
        //TODO: remove last comma
        return strAttendeesListEmailAddresses = commaSeparatedEmailAddressBuilder.toString();
    }


}
