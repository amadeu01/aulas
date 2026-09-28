# Roteiro do facilitador

Plano da aula de 2 horas. O texto completo de cada slide, com as notas do apresentador, está em [slides-e-notas.md](slides-e-notas.md).

## Antes da aula

- [ ] Projeto clonado e `mvn test` verde em todas as máquinas, na tag `inicio`
- [ ] Timer visível de 5 minutos (troca de piloto)
- [ ] `TEST-LIST.md` aberto num lugar que todos veem
- [ ] Conferir versões do JUnit, AssertJ, jqwik e PIT no `pom.xml`
- [ ] Ensaiar as três demonstrações da parte de robustez (seção abaixo)

## Agenda

| Horário | Bloco | Objetivo |
|---|---|---|
| 0:00 · 10 min | Abertura | Aquecimento, acordos do dojo, explicar que powerlifting é só pretexto |
| 0:10 · 15 min | Ciclos do TDD | Os 4 ciclos de Uncle Bob (nano, micro, milli, primário), dois caminhos até o verde, Java 21/24 e setup |
| 0:25 · 15 min | Do pedido ao teste | Ticket ambíguo, perguntas ao produto, critérios, arquitetura, vocabulário de testes (Fowler) e entrada/saída (given/when/then) |
| 0:40 · 60 min | Dojo | Planejar os testes e 5 rodadas de código |
| 1:40 · 10 min | Robustez | Bordas, inválidos, propriedades, mutação e rastreabilidade |
| 1:50 · 10 min | Retro | Manter, ajustar, levar |

Se atrasar: a parte de robustez vira só a demonstração de mutação (5 min) e propriedades ficam como desafio.

## Acordos do dojo

- Troca de piloto a cada 5 minutos; o copiloto de agora é o próximo piloto
- Nenhuma linha de produção sem um teste vermelho
- A plateia pergunta a qualquer hora, mas só sugere código com a barra verde
- O erro é do grupo, nunca de quem digita
- Commit a cada barra verde

## Dojo · 60 minutos

Cada rodada tem um slide de tarefa e um de **estado esperado**. Só mostre o estado esperado quando a turma chegar lá ou se a rodada travar. Se o tempo acabar, faça checkout da tag da rodada e siga.

| Rodada | Tempo | Entrega | Checkpoint | Tag |
|---|---|---|---|---|
| 1 · Planejamento | 8 min | `TEST-LIST.md` completo | A turma concorda com a ordem | `inicio` |
| 2 · Anilhas | 9 min | `Plates` + 3 testes | Fake it → triangulação → genérico | `rodada-2-anilhas` |
| 3 · RPE | 11 min | `RpeRule`, `Loads`, teste parametrizado | O bug do `double` apareceu e foi corrigido | `rodada-3-rpe` |
| 4 · Fadiga | 8 + 2 min | `FatigueRule`, `ReadinessRule` | Teste da borda de 30% | `rodada-4-fadiga` |
| 5 · Fases | 8 min | `Phase` selada + switch | Terra fora da semana da prova | `rodada-5-fases` |
| 6 · Colaboradores | 10 min | `WeeklyPlanService` e os 5 dublês | Nenhum dublê do SUT | `rodada-6-colaboradores` |
| Ciclo primário | 4 min | Revisão das fronteiras | Regras sem JUnit, UI ou banco | `rodada-6-colaboradores` |

### Rodada 1 · Planejamento (8 min)

1. 0–2 min: leitura silenciosa dos critérios de aceitação e das fases; cada um anota exemplos.
2. 2–6 min: o piloto digita no `TEST-LIST.md` o que a turma dita, uma seção por regra.
3. 6–8 min: a turma ordena do mais simples ao mais difícil. O primeiro deve ser o arredondamento.

Estado esperado: cerca de 14 itens, com as bordas (102,5 exato, 30% exatos, 8 e 3 semanas).

| Pergunta provável | Resposta |
|---|---|
| Por que não escrever todos os testes já no código? | Lei 2: um teste vermelho por vez. Com dez vermelhos você não sabe qual passo quebrou o quê. A lista é um plano (Kent Beck). |
| E se esquecermos um caso? | A lista é viva. Caso novo vai para a lista, não para o código na hora. |
| Não é design antecipado demais? | Planejamos exemplos (o quê), não implementação (como). |
| Por que nomes em inglês? | Convenção de código. O texto em português pode ir no `@DisplayName`. |

### Rodada 2 · Anilhas (9 min)

1. Vermelho de compilação: `Plates` não existe. Não compilar é falhar (lei 2).
2. Vermelho de asserção: esperado 100.0, veio 0.0.
3. Verde com fake it: `return 100.0;`
4. Triangular: 104.9 → 102.5. Implementação: `Math.floor(kg / 2.5) * 2.5`.
5. `keepsExactMultiple` e `rejectsNegativeLoad`. Refatorar: constante `INCREMENT`, construtor privado.

| Pergunta provável | Resposta |
|---|---|
| Por que não escrever o `floor` direto? | Pode (implementação óbvia), mas hoje treinamos a marcha mais baixa. |
| Por que `static`? | Função pura, sem estado. |
| `keepsExactMultiple` já nasceu verde. E aí? | Não guiou código, mas documenta a borda. Mantemos. |
| Por que não `BigDecimal` já? | Nenhum teste pediu ainda. Vai pedir na próxima rodada. |

### Rodada 3 · RPE (11 min)

1. `@CsvSource` com uma linha só (`9.0 → 100.0`). Fake it: `return load;`
2. Linha `10.0` → vermelho → regra de −5%.
3. Linhas `7.0` e `8.0`. **A linha do RPE 8 fica vermelha** mesmo com a lógica certa: `100 * 1.025` em `double` é `102.49999999999999`, e o arredondamento para baixo leva a `100.0`.
4. A turma investiga. Correção: extrair `Loads.scale` com `BigDecimal.valueOf`. O teste não muda.

| Pergunta provável | Resposta |
|---|---|
| Por que não `isCloseTo` com tolerância? | Esconderia o bug: 100 em vez de 102,5 está errado para o usuário, não é ruído. |
| Parametrizado não viola "um teste por vez"? | Não, se você adiciona uma linha por vez. |
| Por que `BigDecimal.valueOf` e não `new BigDecimal(1.025)`? | O construtor com `double` carrega o erro binário; `valueOf` usa a representação decimal. |

### Rodada 4 · Fadiga (8 min + 2 de discussão)

1. `velocityLossAbove30RemovesOneSet`. Fake it: `return 4;`
2. A turma escreve a borda (30% mantém 5 séries). Implementação com `>`.
3. Bônus: e se `sets` for 1? `Math.max(1, sets - 1)`.
4. `ReadinessRule` com dois testes; destaque o reuso de `Loads.scale`.

| Pergunta provável | Resposta |
|---|---|
| Mesma classe do RPE ou outra? | Outra. Misturar faz os testes de RPE precisarem de dados de velocidade: acoplamento. |
| `>` ou `>=`? | O teste da borda decide e documenta. |
| O `SetLog` enorme no teste incomoda | Cheiro real. Solução: builder de teste. Anote para a refatoração. |

Discussão (2 min): "treino fácil pelo RPE, mas a velocidade caiu 40%. Quem decide?" É decisão de produto; o teste registra a resposta. Você faz o papel de produto.

### Rodada 5 · Fases (8 min)

Entregue o esqueleto de `Phase` pronto (records e switch); o foco é o comportamento.

1. `taperVolumeIs70Percent`. Fake it: `return new Taper();`
2. `Phase.of` parametrizado: 9, 8, 3, 2 e 1 semanas. As bordas 8 e 3 são onde erram.
3. `meetWeekDropsHeavyDeadlift` → `Phase.allowsHeavy`.
4. Experimento: adicione `record Deload() implements Phase {}` e veja o switch parar de compilar.

| Pergunta provável | Resposta |
|---|---|
| Por que `sealed` e não `enum`? | Cada fase pode carregar dados próprios no futuro, e o switch continua exaustivo. |
| Funciona em Java 21? | Sim. Só o `_` precisa de Java 22+ (ou `--enable-preview` no 21). |
| Por que não um `default` no switch? | Mataria a checagem de exaustividade. |

### Rodada 6 · Colaboradores (10 min)

Antes: cole o `RuleBasedEngine` pronto (tag `motor`) e entregue as interfaces `TrainingLog`, `MeetCalendar` e `CoachNotifier`. Vocabulário em [dubles-de-teste.md](dubles-de-teste.md).

1. `plansNextWeekFromLastWeekAndMeetDate`: **fake** (`InMemoryTrainingLog`), **stub** (`athlete -> 5`), **dummy** (notifier que lança erro) e o motor **real**.
2. `meetWeekFromCalendarDropsHeavyDeadlift`: só troca o stub para 1.
3. `conflictingSignalsAlertTheCoach_withSpy`: verificação de estado.
4. `conflictingSignalsAlertTheCoach_withMock`: verificação de comportamento com Mockito. Compare com o spy.

| Pergunta provável | Resposta |
|---|---|
| Por que o motor não é dublê? | É puro, rápido e já tem testes. Fowler (clássico): use o real quando é fácil. |
| Por que o dummy lança erro? | Prova que, sem conflito, o treinador não é chamado. |
| Spy ou mock? | Os dois provam a regra. O spy tolera refatoração; o mock prende à forma da chamada. |
| Fake it e fake são a mesma coisa? | Não. Fake it é provisório no código de produção; fake é um dublê completo no código de teste. |

### Ciclo primário (4 min)

Pausa para olhar as fronteiras: testes → núcleo (regras puras) ← bordas (app, planilha, sensor, banco). Pergunta: "se a velocidade vier de um relógio amanhã, quais testes mudam?" Resposta esperada: nenhum teste de regra; só nasce um adaptador novo.

Retome o `RuleBasedEngine` e o teste de aceitação: `SetLog(SQUAT, 150, 5, 3, 8.0, 35.0)`, prontidão 4, 5 semanas → `Prescription(SQUAT, 152.5, 4, 3)`.

## Robustez · 10 minutos

Detalhes em [robustez.md](robustez.md). As três demonstrações foram conferidas contra o código deste repositório:

1. **Bug do `double`:** troque `Loads.scale(...)` por `Plates.roundDown(loadKg * factorFor(rpe))` no `RpeRule`. A linha do RPE 8 falha: esperado 102.5, veio 100.0.
2. **Propriedade:** adicione `if (rpe < 7.5) return 1.10;` logo após o primeiro `if` do `factorFor`. Todos os testes de exemplo continuam verdes; `higherRpeNeverSuggestsMoreLoad` falha.
3. **Mutação:** troque `>` por `>=` no `FatigueRule`. Só o teste da borda de 30% falha. Apague esse teste e rode o PIT: o mutante sobrevive.

## Retro · 10 minutos

Um post-it por pergunta: **manter** (o que funcionou?), **ajustar** (o que doeu?), **levar** (o que você faz na segunda-feira?). Volte às mãos levantadas da abertura.
