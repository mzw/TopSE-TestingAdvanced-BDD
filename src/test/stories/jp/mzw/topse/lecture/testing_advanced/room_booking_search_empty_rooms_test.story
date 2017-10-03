Narrative: Meeting-room booking system

Scenario: searching empty meeting room

Given the system is ready and Room1 is booked from 10:00
When searching empty rooms from 10:00
Then the system returns:
|room|
|Room2|
|Room3|
|Room4|
