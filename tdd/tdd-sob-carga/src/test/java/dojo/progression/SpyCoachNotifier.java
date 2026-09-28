package dojo.progression;

import java.util.ArrayList;
import java.util.List;

/** SPY: answers nothing, but records how it was called so the test can check it afterwards. */
final class SpyCoachNotifier implements CoachNotifier {
  final List<String> alerts = new ArrayList<>();

  @Override
  public void alert(String athleteId, String message) {
    alerts.add(athleteId + ": " + message);
  }
}
