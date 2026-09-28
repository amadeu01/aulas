package dojo.progression;

/** Loads must fit real plates: round down to the nearest multiple of 2.5 kg. */
final class Plates {
  static final double INCREMENT = 2.5;

  private Plates() {}

  static double roundDown(double kg) {
    if (kg < 0) {
      throw new IllegalArgumentException("load must be >= 0");
    }
    return Math.floor(kg / INCREMENT) * INCREMENT;
  }
}
