package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyAttendeeGenerator {

    public static List<Attendee> DUMMY_ATTENDEES = Arrays.asList(
            new Attendee("jean@lamzone.com"),
            new Attendee("francis@lamzone.com"),
            new Attendee("alexandra@lamzone.com"),
            new Attendee("maxime@lamzone.com"),
            new Attendee("paul@lamzone.com"),
            new Attendee("amandine@lamzone.com"),
            new Attendee("luc@lamzone.com"),
            new Attendee("viviane@lamzone.com"),
            new Attendee("mathilde@lamzone.com"),
            new Attendee("yoann@lamzone.com")
    );

    static List<Attendee> generateAttendees() {
        return new ArrayList<>(DUMMY_ATTENDEES);
    }
}
