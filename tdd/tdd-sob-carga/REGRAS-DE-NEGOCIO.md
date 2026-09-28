# Regras de negócio

Ponto de partida da aula: o pedido do produto e as regras combinadas depois da conversa. Ainda não existe código de regra; cada linha das tabelas abaixo vai virar pelo menos um teste. O caminho completo (frase, perguntas, exemplos, testes) está em [docs/do-pedido-ao-teste.md](docs/do-pedido-ao-teste.md).

## O ticket, como chegou

> **[PROG-42] Sugerir a carga da próxima semana**
>
> Como atleta, quero que o app sugira o treino da próxima semana com base no último, para progredir sem me machucar.
>
> - Se o treino foi fácil, aumentar a carga
> - Se o atleta estiver cansado, pegar mais leve
> - Perto da competição, treinar menos

Nenhum desses critérios é testável como está: o que é "fácil"? Aumenta quanto? "Cansado" medido como? "Perto" são quantas semanas?

## Vocabulário

| Termo | O que é |
|---|---|
| **RPE** | Esforço percebido numa série, de 1 a 10. O alvo do programa é 8. |
| **Perda de velocidade** | Quanto a barra ficou mais lenta do início ao fim da série, em %. Mede a fadiga. |
| **Prontidão** | Nota de 1 a 5 que o atleta dá antes do treino. |
| **Semanas até a prova** | Quantas semanas faltam para a competição. |
| **Anilha** | O menor incremento possível na barra: 2,5 kg. |

## Critérios de aceitação combinados com o produto

| Frase do ticket | Regra combinada | Resultado esperado | Exemplo |
|---|---|---|---|
| Treino fácil | RPE ≤ 7 | carga +5% | 100 kg → 105 kg |
| Treino no alvo | RPE 7,5 a 8,5 | carga +2,5% | 100 kg → 102,5 kg |
| Treino pesado | RPE 9 | mantém a carga | 100 kg → 100 kg |
| Treino no limite | RPE ≥ 9,5 | carga −5% | 100 kg → 95 kg |
| Atleta cansado | perda de velocidade **acima** de 30% | −1 série (nunca menos que 1) | 5 séries → 4; com exatamente 30%, continuam 5 |
| Atleta cansado | prontidão ≤ 2 | carga −5% | 150 kg → 142,5 kg |
| (regra técnica) | sempre | arredondar **para baixo** a múltiplos de 2,5 kg | 101,2 kg → 100 kg; 102,5 kg → 102,5 kg; carga negativa é erro |

Repare: "atleta cansado" virou **duas** regras. E a regra técnica ninguém pediu, mas o time precisa: ninguém coloca 101,2 kg numa barra.

## Perto da competição

| Semanas até a prova | Fase | Volume em relação ao acúmulo |
|---|---|---|
| 9 ou mais | Acúmulo | 100% |
| 8 a 3 | Intensificação | 85% |
| 2 | Polimento | 70% |
| 1 | Semana da prova | 50%, e **sem terra pesado** |

As bordas (8 e 3 semanas) são onde mais se erra.

## Quando as regras brigam

"Treino fácil pelo RPE, mas a velocidade caiu mais de 30%." Não é decisão técnica, é decisão de produto. A decisão para o PROG-42:

- As regras se somam: a carga sobe pelo RPE e cai uma série pela fadiga.
- O treinador recebe um aviso de "sinais conflitantes" para decidir.

## Exemplo de ponta a ponta

Semana passada: agachamento, 150 kg, 5 séries de 3, RPE 8, perda de velocidade de 35%, prontidão 4, faltando 5 semanas para a prova.

| Passo | Conta | Resultado |
|---|---|---|
| RPE 8 (alvo) | 150 × 1,025 = 153,75 | arredonda para 152,5 kg |
| Prontidão 4 | não corta | 152,5 kg |
| Perda de 35% | acima de 30% | 5 → 4 séries |

Próxima semana: **agachamento, 152,5 kg, 4 séries de 3.**

## Fora do escopo desta aula

- Rejeitar RPE fora de 1 a 10 e prontidão fora de 1 a 5
- Aplicar o fator de volume da fase ao volume base do bloco

Os números são didáticos, inspirados na literatura de treino citada em [docs/referencias.md](docs/referencias.md). Não são prescrição de treino.
