package dojo.progression;

/**
 * Product decision for PROG-42: when RPE says "easy" but velocity says "tired",
 * the rules still add up, and the coach is alerted.
 */
final class SignalConflict {
  private SignalConflict() {}

  static boolean detect(SetLog s) {
    return s.rpe() <= 7.0
        && s.velocityLossPct() > FatigueRule.LIMIT_PCT;
  }
}
