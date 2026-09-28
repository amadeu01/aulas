package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SmokeTest {

  // O que testa: nada do domínio; só prova que o ambiente funciona (JDK, Maven, JUnit e AssertJ).
  // Como: uma conta que não tem como dar errado. Se este teste não fica verde, o problema é de
  // ambiente, não de TDD: resolva antes de começar o dojo.
  @Test
  void smokeTest() {
    assertThat(1 + 1).isEqualTo(2);
  }
}
