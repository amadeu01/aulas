package dojo.progression;

/**
 * Training phase by weeks left until the meet.
 * Sealed + records + exhaustive switch: all final in Java 21.
 */
sealed interface Phase {
  record Accumulation()    implements Phase {}
  record Intensification() implements Phase {}
  record Taper()           implements Phase {}
  record MeetWeek()        implements Phase {}

  static Phase of(int weeks) {
    if (weeks <= 1) return new MeetWeek();
    if (weeks <= 2) return new Taper();
    if (weeks <= 8) return new Intensification();
    return new Accumulation();
  }

  /** Volume relative to the accumulation block. */
  static double volumeFactor(Phase p) {
    return switch (p) {
      case Accumulation a    -> 1.00;
      case Intensification i -> 0.85;
      case Taper t           -> 0.70;
      case MeetWeek m        -> 0.50;
      // Java 22+: case Taper _ -> 0.70;
    };
  }

  /** No heavy deadlift in meet week. */
  static boolean allowsHeavy(Phase p, Lift lift) {
    return !(p instanceof MeetWeek
        && lift == Lift.DEADLIFT);
  }
}
