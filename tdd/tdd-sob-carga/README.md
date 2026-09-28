# TDD sob carga

Coding dojo de TDD em Java 24: **do pedido de produto ao código em produção, teste a teste.**

A aula ensina a pegar uma regra de produto ambígua, transformá-la em exemplos concretos, deixar os exemplos virarem testes que guiam o design e, no fim, provar que a lógica é robusta. O domínio, uma sugestão de cargas de treino de powerlifting, é só um pretexto: tem números, limites e regras que brigam entre si, como qualquer ticket real de frete, desconto ou limite de crédito. Ninguém precisa entender de treino.

- **Duração:** 2 horas, formato coding dojo (Randori: piloto, copiloto e plateia)
- **Público:** quem já programa em Java e está começando com TDD
- **Java:** **Java 24** (`--release 24`); roda em qualquer JDK 24 ou mais novo (25 LTS incluso). O que a aula usa do 24 está em [docs/java-24.md](docs/java-24.md)
- **Ferramentas:** JUnit 5, AssertJ, Mockito, jqwik (testes de propriedade) e PIT (testes de mutação)
- **Base teórica:** "The Cycles of TDD" (Robert C. Martin, 2014), *Clean Code*, *The Clean Coder*, *Clean Craftsmanship*, Kent Beck, Martin Fowler (fixture e dublês de teste), *Pragmatic Unit Testing in Java with JUnit* (Given/When/Then, Right-BICEP, ZOM) e Deitel. Veja [docs/referencias.md](docs/referencias.md)

Convenção da aula: **código, nomes de testes e comentários em inglês**; slides, conversa e documentação em português.

## Como rodar

Pré-requisito: JDK 24 ou mais novo. Não precisa instalar o Maven: o repositório traz o Maven Wrapper (`mvnw`) na raiz de `aulas`.

### No IntelliJ IDEA

1. **File › Open** e escolha a pasta `aulas` (a raiz do repositório, onde está o `pom.xml` agregador). O IntelliJ importa a aula como módulo Maven.
2. **File › Project Structure › Project › SDK:** escolha um JDK 24 ou mais novo (se não houver, **Add SDK › Download JDK**). O nível de linguagem vem do `pom.xml`.
3. Rode pelo menu de configurações, no alto à direita:
   - **Testes (tdd-sob-carga):** todos os testes, inclusive os de propriedade (jqwik)
   - **Main (tdd-sob-carga):** imprime o plano sugerido pelo motor para um exemplo
4. No dojo, o atalho mais usado é a seta verde ao lado de cada teste ou classe de teste (**Ctrl+Shift+F10** / **⌃⇧R**) e **Shift+F10** / **⌃R** para repetir o último.

Abrir só a pasta `tdd/tdd-sob-carga` também funciona: o `pom.xml` da aula é independente.

### No terminal

```bash
cd tdd/tdd-sob-carga
../../mvnw test                                                    # testes de exemplo e de propriedade
../../mvnw test-compile org.pitest:pitest-maven:mutationCoverage   # testes de mutação (relatório em target/pit-reports)
../../mvnw compile exec:java -Dexec.mainClass=dojo.progression.Main   # roda o Main
```

No Windows, use `..\..\mvnw.cmd`. Com o Maven instalado, `mvn` no lugar de `../../mvnw` dá no mesmo.

Bytecode compilado para 24 (class file 68) não roda numa JVM 21: `UnsupportedClassVersionError`.

## O caminho da aula no histórico do git

Cada rodada do dojo tem uma tag com o **estado esperado** ao final dela. Use as tags como gabarito ou como resgate: se uma rodada estourar o tempo, faça checkout da tag e siga.

| Tag | Rodada | O que existe |
|---|---|---|
| `inicio` | Ponto de partida da turma | Contrato (records e interface), teste de fumaça, `TEST-LIST.md` vazio |
| `rodada-2-anilhas` | Arredondar para as anilhas | `Plates` e `PlatesTest` |
| `rodada-3-rpe` | O RPE ajusta a carga | `RpeRule`, `Loads` (correção do bug do `double`) e teste parametrizado |
| `rodada-4-fadiga` | A fadiga corta o volume | `FatigueRule`, `ReadinessRule` e o teste da borda de 30% |
| `rodada-5-fases` | A competição está chegando | `Phase` (sealed + switch com `_`) e a exceção do terra |
| `motor` | Entregue pronto antes da rodada 6 | `RuleBasedEngine` e o teste de aceitação do ticket |
| `rodada-6-colaboradores` | O serviço e as bordas | `WeeklyPlanService`, portas e os cinco dublês de teste (dummy, stub, fake, spy, mock) |
| `robustez` | A lógica está robusta? | Testes de propriedade com jqwik e PIT configurado |

A rodada 1 é de planejamento: a turma decide todos os testes no [`TEST-LIST.md`](TEST-LIST.md), sem escrever código.

```bash
git checkout inicio            # comece a aula daqui
git checkout rodada-3-rpe      # resgate: pule para o fim da rodada 3
git checkout main              # estado final completo
```

## Estrutura

```
├── README.md
├── TEST-LIST.md                    # a lista de testes (rodada 1), marcada a cada verde
├── pom.xml                         # Java 24, JUnit 5, AssertJ, Mockito, jqwik, PIT
├── src/main/java/dojo/progression  # código de produção (regras puras, motor, serviço, portas e Main)
├── src/test/java/dojo/progression  # *Test = exemplos, *Properties = propriedades, InMemory*/Spy* = dublês
├── docs/
│   ├── roteiro-do-facilitador.md   # plano minuto a minuto, estado esperado e perguntas e respostas
│   ├── do-pedido-ao-teste.md       # o método: frase, perguntas, exemplos, testes
│   ├── estrutura-dos-testes.md     # entrada e saída, given/when/then, Right-BICEP, CORRECT, ZOM
│   ├── dubles-de-teste.md          # fixture, SUT e os cinco dublês (Martin Fowler)
│   ├── robustez.md                 # bordas, inválidos, propriedades, mutação, rastreabilidade
│   ├── java-24.md                  # o que a aula usa do Java 24
│   ├── referencias.md
│   └── slides-e-notas.md           # texto de todos os slides com as notas do apresentador
├── slides/README.md                # onde estão os slides e como exportar
└── .github/workflows/ci.yml        # mvn test em JDK 24 e 25
```

## Da regra de produto ao código

| Critério de aceitação | Teste | Produção |
|---|---|---|
| Treino fácil (RPE ≤ 7) sobe 5% | `easySessionIncreasesLoadBy5Percent` | `RpeRule` |
| Perda de velocidade acima de 30% tira uma série | `velocityLossAbove30RemovesOneSet` | `FatigueRule` |
| Exatamente 30% não muda o volume | `velocityLossOfExactly30KeepsSets` | `FatigueRule.LIMIT_PCT` |
| Prontidão ≤ 2 corta 5% da carga | `lowReadinessCutsLoadBy5Percent` | `ReadinessRule` |
| Semana da prova sem terra pesado | `meetWeekDropsHeavyDeadlift` | `Phase.allowsHeavy` |
| Carga sempre em anilhas de 2,5 kg | `roundDownStaysWithinOnePlate` | `Plates` |

| Treino fácil com muita fadiga avisa o treinador | `conflictingSignalsAlertTheCoach_withSpy` | `SignalConflict`, `WeeklyPlanService` |

Critério sem teste é critério não entregue.

## Aviso

Os números do modelo (percentuais de RPE, limite de perda de velocidade, fases) são didáticos, inspirados na literatura de treino citada nas referências. Não são prescrição de treino.

## Autor

Amadeu Cavalcante. Aula ministrada em 28/09/2026.
