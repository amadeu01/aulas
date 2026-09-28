package dojo.progression;

import java.util.HashMap;
import java.util.Map;

/** FAKE (Fowler/Meszaros): a working implementation with a shortcut (a HashMap, no database). */
final class InMemoryTrainingLog implements TrainingLog {
  private final Map<String, WeekLog> weeks = new HashMap<>();

  void record(String athleteId, WeekLog week) {
    weeks.put(athleteId, week);
  }

  @Override
  public WeekLog lastWeek(String athleteId) {
    return weeks.get(athleteId);
  }
}
