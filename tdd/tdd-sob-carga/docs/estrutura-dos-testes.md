# Como estruturar e o que testar

## Todo teste é entrada e saída

Um teste funciona como uma função: você prepara uma entrada, executa uma ação e confere uma saída. Três vocabulários descrevem a mesma estrutura:

| | Entrada | Ação | Saída |
|---|---|---|---|
| **Given / When / Then** (BDD, Dan North) | Given | When | Then |
| **Arrange / Act / Assert** (*Pragmatic Unit Testing*) | Arrange | Act | Assert |
| **Quatro fases** (Martin Fowler / Meszaros) | Setup (a fixture) | Exercise | Verify |

Convenção deste projeto: comentários `// given`, `// when`, `// then`.

```java
@Test
void velocityLossAbove30RemovesOneSet() {
  // given
  var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0);
  // when
  var sets = FatigueRule.nextSets(last);
  // then
  assertThat(sets).isEqualTo(4);
}
```

A saída nem sempre é um valor de retorno:

| Saída | Exemplo | Como conferir |
|---|---|---|
| Um valor | 101,2 kg vira 100 kg | `assertThat(...).isEqualTo(...)` |
| Um erro | carga negativa | `assertThatThrownBy(...).isInstanceOf(...)` |
| Um efeito num colaborador | o treinador é avisado | dublê: spy (estado) ou mock (comportamento) |

Nas regras puras (`Plates`, `RpeRule`, `FatigueRule`, `Phase`), a tabela de exemplos do produto vira diretamente um teste parametrizado: uma linha de `@CsvSource` por exemplo.

## De onde vem cada exemplo

Cada teste precisa de um motivo. No arredondamento (regra completa em [REGRAS-DE-NEGOCIO.md](../REGRAS-DE-NEGOCIO.md#arredondamento-para-as-anilhas)):

- **101,2 → 100:** o caso típico, uma carga "quebrada".
- **104,9 → 102,5:** o segundo exemplo da triangulação. Foi escolhido para ter uma resposta **diferente** de 100 e assim derrubar o `return 100.0` do fake it. Qualquer carga entre 102,5 e 104,99 serviria.
- **102,5 → 102,5:** a borda, um valor que já é múltiplo exato.
- **−1:** a entrada inválida.

## O que testar: Right-BICEP, CORRECT e ZOM

Heurísticas do livro *Pragmatic Unit Testing in Java with JUnit* (Jeff Langr, com Andy Hunt e Dave Thomas; a 3ª edição, de 2024, usa Java 21).

**Right-BICEP**

| Letra | Pergunta | No projeto |
|---|---|---|
| **Right** | O resultado está certo? | 101,2 kg vira 100 kg |
| **B**oundary | E nas bordas? | 30% exatos; 8 e 3 semanas |
| **I**nverse | Dá para conferir pelo inverso? | o resultado dividido por 2,5 é inteiro |
| **C**ross-check | Outro caminho dá o mesmo resultado? | conta em `BigDecimal` contra a tabela de exemplos |
| **E**rror | E quando dá errado? | carga negativa lança exceção |
| **P**erformance | Está rápido o bastante? | a suíte roda em milissegundos |

**CORRECT** (para achar bordas): Conformance (formato), Ordering (ordem), Range (faixa de valores), Reference (dependências externas), Existence (existe? é nulo?), Cardinality (quantos), Time (quando). No projeto, "RPE fora de 1 a 10" é Range; "prontidão ausente" seria Existence.

**ZOM** (zero, um, muitos), para coleções:

| | Teste |
|---|---|
| Zero | `emptyWeekGivesEmptyPlan` |
| Um | `targetSessionWithHighFatigueRaisesLoadAndDropsOneSet` |
| Muitos | `meetWeekDropsHeavyDeadlift` |
