# Java 21 ou 24: o que muda para esta aula

Regra da aula: **tudo o que é obrigatório compila em Java 21.** O que for só do 24 aparece como bônus. O TDD em si (ciclo, JUnit, AssertJ, jqwik, PIT) é igual nas duas versões.

| Recurso | Java 21 (LTS) | Java 24 | Na aula |
|---|---|---|---|
| Records | Final | Final | Sim: `SetLog`, `WeekLog`, `Prescription` |
| Sealed interfaces | Final | Final | Sim: `Phase` |
| Switch com pattern matching e record patterns | Final | Final | Sim: `Phase.volumeFactor` |
| `Stream.toList()` | Final (desde 16) | Final | Sim: `RuleBasedEngine` |
| Variável sem nome `_` (JEP 456) | Preview, com `--enable-preview` | Final (desde 22) | Bônus: `case Taper _ -> 0.70;` |
| Stream Gatherers (JEP 485) | Não existe | Final (24) | Bônus |
| Main sem classe (instance main) | Preview | Preview | Não usar (final só no 25) |

## Limite na JVM

Bytecode tem versão. `--release 21` gera class file 65; `--release 24` gera 68. Compilou para 24? Não roda numa JVM 21 (`UnsupportedClassVersionError`). Um time com Java 21 em produção não pode receber classes compiladas para 24.

O CI deste repositório roda `mvn test` em JDK 21 e JDK 24, sempre com `--release 21`.

## Observações

- O Java 24 não é LTS e já saiu de suporte; o LTS seguinte ao 21 é o 25, e tudo desta aula também roda nele.
