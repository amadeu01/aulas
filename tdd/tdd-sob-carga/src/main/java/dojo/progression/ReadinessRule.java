package dojo.progression;

/** Readiness 1..5 reported before training. 2 or less cuts the load by 5%. */
final class ReadinessRule {
  static final int LOW_READINESS = 2;
  static final double LOW_READINESS_FACTOR = 0.95;

  private ReadinessRule() {}

  static double adjust(double kg, int score) {
    return score <= LOW_READINESS
        ? Loads.scale(kg, LOW_READINESS_FACTOR)
        : kg;
  }
}
