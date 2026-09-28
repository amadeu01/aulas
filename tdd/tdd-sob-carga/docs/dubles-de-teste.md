# Fixture, SUT e dublês de teste

Vocabulário baseado em dois textos de Martin Fowler:

- [Mocks Aren't Stubs](https://martinfowler.com/articles/mocksArentStubs.html)
- [TestDouble](https://martinfowler.com/bliki/TestDouble.html) (o termo "test double" é de Gerard Meszaros)

Todos os exemplos estão em `src/test/java/dojo/progression/WeeklyPlanServiceTest.java` (tag `rodada-6-colaboradores`).

## Anatomia de um teste

| Termo | O que é | No projeto |
|---|---|---|
| **SUT** (System Under Test) | O objeto que o teste está testando | `WeeklyPlanService` |
| **Colaborador** | Outro objeto de que o SUT depende | `TrainingLog`, `MeetCalendar`, `CoachNotifier`, `ProgressionEngine` |
| **Fixture** | Tudo o que o teste monta antes de rodar: SUT, colaboradores e dados | Os campos da classe de teste e o `@BeforeEach` |

Todo teste tem quatro fases: **setup** (monta a fixture), **exercise** (chama o SUT), **verify** (confere o resultado) e **teardown** (limpa; em teste de unidade, quase nunca há o que limpar). É a mesma ideia do Arrange / Act / Assert.

Fixture **não** é um tipo de dublê. A fixture é o cenário inteiro; dublês são algumas das peças que podem estar nele.

```java
@Test
void plansNextWeekFromLastWeekAndMeetDate() {
  // 1. setup: the fixture
  log.record("ana", week(8.0, 35.0));
  MeetCalendar fiveWeeksOut = athlete -> 5;
  var service = new WeeklyPlanService(log, fiveWeeksOut, dummyNotifier, engine); // SUT

  // 2. exercise
  var plan = service.planFor("ana");

  // 3. verify
  assertThat(plan).containsExactly(new Prescription(SQUAT, 152.5, 4, 3));
  // 4. teardown: nothing to clean, JUnit discards it
}
```

## Os cinco dublês

"Test double" é o nome geral para qualquer objeto que substitui um colaborador real num teste, como o dublê do cinema.

| Dublê | Definição (Fowler) | No projeto |
|---|---|---|
| **Dummy** | Passado como parâmetro, mas nunca usado | `dummyNotifier`: lança erro se for chamado |
| **Stub** | Dá respostas prontas às chamadas do teste | `MeetCalendar fiveWeeksOut = athlete -> 5` |
| **Fake** | Implementação que funciona, com um atalho que não serve para produção | `InMemoryTrainingLog`: um `HashMap` no lugar do banco |
| **Spy** | Um stub que também grava como foi chamado | `SpyCoachNotifier`: guarda os alertas numa lista |
| **Mock** | Programado com as chamadas que deve receber | `mock(CoachNotifier.class)` + `verify(...)` (Mockito) |

```java
// DUMMY: must be passed, must never be used
private final CoachNotifier dummyNotifier = (athlete, message) -> {
  throw new AssertionError("dummy: should never be called");
};

// STUB: canned answer
MeetCalendar meetWeek = athlete -> 1;

// FAKE: working implementation with a shortcut
final class InMemoryTrainingLog implements TrainingLog {
  private final Map<String, WeekLog> weeks = new HashMap<>();
  void record(String athleteId, WeekLog week) { weeks.put(athleteId, week); }
  public WeekLog lastWeek(String athleteId) { return weeks.get(athleteId); }
}

// SPY: records how it was called
final class SpyCoachNotifier implements CoachNotifier {
  final List<String> alerts = new ArrayList<>();
  public void alert(String athleteId, String message) { alerts.add(athleteId + ": " + message); }
}

// MOCK: expectations about the calls
CoachNotifier mockNotifier = mock(CoachNotifier.class);
```

## Verificar estado ou comportamento

| | Verificação de estado | Verificação de comportamento |
|---|---|---|
| Pergunta | O que ficou registrado no fim? | A chamada certa aconteceu? |
| Dublê típico | Stub, fake, spy | Mock |
| No projeto | `assertThat(spy.alerts).containsExactly("ana: conflicting signals on SQUAT")` | `verify(mockNotifier).alert("ana", "conflicting signals on SQUAT")` |
| Custo | Mais tolerante a refatoração | Presa à forma exata das chamadas |

Fowler descreve duas escolas:

- **Clássica:** usa objetos reais sempre que possível e dublês só quando o real é difícil de usar (banco, rede, e-mail). Fowler se declara clássico.
- **Mockista:** usa mocks para qualquer colaborador com comportamento interessante. Ajuda a desenhar de fora para dentro, mas acopla os testes à implementação.

Este dojo é clássico: as regras e o `RuleBasedEngine` são **reais** nos testes do serviço; só as bordas (`TrainingLog`, `MeetCalendar`, `CoachNotifier`) viram dublês.

## Três regras práticas

1. **Dublê substitui colaborador, nunca o SUT.** Mockar a classe que você está testando é testar o mock: o teste fica verde para sempre e não guia nada.
2. **Use o objeto real quando ele é rápido e já tem testes.** Mockar o motor faria o teste do serviço passar mesmo com as regras erradas.
3. **Um dummy que lança erro é melhor que um dummy silencioso:** ele prova que aquele caminho não foi usado.

## "Fake it" não é "fake"

Os nomes confundem. **Fake it** (Kent Beck) é uma implementação provisória no **código de produção**, como `return 100.0;`, que o próximo teste derruba. **Fake** (Meszaros/Fowler) é um dublê completo que vive no **código de teste**, como o `InMemoryTrainingLog`.
