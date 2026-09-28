package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import dojo.progression.Phase.MeetWeek;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhaseTest {

  @Test
  void taperVolumeIs70Percent() {
    assertThat(Phase.volumeFactor(Phase.of(2)))
        .isEqualTo(0.70);
  }

  @ParameterizedTest
  @CsvSource({"9,Accumulation", "8,Intensification",
              "3,Intensification", "2,Taper",
              "1,MeetWeek"})
  void phaseByWeeksToMeet(int weeks, String name) {
    assertThat(Phase.of(weeks).getClass()
        .getSimpleName()).isEqualTo(name);
  }

  @Test
  void meetWeekDropsHeavyDeadlift() {
    var meetWeek = new MeetWeek();
    assertThat(Phase.allowsHeavy(meetWeek, DEADLIFT))
        .isFalse();
  }

  @Test
  void meetWeekKeepsSquat() {
    assertThat(Phase.allowsHeavy(new MeetWeek(), SQUAT))
        .isTrue();
  }
}
