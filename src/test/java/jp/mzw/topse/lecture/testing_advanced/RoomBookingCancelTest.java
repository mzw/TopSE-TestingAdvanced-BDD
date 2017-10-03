package jp.mzw.topse.lecture.testing_advanced;

import java.text.ParseException;
import java.util.Date;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

public class RoomBookingCancelTest extends StoryBase {

	private RoomBooking system;

	@Given("the system is ready and $roomName is booked from $timeslot")
	public void doGiven(String roomName, String timeslot) throws ParseException {
		this.system = new RoomBooking();
		final RoomBooking.Room _room = RoomBooking.Room.valueOf(roomName);
		final RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(timeslot);
		this.system.book("foo", _room, _timeslot, new Date());
	}

	@When("$userName cancels $roomName booked from $timeslot")
	public void doWhen(String userName, String roomName, String timeslot) throws ParseException {
		final RoomBooking.Room _room = RoomBooking.Room.valueOf(roomName);
		final RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(timeslot);
		this.system.cancel(_room, _timeslot);
	}

	@Then("the system has no booking")
	public void doThen(String roomName, String userName, String timeslot) {
		Assert.assertArrayEquals("".toCharArray(), this.system.getDisplayContent().toCharArray());
	}

}
