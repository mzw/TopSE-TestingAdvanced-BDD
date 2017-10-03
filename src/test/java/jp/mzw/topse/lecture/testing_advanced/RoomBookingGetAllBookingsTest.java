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

public class RoomBookingGetAllBookingsTest extends StoryBase {

	private RoomBooking system;
	private List<RoomBooking.Booking> actual;

	@Given("the system is ready and $roomName is booked by $userName from $timeslot")
	public void doGiven(String roomName, String userName, String timeslot) throws ParseException {
		this.system = new RoomBooking();
		final RoomBooking.Room _room = RoomBooking.Room.valueOf(roomName);
		final RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(timeslot);
		this.system.book(userName, _room, _timeslot, new Date());
	}

	@When("confirming all bookings")
	public void doWhen() throws ParseException {
		actual = this.system.getAllBookings();
	}

	@Then("the system returns: $bookings")
	public void doThen(ExamplesTable bookings) throws ParseException {
		List<RoomBooking.Booking> expect = new ArrayList<>();
		for (final Map<String, String> row : bookings.getRows()) {
			String _userName = row.get("user");
			RoomBooking.Room _room = RoomBooking.Room.valueOf(row.get("room"));
			RoomBooking.Timeslot _timeslot = RoomBooking.Timeslot.parse(row.get("timeslot"));
			expect.add(new RoomBooking.Booking(_userName, _room, _timeslot, new Date()));
		}
		Assert.assertEquals(expect.size(), actual.size());
		for (int i = 0; i < expect.size(); i++) {
			Assert.assertArrayEquals(expect.get(i).getUserName().toCharArray(), actual.get(i).getUserName().toCharArray());
			Assert.assertArrayEquals(expect.get(i).getRoom().name().toCharArray(), actual.get(i).getRoom().name().toCharArray());
			Assert.assertEquals(expect.get(i).getTimeslot().getTimeslot(), actual.get(i).getTimeslot().getTimeslot());
		}
	}

}
