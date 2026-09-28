package dojo.progression;

/** Velocity loss above 30% within a set means too much fatigue: drop one set. */
final class FatigueRule {
  static final double LIMIT_PCT = 30.0;

  private FatigueRule() {}

  static int nextSets(SetLog last) {
    return last.velocityLossPct() > LIMIT_PCT
        ? Math.max(1, last.sets() - 1)
        : last.sets();
  }
}
