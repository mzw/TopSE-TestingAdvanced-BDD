Narrative: Meeting-room booking system

Scenario: canceling meeting room booked

Given the system is ready and Room1 is booked from 10:00
When George cancels Room1 booked from 10:00
Then the system has no booking
