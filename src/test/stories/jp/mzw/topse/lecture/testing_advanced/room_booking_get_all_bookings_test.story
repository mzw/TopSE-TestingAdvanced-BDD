Narrative: Meeting-room booking system

Scenario: getting all bookings

Given the system is ready and Room1 is booked by George from 10:00
When confirming all bookings
Then the system returns:
|user|room|timeslot|
|George|Room1|10:00|
