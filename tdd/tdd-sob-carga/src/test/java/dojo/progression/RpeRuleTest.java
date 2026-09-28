package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RpeRuleTest {

  @Test
  void easySessionIncreasesLoadBy5Percent() { // "easy" = RPE <= 7 (agreed with product)
    assertThat(RpeRule.next(100.0, 7.0)).isEqualTo(105.0);
  }

  // The "8.0" row is the one that exposes the double bug:
  // 100 * 1.025 == 102.49999999999999 -> rounds down to 100.0 without Loads.scale.
  @ParameterizedTest
  @CsvSource({
    "7.0,  100.0, 105.0",
    "8.0,  100.0, 102.5",
    "9.0,  100.0, 100.0",
    "10.0, 100.0,  95.0"
  })
  void adjustsLoadByRpe(double rpe, double load,
                        double expected) {
    assertThat(RpeRule.next(load, rpe))
        .isEqualTo(expected);
  }
}
