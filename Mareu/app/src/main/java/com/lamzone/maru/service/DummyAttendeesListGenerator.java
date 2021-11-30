package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyAttendeesListGenerator {

    //call the API to access to the Attendees
    private static List<Attendee> attendeesList = DummyAttendeeGenerator.generateAttendees();

    //To simulate already recorded Attendees Lists for planned meetings
    private static List<Attendee> DUMMY_ATTENDEES_LIST_1 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_2 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_3 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_4 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_5 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_6 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_7 = new ArrayList<>();
    private static List<Attendee> DUMMY_ATTENDEES_LIST_8 = new ArrayList<>();


    public static List<List<Attendee>> DUMMY_ATTENDEES_LISTS = Arrays.asList(
            DUMMY_ATTENDEES_LIST_1,
            DUMMY_ATTENDEES_LIST_2,
            DUMMY_ATTENDEES_LIST_3,
            DUMMY_ATTENDEES_LIST_4,
            DUMMY_ATTENDEES_LIST_5,
            DUMMY_ATTENDEES_LIST_6,
            DUMMY_ATTENDEES_LIST_7,
            DUMMY_ATTENDEES_LIST_8
    );

    //TODO: find a best way to load attendees lists
    static List<List<Attendee>> generateAttendeesLists() {
        DUMMY_ATTENDEES_LIST_1.clear();
        DUMMY_ATTENDEES_LIST_2.clear();
        DUMMY_ATTENDEES_LIST_3.clear();
        DUMMY_ATTENDEES_LIST_4.clear();
        DUMMY_ATTENDEES_LIST_5.clear();
        DUMMY_ATTENDEES_LIST_6.clear();
        DUMMY_ATTENDEES_LIST_7.clear();
        DUMMY_ATTENDEES_LIST_8.clear();

        int i;
        for (i = 0; i < 4; i++) {
            DUMMY_ATTENDEES_LIST_1.add(attendeesList.get(i));
        }
        for (i = 4; i < 8; i++) {
            DUMMY_ATTENDEES_LIST_2.add(attendeesList.get(i));
        }
        for (i = 1; i < 5; i++) {
            DUMMY_ATTENDEES_LIST_3.add(attendeesList.get(i));
        }
        for (i = 2; i < 6; i++) {
            DUMMY_ATTENDEES_LIST_4.add(attendeesList.get(i));
        }
        for (i = 3; i < 5; i++) {
            DUMMY_ATTENDEES_LIST_5.add(attendeesList.get(i));
        }
        for (i = 1; i < 7; i++) {
            DUMMY_ATTENDEES_LIST_6.add(attendeesList.get(i));
        }
        for (i = 5; i < 9; i++) {
            DUMMY_ATTENDEES_LIST_7.add(attendeesList.get(i));
        }
        for (i = 6; i < 9; i++) {
            DUMMY_ATTENDEES_LIST_8.add(attendeesList.get(i));
        }


        return DUMMY_ATTENDEES_LISTS;
    }

    static String getAttendeesListEmailAddresses(List<Attendee> attendeesList) {
        String strAttendeesListEmailAddresses;

        StringBuilder commaSeparatedEmailAddressBuilder = new StringBuilder();

        for (Attendee attendee : attendeesList) {
            commaSeparatedEmailAddressBuilder.append(attendee.getStrAttendeeEmailAddress());
            commaSeparatedEmailAddressBuilder.append(", ");
        }
        //TODO: remove last comma
        return strAttendeesListEmailAddresses = commaSeparatedEmailAddressBuilder.toString();
    }


}
