package dojo.progression;

/** Port: tells the coach when something needs a human decision. */
public interface CoachNotifier {
  void alert(String athleteId, String message);
}
