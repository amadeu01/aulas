package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

/** Ticket PROG-42, end to end: the rules combined through the public contract. */
class EngineAcceptanceTest {

  private final ProgressionEngine engine = new RuleBasedEngine();

  @Test
  void emptyWeekGivesEmptyPlan() { // ZOM: zero
    var next = engine.nextWeek(new WeekLog(List.of(), 4), 5);

    assertThat(next).isEmpty();
  }

  @Test
  void targetSessionWithHighFatigueRaisesLoadAndDropsOneSet() { // ZOM: one
    // given
    var last = new WeekLog(
        List.of(new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0)), 4);

    // when
    var next = engine.nextWeek(last, 5);

    // then: RPE 8: 150 * 1.025 = 153.75 -> 152.5; readiness 4 keeps it; 35% loss: 5 -> 4 sets
    assertThat(next).containsExactly(new Prescription(SQUAT, 152.5, 4, 3));
  }

  @Test
  void meetWeekDropsHeavyDeadlift() { // ZOM: many
    var last = new WeekLog(List.of(
        new SetLog(SQUAT, 150, 3, 2, 9.0, 10.0),
        new SetLog(DEADLIFT, 200, 3, 2, 9.0, 10.0)), 4);

    var next = engine.nextWeek(last, 1);

    assertThat(next).extracting(Prescription::lift).containsExactly(SQUAT);
  }
}
