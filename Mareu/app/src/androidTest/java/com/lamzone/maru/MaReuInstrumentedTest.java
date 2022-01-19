package com.lamzone.maru;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.lamzone.maru.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import android.widget.DatePicker;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.lamzone.maru.di.DI;
import com.lamzone.maru.service.MaReuApiService;
import com.lamzone.maru.utils.DeleteViewAction;

import org.junit.After;
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


    private MaReuApiService mApiService;

    private final int ITEMS_COUNT = 10;
    private final int MEETINGS_THE_2021_11_22 = 7;
    private final int MEETING_THE_2021_11_23 = 1;
    private final int MEETING_IN_ROOM_1 = 1;

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(MaReuActivity.class);

    @Before
    public void setUp() {
        MaReuActivity activity = (MaReuActivity) mActivityRule.getActivity();
        mApiService = DI.getApiService();
        assertThat(activity, notNullValue());
    }

    @After
    public void endTest() {
        MaReuActivity.setIsRoomFilterActivated(false);
        MaReuActivity.setIsDateFilterActivated(false);
        mApiService = DI.getNewInstanceApiService();
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
                .perform(typeText("Test meeting"))
                .perform(closeSoftKeyboard());
        //click on the spinner to see rooms
        onView(withId(R.id.activity_add_meeting_room_spinner))
                .perform(click());
        //click on "salle 1"
        onData(allOf(is(instanceOf(String.class)), is("salle 1")))
                .perform(click())
                .perform(closeSoftKeyboard());
        //set the Date picker
        onView(withId(R.id.activity_add_meeting_date_picker))
                .perform(PickerActions.setDate(2021, 12, 1))
                .perform(closeSoftKeyboard());
        //set the Time picker
        onView(withId(R.id.activity_add_meeting_time_picker))
                .perform(PickerActions.setTime(11, 0))
                .perform(closeSoftKeyboard())
                .perform(scrollTo());
        //set the duration
        onView(ViewMatchers.withId(R.id.activity_add_meeting_duration))
                .perform(ViewActions.scrollTo())
                .perform(typeText("20"))
                .perform(closeSoftKeyboard());
        //add 2 attendees
        onView(ViewMatchers.withId(R.id.activity_add_meeting_attendees))
                .perform(ViewActions.scrollTo())
                .perform(typeText("attendee 1"));
        onView(ViewMatchers.withId(R.id.activity_add_meeting_add_attendee))
                .perform(click());
        onView(ViewMatchers.withId(R.id.activity_add_meeting_attendees))
                .perform(typeText("attendee 2"));
        onView(ViewMatchers.withId(R.id.activity_add_meeting_add_attendee))
                .perform(click())
                .perform(closeSoftKeyboard());
        //save
        onView(ViewMatchers.withId(R.id.activity_add_meeting_save_button))
                .perform(ViewActions.scrollTo())
                .perform(click());
        //Meeting list should be displayed with one additional item
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()))
                .check(withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void SelectRoomAsFilter_ShouldShowOnlyMeetingInThisRoom() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //click on the menu button
        onView(withId(R.id.menu_meeting_list_activity))
                .perform(click());
        //click on filtre salle
        onView(ViewMatchers.withText("filtre salle"))
                .perform(click());
        //click on salle 1
        onView(withText("salle 1"))
                .perform(click());
        //Check the number of meetings
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(MEETING_IN_ROOM_1));
    }

    @Test
    public void SelectDateAsFilter_ShouldShowOnlyMeetingAtThisDate() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //click on the menu button
        onView(withId(R.id.menu_meeting_list_activity))
                .perform(click());
        //click on filtre date
        onView(ViewMatchers.withText("filtre date"))
                .perform(click());
        //set the date
        onView(isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(2021, 11, 23));
        //click OK
        onView(withText("OK")).perform(click());
        //check the number of meetings
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(MEETING_THE_2021_11_23));
    }

    @Test
    public void RemoveFilter_ShouldShowAllMeetings() {
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(matches(isDisplayed()));
        //click on the menu button
        onView(withId(R.id.menu_meeting_list_activity))
                .perform(click());
        //set a filter
        onView(ViewMatchers.withText("filtre date"))
                .perform(click());
        onView(isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(2021, 11, 22));
        onView(withText("OK"))
                .perform(click());
        //check number of meeting with this filter
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(MEETINGS_THE_2021_11_22));
        //click on the filter button to remove the filter
        onView(withId(R.id.menu_meeting_list_activity))
                .perform(click());
        onView(ViewMatchers.withText("supprimer le filtre"))
                .perform(click());
        //check all meetings are displayed
        onView(ViewMatchers.withId(R.id.activity_meetings_list))
                .check(withItemCount(ITEMS_COUNT));
    }
}