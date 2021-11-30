package com.lamzone.maru.service;

import com.lamzone.maru.model.Attendee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyAttendeeGenerator {

    public static List<Attendee> DUMMY_ATTENDEES = Arrays.asList(
            new Attendee("Jean", "jean@lamzone.com"),
            new Attendee("Francis R", "francis@lamzone.com"),
            new Attendee("Alexandra S", "alexandra@lamzone.com"),
            new Attendee("Maxime", "maxime@lamzone.com"),
            new Attendee("Paul", "paul@lamzone.com"),
            new Attendee("Amandine", "amandine@lamzone.com"),
            new Attendee("Luc", "luc@lamzone.com"),
            new Attendee("Viviane", "viviane@lamzone.com"),
            new Attendee("Mathilde", "mathilde@lamzone.com"),
            new Attendee("Yoann", "yoann@lamzone.com")
    );

    static List<Attendee> generateAttendees() {
        return new ArrayList<>(DUMMY_ATTENDEES);
    }
}
