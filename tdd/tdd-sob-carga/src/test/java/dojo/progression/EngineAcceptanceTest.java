package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

/** Ticket PROG-42, end to end: the rules combined through the public contract. */
class EngineAcceptanceTest {

  private final ProgressionEngine engine = new RuleBasedEngine();

  // O que testa: ZOM, o caso "zero". Sem treino na semana passada, o plano vem vazio.
  // Como: WeekLog com lista vazia; o motor não pode inventar exercício nem quebrar.
  @Test
  void emptyWeekGivesEmptyPlan() { // ZOM: zero
    var next = engine.nextWeek(new WeekLog(List.of(), 4), 5);

    assertThat(next).isEmpty();
  }

  // O que testa: o ticket PROG-42 de ponta a ponta, com as regras combinadas no motor.
  // Como: ZOM "um", um exercício só. RPE 8 sobe 2,5% e arredonda (152,5 kg), prontidão 4 não
  // corta e 35% de perda tira uma série (5 -> 4). Conferimos a prescrição inteira de uma vez.
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

  // O que testa: ZOM "muitos". Com dois exercícios na semana da prova, o motor tira só o terra.
  // Como: agachamento e terra entram, com 1 semana até a prova; extracting pega só o movimento
  // de cada prescrição e conferimos que sobrou apenas SQUAT.
  @Test
  void meetWeekDropsHeavyDeadlift() { // ZOM: many
    var last = new WeekLog(List.of(
        new SetLog(SQUAT, 150, 3, 2, 9.0, 10.0),
        new SetLog(DEADLIFT, 200, 3, 2, 9.0, 10.0)), 4);

    var next = engine.nextWeek(last, 1);

    assertThat(next).extracting(Prescription::lift).containsExactly(SQUAT);
  }
}
