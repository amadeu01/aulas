package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PlatesTest {

  @Test
  void roundsDownToNearestMultipleOf2_5() {
    assertThat(Plates.roundDown(101.2))
        .isEqualTo(100.0);
  }

  @Test
  void keepsExactMultiple() {
    assertThat(Plates.roundDown(102.5))
        .isEqualTo(102.5);
  }

  @Test
  void rejectsNegativeLoad() {
    assertThatThrownBy(() -> Plates.roundDown(-1))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
