package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * One test class, every kind of test double (Martin Fowler, "Mocks Aren't Stubs").
 * SUT: WeeklyPlanService. Collaborators: TrainingLog, MeetCalendar, CoachNotifier, ProgressionEngine.
 * The engine is REAL (classical TDD: use real objects when practical, doubles only at the edges).
 */
class WeeklyPlanServiceTest {

  // FIXTURE: everything the tests need, created in setup
  private InMemoryTrainingLog log;                                 // FAKE
  private final ProgressionEngine engine = new RuleBasedEngine();  // real collaborator

  // DUMMY: must be passed, must never be used
  private final CoachNotifier dummyNotifier = (athlete, message) -> {
    throw new AssertionError("dummy: should never be called");
  };

  @BeforeEach
  void setUp() {
    log = new InMemoryTrainingLog();
  }

  private static WeekLog week(double rpe, double velocityLossPct) {
    return new WeekLog(List.of(new SetLog(SQUAT, 150, 5, 3, rpe, velocityLossPct)), 4);
  }

  // O que testa: o serviço junta as bordas e o motor: busca a semana passada, pergunta quantas
  // semanas faltam para a prova e devolve o plano.
  // Como: o FAKE (InMemoryTrainingLog) guarda o treino, o STUB (athlete -> 5) responde pelo
  // calendário e o DUMMY do treinador falha se for chamado (sem conflito, ninguém é avisado).
  // Verificação de estado: conferimos o plano devolvido.
  @Test
  void plansNextWeekFromLastWeekAndMeetDate() {
    // given (Fowler: setup, the fixture)
    log.record("ana", week(8.0, 35.0));
    MeetCalendar fiveWeeksOut = athlete -> 5;                       // STUB: canned answer
    var service = new WeeklyPlanService(log, fiveWeeksOut, dummyNotifier, engine);

    // when (Fowler: exercise)
    var plan = service.planFor("ana");

    // then (Fowler: verify; state verification)
    assertThat(plan).containsExactly(new Prescription(SQUAT, 152.5, 4, 3));
  }

  // O que testa: a resposta do calendário chega até o motor. Se o calendário diz "semana da
  // prova", o terra sai do plano.
  // Como: STUB que responde 1 semana; conferimos que só o agachamento ficou.
  @Test
  void meetWeekFromCalendarDropsHeavyDeadlift() {
    log.record("ana", new WeekLog(List.of(
        new SetLog(SQUAT, 150, 3, 2, 9.0, 10.0),
        new SetLog(DEADLIFT, 200, 3, 2, 9.0, 10.0)), 4));
    MeetCalendar meetWeek = athlete -> 1;                           // STUB
    var service = new WeeklyPlanService(log, meetWeek, dummyNotifier, engine);

    var plan = service.planFor("ana");

    assertThat(plan).extracting(Prescription::lift).containsExactly(SQUAT);
  }

  // O que testa: com sinais conflitantes (RPE 7 e 35% de perda), o treinador é avisado.
  // Como: SPY. O SpyCoachNotifier só grava os avisos numa lista; depois da chamada, olhamos a
  // lista (verificação de estado).
  @Test
  void conflictingSignalsAlertTheCoach_withSpy() {
    log.record("ana", week(7.0, 35.0));
    var spy = new SpyCoachNotifier();                               // SPY
    var service = new WeeklyPlanService(log, athlete -> 5, spy, engine);

    service.planFor("ana");

    // state verification on what the spy recorded
    assertThat(spy.alerts).containsExactly("ana: conflicting signals on SQUAT");
  }

  // O que testa: o mesmo comportamento do teste anterior, agora com um MOCK do Mockito.
  // Como: verify confere que alert foi chamado com esses argumentos, e verifyNoMoreInteractions
  // que não houve nenhuma outra chamada (verificação de comportamento). Compare com o spy: o
  // mock fica preso à forma exata da chamada.
  @Test
  void conflictingSignalsAlertTheCoach_withMock() {
    log.record("ana", week(7.0, 35.0));
    CoachNotifier mockNotifier = mock(CoachNotifier.class);         // MOCK
    var service = new WeeklyPlanService(log, athlete -> 5, mockNotifier, engine);

    service.planFor("ana");

    // behavior verification: the expected call happened, and nothing else
    verify(mockNotifier).alert("ana", "conflicting signals on SQUAT");
    verifyNoMoreInteractions(mockNotifier);
  }
}
