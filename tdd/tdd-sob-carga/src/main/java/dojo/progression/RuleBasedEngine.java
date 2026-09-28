package dojo.progression;

import java.util.List;

/** Composes the rules. Each rule is tested on its own; this class only wires them. */
public final class RuleBasedEngine implements ProgressionEngine {

  @Override
  public List<Prescription> nextWeek(WeekLog last, int weeksToMeet) {
    var phase = Phase.of(weeksToMeet);
    return last.sets().stream()
        .filter(s -> Phase.allowsHeavy(phase, s.lift()))
        .map(s -> prescribe(s, last.readiness()))
        .toList();
  }

  private Prescription prescribe(SetLog s, int readiness) {
    var load = RpeRule.next(s.loadKg(), s.rpe());
    load = ReadinessRule.adjust(load, readiness);
    var sets = FatigueRule.nextSets(s);
    return new Prescription(s.lift(), load, sets, s.reps());
  }
}
