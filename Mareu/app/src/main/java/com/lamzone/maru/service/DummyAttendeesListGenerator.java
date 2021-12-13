package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyAttendeesListGenerator {

    //call the API to access to the Attendees
    private static final List<Attendee> dummyAttendeesList = DummyAttendeeGenerator.generateAttendees();

    //To simulate already recorded Attendees Lists for planned meetings
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_1 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_2 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_3 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_4 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_5 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_6 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_7 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_8 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_9 = new ArrayList<>();
    private static final List<Attendee> DUMMY_ATTENDEES_LIST_10 = new ArrayList<>();


    public static List<List<Attendee>> DUMMY_ATTENDEES_LISTS = Arrays.asList(
            generateAttendeesList(0, 4, DUMMY_ATTENDEES_LIST_1),
            generateAttendeesList(4, 8, DUMMY_ATTENDEES_LIST_2),
            generateAttendeesList(1, 5, DUMMY_ATTENDEES_LIST_3),
            generateAttendeesList(2, 6, DUMMY_ATTENDEES_LIST_4),
            generateAttendeesList(3, 5, DUMMY_ATTENDEES_LIST_5),
            generateAttendeesList(1, 7, DUMMY_ATTENDEES_LIST_6),
            generateAttendeesList(5, 9, DUMMY_ATTENDEES_LIST_7),
            generateAttendeesList(1, 9, DUMMY_ATTENDEES_LIST_8),
            generateAttendeesList(2, 5, DUMMY_ATTENDEES_LIST_9),
            generateAttendeesList(0, 2, DUMMY_ATTENDEES_LIST_10)
    );

    static List<Attendee> generateAttendeesList(int a, int b, List<Attendee> attendeesList) {
        attendeesList.clear();
        int i;
        for (i = a; i < b; i++) {
            attendeesList.add(dummyAttendeesList.get(i));
        }
        return attendeesList;
    }

    static List<List<Attendee>> getDummyAttendeesLists() {
        return DUMMY_ATTENDEES_LISTS;
    }

    static String getAttendeesListEmailAddresses(List<Attendee> attendeesList) {

        StringBuilder commaSeparatedEmailAddressBuilder = new StringBuilder();

        for (Attendee attendee : attendeesList) {
            commaSeparatedEmailAddressBuilder.append(attendee.getStrAttendeeEmailAddress());
            commaSeparatedEmailAddressBuilder.append(", ");
        }
        //TODO: remove last comma
        return commaSeparatedEmailAddressBuilder.toString();
    }


}
