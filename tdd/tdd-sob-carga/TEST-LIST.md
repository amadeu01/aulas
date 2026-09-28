# TEST-LIST.md

Rodada 1: a turma decide todos os testes antes de escrever código.
A lista é um plano, não código: um teste vermelho por vez (lei 2 do TDD).
Marque `[x]` a cada teste verde. Caso novo que aparecer vai para a lista, não direto para o código.

## Plates
- [x] roundsDown: 101.2 -> 100.0
- [x] keepsExactMultiple: 102.5 -> 102.5
- [x] rejectsNegativeLoad

## RpeRule (target RPE 8)
- [x] rpe7:  +5%   (100 -> 105.0)
- [x] rpe8:  +2.5% (100 -> 102.5)
- [x] rpe9:  keeps (100 -> 100.0)
- [x] rpe10: -5%   (100 ->  95.0)

## FatigueRule / ReadinessRule
- [x] velocityLoss 35%: sets 5 -> 4
- [x] velocityLoss 30%: sets stay 5
- [x] never below one set
- [x] readiness 2: load -5%
- [x] readiness 4: load unchanged

## Phase
- [x] 9 weeks: Accumulation (1.00)
- [x] 8..3 weeks: Intensification (0.85)
- [x] 2 weeks: Taper (0.70)
- [x] 1 week: MeetWeek (0.50), no heavy deadlift

## Engine (acceptance, ticket PROG-42)
- [ ] zero sets: empty plan (ZOM)
- [ ] RPE 8 + 35% velocity loss: 150 x 5 -> 152.5 x 4
- [ ] meet week drops heavy deadlift

## WeeklyPlanService (collaborators and test doubles)
- [ ] conflict: easy RPE + velocity loss > 30%
- [ ] no conflict: target RPE + velocity loss > 30%
- [ ] plans from last week (fake) and weeks to meet (stub), notifier unused (dummy)
- [ ] meet week from calendar drops heavy deadlift (stub)
- [ ] conflicting signals alert the coach (spy)
- [ ] conflicting signals alert the coach (mock)

## Robustness
- [ ] property: roundDown stays within one plate
- [ ] property: higher RPE never suggests more load

## Ideas for later (not done in class)
- [ ] reject RPE outside 1..10
- [ ] reject readiness outside 1..5
- [ ] apply phase volume factor to the block's base volume
