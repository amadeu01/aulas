package dojo.progression;

/** Target RPE is 8: easier sessions go up, harder sessions stay or come down. */
final class RpeRule {
  private RpeRule() {}

  static double next(double loadKg, double rpe) {
    return Loads.scale(loadKg, factorFor(rpe));
  }

  private static double factorFor(double rpe) {
    if (rpe <= 7.0) return 1.05;
    if (rpe <= 8.5) return 1.025;
    if (rpe < 9.5)  return 1.00;
    return 0.95;
  }
}
