package dojo.progression;

/** Port: how many weeks until the athlete's next meet. */
public interface MeetCalendar {
  int weeksToMeet(String athleteId);
}
