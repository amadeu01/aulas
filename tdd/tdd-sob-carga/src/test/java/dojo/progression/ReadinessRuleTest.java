package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReadinessRuleTest {

  // O que testa: prontidão baixa (nota 2 de 5) corta 5% da carga.
  // Como: 150 kg x 0,95 = 142,5 kg, que já é múltiplo de 2,5.
  @Test
  void lowReadinessCutsLoadBy5Percent() {
    assertThat(ReadinessRule.adjust(150.0, 2))
        .isEqualTo(142.5);
  }

  // O que testa: o outro lado da regra. Prontidão boa (nota 4) não mexe na carga.
  // Como: 150 kg entra e 150 kg sai. Sem este teste, "sempre cortar 5%" também passaria.
  @Test
  void goodReadinessKeepsLoad() {
    assertThat(ReadinessRule.adjust(150.0, 4))
        .isEqualTo(150.0);
  }
}
