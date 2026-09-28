package dojo.progression;

import java.util.List;

/** Application service: talks to the edges (ports) and delegates the rules to the engine. */
public final class WeeklyPlanService {
  private final TrainingLog log;
  private final MeetCalendar calendar;
  private final CoachNotifier notifier;
  private final ProgressionEngine engine;

  public WeeklyPlanService(TrainingLog log, MeetCalendar calendar,
                           CoachNotifier notifier, ProgressionEngine engine) {
    this.log = log;
    this.calendar = calendar;
    this.notifier = notifier;
    this.engine = engine;
  }

  public List<Prescription> planFor(String athleteId) {
    var last = log.lastWeek(athleteId);
    last.sets().stream()
        .filter(SignalConflict::detect)
        .forEach(s -> notifier.alert(athleteId, "conflicting signals on " + s.lift()));
    return engine.nextWeek(last, calendar.weeksToMeet(athleteId));
  }
}
