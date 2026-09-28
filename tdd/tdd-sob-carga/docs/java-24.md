# Java 24 nesta aula

O projeto compila com `--release 24` e roda em qualquer JDK 24 ou mais novo (o 25 LTS incluso). O TDD em si (ciclo, JUnit, AssertJ, Mockito, jqwik, PIT) não depende da versão; o que muda é o que a linguagem oferece.

| Recurso | Desde | Na aula |
|---|---|---|
| Records | 16 | Sim: `SetLog`, `WeekLog`, `Prescription` |
| `Stream.toList()` | 16 | Sim: `RuleBasedEngine` |
| Sealed interfaces | 17 | Sim: `Phase` |
| Switch com pattern matching e record patterns | 21 | Sim: `Phase.volumeFactor` |
| Padrões e variáveis sem nome `_` (JEP 456) | 22 | Sim: `case Taper _ -> 0.70;` |
| Stream Gatherers (JEP 485) | 24 | Desafio para casa |
| Main sem classe (instance main) | Preview no 24 | Não usar (final só no 25) |

```java
static double volumeFactor(Phase p) {
  return switch (p) {
    case Accumulation _    -> 1.00;
    case Intensification _ -> 0.85;
    case Taper _           -> 0.70;
    case MeetWeek _        -> 0.50;
  };
}
```

O `_` diz: "só importa o tipo, não preciso da variável". No Java 21 ele é preview; lá, escreva `case Taper t ->`.

## Limite na JVM

Bytecode tem versão. `--release 21` gera class file 65; `--release 24` gera 68. Este projeto não roda numa JVM 21 ou 23 (`UnsupportedClassVersionError`). Um time com Java 21 em produção não pode receber classes compiladas para 24.

O CI roda os testes em JDK 24 e JDK 25.

## Observações

- O Java 24 não é LTS e já saiu de suporte; o LTS seguinte ao 21 é o 25, e tudo desta aula roda nele sem mudança.
- No Java 21+, o Mockito mostra um aviso sobre carregar um agente dinâmico. É só um aviso; os testes rodam.
- As cinco fases de um programa Java (editar, compilar, carregar, verificar, executar) seguem a apresentação dos Deitel em *Java How to Program*. Cada volta do ciclo TDD passa por elas; por isso testes de unidade precisam ser rápidos.
