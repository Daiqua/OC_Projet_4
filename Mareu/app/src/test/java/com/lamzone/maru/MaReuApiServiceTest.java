package com.lamzone.maru;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.model.Attendee;
import com.lamzone.maru.model.Meeting;
import com.lamzone.maru.model.MeetingRoom;
import com.lamzone.maru.service.DummyAttendeeGenerator;
import com.lamzone.maru.service.DummyAttendeesListGenerator;
import com.lamzone.maru.service.DummyMeetingGenerator;
import com.lamzone.maru.service.DummyMeetingRoomGenerator;
import com.lamzone.maru.service.MaReuApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

/**
 * Unit test on MaReu ApiService
 */
@RunWith(JUnit4.class)
public class MaReuApiServiceTest {

    private MaReuApiService service;
    private String targetedDateOne = "2021.11.22";//8 meetings planned
    private String targetedDateTwo = "2021.11.23";//0 meeting planned



    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetingsList = service.getMeetings();
        List<Meeting> expectedMeetingsList = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetingsList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetingsList.toArray()));
    }

    @Test
    public void addMeetingWithSuccess() {
        List<Meeting> meetingsList = service.getMeetings();
        int initialNumberOfMeetings = meetingsList.size();
        //create new meeting
        List<List<Attendee>> listsOfAttendees = service.getListsOfAttendees();
        List<MeetingRoom> meetingRoomList = service.getMeetingRooms();
        Meeting newMeeting = new Meeting("MeetingTest",meetingRoomList.get(0),listsOfAttendees.get(0));
        //add it to meetingsList
        service.addMeeting(newMeeting);
        int numberOfMeetingAfterAdding = meetingsList.size();
        assertEquals(initialNumberOfMeetings+1,numberOfMeetingAfterAdding);
    }

    @Test
    public void getAttendeesWithSuccess() {
        List<Attendee> attendeesList = service.getAttendees();
        List<Attendee> expectedAttendeesList = DummyAttendeeGenerator.DUMMY_ATTENDEES;
        assertThat(attendeesList, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedAttendeesList));

    }
    @Test
    public void addAttendeesWithSuccess() {
        List<Attendee> attendeesList = service.getAttendees();
        int initialAttendeesListSize = attendeesList.size();
        service.addAttendees(new Attendee("Test"));
        int AttendeesListSizeAfterAdding = attendeesList.size();
        assertEquals(initialAttendeesListSize+1,AttendeesListSizeAfterAdding);

    }
    @Test
    public void getListsOfAttendeesWithSuccess() {
        List<List<Attendee>> listsOfAttendees = service.getListsOfAttendees();
        List<List<Attendee>> expectedListsOfAttendees = DummyAttendeesListGenerator.DUMMY_ATTENDEES_LISTS;
        assertThat(listsOfAttendees, IsIterableContainingInAnyOrder
                                                        .containsInAnyOrder(expectedListsOfAttendees.toArray()));
    }

    @Test
    public void getMeetingRoomsWithSuccess() {
        List<MeetingRoom> meetingRoomList = service.getMeetingRooms();
        List<MeetingRoom> expectedMeetingRoomList = DummyMeetingRoomGenerator.DUMMY_MEETING_ROOMS;
        assertThat(meetingRoomList, IsIterableContainingInAnyOrder
                                                    .containsInAnyOrder(expectedMeetingRoomList.toArray()));
    }

    //TODO: to be changed when last comma will be removed
    @Test
    public void getAttendeesListEmailAddressesWithSuccess() {
        List<Attendee> attendeesList = service.getAttendees();
        String attendeesEmailAddresses = service.getAttendeesListEmailAddresses(attendeesList);
        String expectedAttendeesEmailAddress = "jean@lamzone.com, francis@lamzone.com, " +
                "alexandra@lamzone.com, maxime@lamzone.com, paul@lamzone.com, amandine@lamzone.com," +
                " luc@lamzone.com, viviane@lamzone.com, mathilde@lamzone.com, yoann@lamzone.com, ";
        assertEquals(attendeesEmailAddresses, IsIterableContainingInAnyOrder
                                                .containsInAnyOrder(expectedAttendeesEmailAddress));
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void getRoomsListWithSuccess() {
        List<MeetingRoom> meetingRoomList = service.getMeetingRooms();
        List<MeetingRoom> expectedMeetingRoomList = DummyMeetingRoomGenerator.DUMMY_MEETING_ROOMS;
        assertThat(meetingRoomList, IsIterableContainingInAnyOrder
                                                    .containsInAnyOrder(expectedMeetingRoomList.toArray()));
    }

    @Test
    public void  generateDateFilteredListWithSuccess() {
        List<Meeting> meetingsListAtTargetedDate = service.generateDateFilteredList(targetedDateOne);
        assertEquals(8, meetingsListAtTargetedDate);
        meetingsListAtTargetedDate = service.generateDateFilteredList(targetedDateTwo);
        assertEquals(0, meetingsListAtTargetedDate.size());
    }

    @Test
    public void generateRoomFilteredListWithSuccess() {
        String meetingRoomToFilter = service.getMeetingRooms().get(0).getStrMeetingRoomName();
        List<Meeting> meetingsAfterFilter = service.
                                              generateRoomFilteredList(meetingRoomToFilter);
        assertEquals(1, meetingsAfterFilter.size());
    }
}