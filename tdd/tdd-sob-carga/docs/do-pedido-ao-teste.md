# Do pedido de produto ao teste

O objetivo central da aula: ler um pedido de produto, achar a ambiguidade, transformar em exemplos e deixar os exemplos virarem testes que guiam o código. Serve para qualquer domínio.

## O ticket, como chega

> **[PROG-42] Sugerir a carga da próxima semana**
>
> Como atleta, quero que o app sugira o treino da próxima semana com base no último, para progredir sem me machucar.
>
> - Se o treino foi fácil, aumentar a carga
> - Se o atleta estiver cansado, pegar mais leve
> - Perto da competição, treinar menos

Tente escrever o `assertThat` do primeiro critério. Não dá: o que é "fácil"? Aumenta quanto? Se você não consegue escrever o teste, também não sabe escrever o código; só vai chutar.

## O caminho: frase, pergunta, exemplo, teste

| Passo | No exemplo |
|---|---|
| 1. A frase do produto | "Se o treino foi fácil, aumentar a carga." |
| 2. As perguntas que ela não responde | Fácil é RPE até 7? Aumenta quanto? Arredonda como? |
| 3. Exemplos concretos, validados com quem pediu | RPE 7: 100 → 105.0 · RPE 8: 100 → 102.5 · RPE 10: 100 → 95.0 |
| 4. Um teste por exemplo, com o nome da regra | `easySessionIncreasesLoadBy5Percent` |

```java
@Test
void easySessionIncreasesLoadBy5Percent() { // "easy" = RPE <= 7 (agreed with product)
  assertThat(RpeRule.next(100.0, 7.0)).isEqualTo(105.0);
}
```

A decisão de produto fica registrada no teste. Quando alguém perguntar "por que RPE 7?", a resposta está no código, não na memória de alguém.

## Caçando ambiguidade

Leve estas perguntas para o refinamento do seu time.

| Pergunte ao produto | No nosso exemplo | Vira teste de... |
|---|---|---|
| Exatamente no limite, conta? | 30% de perda tira série? | Borda: os dois lados do limite |
| Com que precisão? | 101,2 kg vira quanto? | Valor quebrado e arredondamento |
| E se o dado vier errado? | Carga negativa, RPE 11 | Entrada inválida e exceção |
| E se duas regras brigarem? | Treino fácil, mas muita fadiga | Aceitação: regras combinadas |
| O que nunca pode acontecer? | Subir carga com RPE 10 | Propriedade: vale para qualquer entrada |

## Critérios de aceitação combinados

| Frase do ticket | Regra combinada | Resultado esperado |
|---|---|---|
| Treino fácil | RPE ≤ 7 | carga +5% |
| Treino no alvo | RPE 7,5 a 8,5 | carga +2,5% |
| Treino pesado | RPE 9 | mantém a carga |
| Treino no limite | RPE ≥ 9,5 | carga −5% |
| Atleta cansado | perda de velocidade acima de 30% | −1 série |
| Atleta cansado | prontidão ≤ 2 (de 1 a 5) | carga −5% |
| (regra técnica) | sempre | para baixo, múltiplo de 2,5 kg ([detalhes](../REGRAS-DE-NEGOCIO.md#arredondamento-para-as-anilhas)) |
| Perto da competição | 9+ semanas / 8 a 3 / 2 / 1 | volume 100% / 85% / 70% / 50%, terra pesado sai na semana da prova |

Repare: "atleta cansado" virou duas regras. Uma frase de produto pode esconder vários critérios. E a última linha técnica ninguém pediu, mas o time precisa; regra técnica também vira teste.

## Os testes desenham a arquitetura

Não desenhamos classes antes: agrupamos critérios que mudam juntos, e cada grupo ganha uma classe de teste. A classe de produção nasce para fazer esses testes passarem.

| Grupo de critérios | Classe de teste | Código de produção | Depende de |
|---|---|---|---|
| Arredondamento | `PlatesTest` | `Plates` | nada |
| Esforço percebido | `RpeRuleTest` | `RpeRule`, `Loads` | `Plates` |
| Fadiga e prontidão | `FatigueRuleTest`, `ReadinessRuleTest` | `FatigueRule`, `ReadinessRule` | `Loads` |
| Prazo até a prova | `PhaseTest` | `Phase` (sealed) | nada |
| O ticket inteiro | `EngineAcceptanceTest` | `RuleBasedEngine` | todas as regras |

Teste difícil de montar é um recado do design: a classe sabe coisa demais. Uma regra nova (por exemplo, sono) vira uma classe nova com o próprio teste e uma linha no motor; nenhuma regra existente muda.

## Conflito entre regras é decisão de produto

"Treino fácil pelo RPE, mas a velocidade caiu 40%." Quem vence? Não é decisão técnica. O papel do dev é perceber o conflito e levar a pergunta ao produto com um exemplo concreto. A resposta vira um teste de aceitação com nome claro. No código atual, as regras se somam: a carga sobe pelo RPE e cai uma série pela fadiga (`targetSessionWithHighFatigueRaisesLoadAndDropsOneSet`).
