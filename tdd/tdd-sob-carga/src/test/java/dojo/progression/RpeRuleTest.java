package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RpeRuleTest {

  // O que testa: a primeira regra do ticket, "treino fácil aumenta a carga".
  // Como: "fácil" foi combinado com o produto como RPE <= 7, e o aumento é de 5%: 100 kg viram
  // 105 kg. O nome do teste é a própria regra de negócio.
  @Test
  void easySessionIncreasesLoadBy5Percent() { // "easy" = RPE <= 7 (agreed with product)
    assertThat(RpeRule.next(100.0, 7.0)).isEqualTo(105.0);
  }

  // O que testa: a tabela inteira de RPE: <= 7 sobe 5%, até 8,5 sobe 2,5%, 9 mantém, >= 9,5 cai 5%.
  // Como: teste parametrizado. Cada linha do @CsvSource é um exemplo (rpe, carga, esperado) e o
  // JUnit roda o mesmo teste uma vez por linha. Na aula, acrescente uma linha por vez.
  // A linha do RPE 8 revelou o bug do double: 100 * 1.025 == 102.49999999999999, que arredonda
  // para 100.0. A correção foi Loads.scale (BigDecimal); o teste não mudou.
  @ParameterizedTest
  @CsvSource({
    "7.0,  100.0, 105.0",
    "8.0,  100.0, 102.5",
    "9.0,  100.0, 100.0",
    "10.0, 100.0,  95.0"
  })
  void adjustsLoadByRpe(double rpe, double load,
                        double expected) {
    assertThat(RpeRule.next(load, rpe))
        .isEqualTo(expected);
  }
}
