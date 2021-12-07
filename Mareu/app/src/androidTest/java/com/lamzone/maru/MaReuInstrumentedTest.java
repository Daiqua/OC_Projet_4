package com.lamzone.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MaReuInstrumentedTest {


    private MaReuActivity mActivity;
    private MaReuApiService mApiService;

    private int ITEMS_COUNT = 8;

    @Rule
    public ActivityTestRule<MaReuActivity> mActivityRule =
            new ActivityTestRule(MaReuActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mApiService = DI.getNewInstanceApiService();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void MeetingsList_shouldNotBeEmpty() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void ClickOnDeleteButton_ShouldDeleteMeeting() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //check the number of items displayed
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(ITEMS_COUNT));
        // When perform a click on the delete icon
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, new DeleteViewAction()));
        // Then the number of element is 7
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void ClickOnAddMeetingButtonInMaReuActivity_ShouldDisplayAddMeetingActivityLayout() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //click on add meeting button
        onView(withId(R.id.activity_ma_reu_add_meeting_button))
                .perform(click());
        //check add meeting layout is displayed
        onView(ViewMatchers.withId(R.id.activity_add_meeting_layout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void ClickOnSaveButtonInAddMeetingActivity_ShouldAddOneMeetingInMaReuActivity() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //click on add meeting button
        onView(withId(R.id.activity_ma_reu_add_meeting_button))
                .perform(click());
        //check add meeting layout is displayed
        onView(ViewMatchers.withId(R.id.activity_add_meeting_layout))
                .check(matches(isDisplayed()));
        //fill the data
        //fill the topic
        onView(ViewMatchers.withId(R.id.activity_add_meeting_topic))
                .perform(typeText("Test meeting"));
        //click on the spinner to see rooms
        onView(withId(R.id.activity_add_meeting_room_spinner))
                .perform(click());
        //click on "salle 1"
        onData(allOf(is(instanceOf(String.class)), is("salle 1")))
                .perform(click());
        //set the Date picker
        onView(withId(R.id.activity_add_meeting_date_picker))
                .perform(PickerActions.setDate(2021, 12, 1));
        //set the Time picker
        onView(withId(R.id.activity_add_meeting_time_picker))
                .perform(PickerActions.setTime(11, 0));
        //add 2 attendees
        onView(ViewMatchers.withId(R.id.activity_add_meeting_duration))
                .perform(typeText("20"));
        onView(ViewMatchers.withId(R.id.activity_add_meeting_attendees))
                .perform(typeText("attendee 1"));
        onView(ViewMatchers.withId(R.id.activity_add_meeting_add_attendee))
                .perform(click());
        onView(ViewMatchers.withId(R.id.activity_add_meeting_attendees))
                .perform(typeText("attendee 2"));
        onView(ViewMatchers.withId(R.id.activity_add_meeting_add_attendee))
                .perform(click());
        //save
        onView(ViewMatchers.withId(R.id.activity_add_meeting_save_button))
                .perform(click());
        //Meeting list should be displayed with one additionnal item
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()))
                .check(withItemCount(ITEMS_COUNT + 1));
        //TODO: check content of added meeting

    }

    @Test
    public void SelectRoomAsFilter_ShouldShowOnlyMeetingInThisRoom() {

    }

    @Test
    public void SelectDateAsFilter_ShouldShowOnlyMeetingAtThisDate() {

    }

    @Test
    public void RemoveFilter_ShouldShowAllMeetings() {

    }
}