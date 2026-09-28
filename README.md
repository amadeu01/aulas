# Aulas

Material e código das aulas de Amadeu Cavalcante. Cada aula vive numa pasta própria, com código, roteiro e notas.

| Aula | Pasta | Tema |
|---|---|---|
| TDD sob carga | [`tdd/tdd-sob-carga`](tdd/tdd-sob-carga) | Coding dojo de TDD em Java 24: do pedido de produto ao código em produção |

A evolução de cada aula fica em branches. TDD sob carga: `aula-tdd-beginning`, `aula-tdd-1` … `aula-tdd-7` (detalhes no [README da aula](tdd/tdd-sob-carga/README.md#o-caminho-da-aula-em-branches)).

## Abrir no IntelliJ IDEA

1. **File › Open** › escolha esta pasta (`aulas`). O `pom.xml` da raiz agrega as aulas; cada uma vira um módulo Maven.
2. **File › Project Structure › Project › SDK:** um JDK 24 ou mais novo.
3. Use as configurações prontas em `.run/` (menu no alto à direita), como **Testes (tdd-sob-carga)** e **Main (tdd-sob-carga)**.

## No terminal

Pré-requisito: JDK 24+. O Maven Wrapper baixa o Maven sozinho.

```bash
./mvnw test          # testes de todas as aulas
```
