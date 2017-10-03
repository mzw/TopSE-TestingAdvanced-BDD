package jp.mzw.topse.lecture.testing_advanced;

import java.text.ParseException;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class RoomBookingTest extends StoryBase {

	private RoomBooking system;

	@Given("the system is ready and no booking")
	public void doGiven() {
		this.system = new RoomBooking();
		this.system.clearAllBookings();
	}

	@When("$userName books $roomName from $timeslot")
	public void doWhen(String userName, String roomName, String timeslot) throws ParseException {
		this.system.book(userName, RoomBooking.Room.valueOf(roomName), RoomBooking.Timeslot.parse(timeslot), new Date());
	}

	@Then("the system displays: $roomName is booked by $userName from $timeslot")
	public void doThen(String roomName, String userName, String timeslot) {
		Assert.assertArrayEquals((roomName + " is booked by " + userName + " from " + timeslot).toCharArray(), this.system.getDisplayContent().toCharArray());
	}

}
