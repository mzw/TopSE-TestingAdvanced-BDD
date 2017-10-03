package jp.mzw.topse.lecture.testing_advanced;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RoomBooking {

	private List<Booking> bookings;

	public RoomBooking() {
		this.bookings = new ArrayList<>();
	}

	public void clearAllBookings() {
		this.bookings = new ArrayList<>();
	}

	public boolean book(final String userName, final Room room, final Timeslot timeslot, final Date date) {
		Booking candidate = new Booking(userName, room, timeslot, date);
		if (validate(candidate)) {
			this.bookings.add(candidate);
			return true;
		}
		return false;
	}

	public List<Booking> getAllBookings() {
		return this.bookings;
	}

	public List<Room> searchEmptyRooms(final Timeslot timeslot) {
		final List<Room> empties = new ArrayList<>();
		for (final Room room : Room.values()) {
			boolean booked = false;
			for (final Booking booking : this.bookings) {
				if (!isSameDay(new Date(), booking.getDate())) {
					continue;
				}
				if (booking.getRoom().equals(room) && booking.getTimeslot().isSame(timeslot)) {
					booked = true;
					break;
				}
			}
			if (!booked) {
				empties.add(room);
			}
		}
		return empties;
	}

	public boolean cancel(final Room room, final Timeslot timeslot) {
		for (final Booking booking : this.bookings) {
			if (!isSameDay(new Date(), booking.getDate())) {
				continue;
			}
			if (booking.getRoom().equals(room) && booking.getTimeslot().isSame(timeslot)) {
				this.bookings.remove(booking);
				return true;
			}
		}
		return false;
	}

	public String getDisplayContent() {
		final StringBuilder content = new StringBuilder();
		String delim = "";
		for (final Booking booking : this.bookings) {
			content.append(delim).append(booking.getRoom().name()).append(" is booked by ").append(booking.getUserName()).append(" from ")
					.append(booking.getTimeslot().getDisplayContent());
			delim = "\n";
		}
		return content.toString();
	}

	private boolean validate(final Booking candidate) {
		if (!isSameDay(new Date(), candidate.getDate())) {
			return false;
		}
		for (final Booking booking : this.bookings) {
			if (!isSameDay(booking.getDate(), candidate.getDate())) {
				continue;
			}
			if (booking.getTimeslot().isSame(candidate.getTimeslot())) {
				return false;
			}
		}
		return true;
	}

	private static boolean isSameDay(final Date date1, final Date date2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(date1).equals(format.format(date2));
	}

	public static enum Room {
		Room1, Room2, Room3, Room4
	}

	public static class Booking {
		private final String userName;
		private final Room room;
		private final Timeslot timeslot;
		private final Date date;

		public Booking(final String userName, final Room room, final Timeslot timeslot, final Date date) {
			this.userName = userName;
			this.room = room;
			this.timeslot = timeslot;
			this.date = date;
		}

		public String getUserName() {
			return this.userName;
		}

		public Room getRoom() {
			return this.room;
		}

		public Date getDate() {
			return this.date;
		}

		public Timeslot getTimeslot() {
			return this.timeslot;
		}
	}

	public static class Timeslot {
		private final int timeslot;

		public Timeslot(final int timeslot) {
			if (timeslot < 0 || 23 < timeslot) {
				throw new IllegalArgumentException();
			}
			this.timeslot = timeslot;
		}

		public int getTimeslot() {
			return this.timeslot;
		}

		public boolean isSame(final Timeslot timeslot) {
			return this.timeslot == timeslot.getTimeslot();
		}

		public String getDisplayContent() {
			return this.timeslot + ":00";
		}

		public static Timeslot parse(String timeslot) throws ParseException {
			SimpleDateFormat format = new SimpleDateFormat("HH:MM");
			Date time = format.parse(timeslot);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			return new Timeslot(calendar.get(Calendar.HOUR_OF_DAY));
		}
	}

}
