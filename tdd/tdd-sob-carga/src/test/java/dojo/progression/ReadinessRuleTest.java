package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReadinessRuleTest {

  @Test
  void lowReadinessCutsLoadBy5Percent() {
    assertThat(ReadinessRule.adjust(150.0, 2))
        .isEqualTo(142.5);
  }

  @Test
  void goodReadinessKeepsLoad() {
    assertThat(ReadinessRule.adjust(150.0, 4))
        .isEqualTo(150.0);
  }
}
