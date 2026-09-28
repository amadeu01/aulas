package dojo.progression;

import java.math.BigDecimal;

/**
 * Scales a load by a factor without binary floating point surprises.
 * In plain double, 100 * 1.025 == 102.49999999999999, which rounds down to 100.0.
 */
final class Loads {
  private Loads() {}

  static double scale(double kg, double factor) {
    var exact = BigDecimal.valueOf(kg)
        .multiply(BigDecimal.valueOf(factor));
    return Plates.roundDown(exact.doubleValue());
  }
}
