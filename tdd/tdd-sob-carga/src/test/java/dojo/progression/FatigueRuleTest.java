package dojo.progression;

import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FatigueRuleTest {

  @Test
  void velocityLossAbove30RemovesOneSet() {
    // given
    var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0);
    // when
    var sets = FatigueRule.nextSets(last);
    // then
    assertThat(sets).isEqualTo(4);
  }

  @Test
  void velocityLossOfExactly30KeepsSets() {
    var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 30.0);
    assertThat(FatigueRule.nextSets(last)).isEqualTo(5);
  }

  @Test
  void neverGoesBelowOneSet() {
    var last = new SetLog(SQUAT, 150, 1, 3, 8.0, 45.0);
    assertThat(FatigueRule.nextSets(last)).isEqualTo(1);
  }
}
