package dojo.progression;

import java.util.List;

public interface ProgressionEngine {
  List<Prescription> nextWeek(WeekLog last, int weeksToMeet);
}
