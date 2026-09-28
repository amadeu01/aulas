# A lógica está robusta?

Barra verde prova que os exemplos que escolhemos funcionam. Não prova que a lógica está certa. Cinco camadas de confiança, da mais barata à mais rigorosa:

| Camada | O que é | Neste repositório |
|---|---|---|
| 1. Exemplos | Um critério, pelo menos um teste | `*Test.java` |
| 2. Bordas | Os dois lados de cada limite | `velocityLossOfExactly30KeepsSets`, `phaseByWeeksToMeet` (8 e 3) |
| 3. Inválidos | Dado errado é rejeitado com clareza | `rejectsNegativeLoad` (RPE fora de 1 a 10 fica como exercício) |
| 4. Propriedades | Regras que valem para qualquer entrada | `PlatesProperties`, `RpeRuleProperties` (jqwik) |
| 5. Mutação | Uma ferramenta estraga o código; algum teste precisa falhar | PIT configurado no `pom.xml` |

Acima de tudo: o teste de aceitação do ticket inteiro (`EngineAcceptanceTest`).

## Testes de propriedade com jqwik

Exemplos testam o que você imaginou; propriedades procuram o que você não imaginou. O jqwik roda no JUnit Platform, junto com os testes Jupiter. Por padrão gera 1000 entradas por propriedade e, quando acha uma falha, encolhe (shrinking) até o menor exemplo que quebra.

```java
@Property
void roundDownStaysWithinOnePlate(
    @ForAll @DoubleRange(min = 0, max = 500) double kg) {
  double r = Plates.roundDown(kg);
  assertThat(r).isLessThanOrEqualTo(kg);
  assertThat(kg - r).isLessThan(Plates.INCREMENT);
}

@Property
void higherRpeNeverSuggestsMoreLoad(
    @ForAll @DoubleRange(min = 6, max = 10) double a,
    @ForAll @DoubleRange(min = 6, max = 10) double b) {
  Assume.that(a <= b);
  assertThat(RpeRule.next(100, a))
      .isGreaterThanOrEqualTo(RpeRule.next(100, b));
}
```

As propriedades vêm da pergunta ao produto: "o que nunca pode acontecer?"

**Demonstração:** no `factorFor` do `RpeRule`, logo após o primeiro `if`, adicione `if (rpe < 7.5) return 1.10;`. Os testes de exemplo (RPE 7, 8, 9 e 10) continuam verdes, porque nenhum cai entre 7 e 7,5. A propriedade falha: um RPE maior passa a sugerir mais carga.

## Testes de mutação com PIT

O PIT faz pequenas mudanças no código de produção (mutantes). Se nenhum teste falha, o mutante sobreviveu: há um buraco nos testes.

```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
# relatório HTML em target/pit-reports
```

```java
// original (FatigueRule)
return last.velocityLossPct() > LIMIT_PCT
// mutant: CONDITIONALS_BOUNDARY
return last.velocityLossPct() >= LIMIT_PCT
// without the 30% edge test: SURVIVED
// with velocityLossOfExactly30KeepsSets: KILLED
```

**Demonstração:** apague `velocityLossOfExactly30KeepsSets`, rode o PIT e mostre o mutante sobrevivente. Restaure o teste e rode de novo.

Cobertura mostra o que rodou. Mutação mostra o que foi verificado. Não persiga 100% de mutantes mortos: alguns são equivalentes (mudam o código sem mudar o comportamento). O PIT é mais lento que os testes normais; rode no CI ou antes do merge. As propriedades ficam fora do PIT (`excludedTestClasses`) para não multiplicar o tempo.

## Rastreabilidade: fechando o ciclo com produto

| Critério de aceitação | Teste | Produção |
|---|---|---|
| Treino fácil (RPE ≤ 7) sobe 5% | `easySessionIncreasesLoadBy5Percent` | `RpeRule` |
| Perda de velocidade acima de 30% tira uma série | `velocityLossAbove30RemovesOneSet` | `FatigueRule` |
| Exatamente 30% não muda o volume | `velocityLossOfExactly30KeepsSets` | `FatigueRule.LIMIT_PCT` |
| Prontidão ≤ 2 corta 5% da carga | `lowReadinessCutsLoadBy5Percent` | `ReadinessRule` |
| Semana da prova sem terra pesado | `meetWeekDropsHeavyDeadlift` | `Phase.allowsHeavy` |
| Carga sempre em anilhas de 2,5 kg | `roundDownStaysWithinOnePlate` | `Plates` |

Produto lê a primeira coluna; o time mantém as outras duas. Critério sem teste é critério não entregue.

O que ainda não tem linha: "perto da competição, treinar menos" está só parcialmente coberto. O fator de volume da fase existe (`Phase.volumeFactor`), mas não entra no motor, porque ele é relativo ao volume base do bloco, não à semana anterior. Aplicá-lo semana após semana cortaria o volume em cascata. Decidir a base do bloco é um desafio para casa.
