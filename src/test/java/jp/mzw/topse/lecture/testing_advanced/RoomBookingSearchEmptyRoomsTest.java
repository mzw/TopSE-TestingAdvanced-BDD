package jp.mzw.topse.lecture.testing_advanced;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.junit.Assert;

import com.google.common.collect.Iterables;

public class RoomBookingSearchEmptyRoomsTest extends StoryBase {

	private RoomBooking system;
	private List<RoomBooking.Room> actual;

	@Given("the system is ready and $roomName is booked from $timeslot")
	public void doGiven(String roomName, String timeslot) throws ParseException {
		this.system = new RoomBooking();
		final RoomBooking.Room _room = RoomBooking.Room.valueOf(roomName);
		final RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(timeslot);
		this.system.book("foo", _room, _timeslot, new Date());
	}

	@When("searching empty rooms from $timeslot")
	public void doWhen(String timeslot) throws ParseException {
		final RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(timeslot);
		actual = this.system.searchEmptyRooms(_timeslot);
	}

	@Then("the system returns: $rooms")
	public void doThen(ExamplesTable rooms) {
		List<RoomBooking.Room> expect = new ArrayList<>();
		for (final Map<String, String> row : rooms.getRows()) {
			expect.add(RoomBooking.Room.valueOf(row.get("room")));
		}
		Assert.assertTrue(Iterables.elementsEqual(expect, actual));
	}

}
