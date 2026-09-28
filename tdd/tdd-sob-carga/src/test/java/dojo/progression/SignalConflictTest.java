package dojo.progression;

import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SignalConflictTest {

  @Test
  void easyRpeWithHighVelocityLossIsAConflict() {
    assertThat(SignalConflict.detect(new SetLog(SQUAT, 150, 5, 3, 7.0, 35.0))).isTrue();
  }

  @Test
  void targetRpeWithHighVelocityLossIsNotAConflict() {
    assertThat(SignalConflict.detect(new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0))).isFalse();
  }
}
