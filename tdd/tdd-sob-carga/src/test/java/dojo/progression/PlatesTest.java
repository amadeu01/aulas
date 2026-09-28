package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class PlatesTest {

  // O que testa: uma carga "quebrada" é arredondada para baixo até caber nas anilhas de 2,5 kg.
  // Como: 101,2 kg não existe na barra; o múltiplo de 2,5 logo abaixo é 100,0.
  // Foi o primeiro teste da rodada 2: ficou verde primeiro com fake it (return 100.0;).
  @Test
  void roundsDownToNearestMultipleOf2_5() {
    assertThat(Plates.roundDown(101.2))
        .isEqualTo(100.0);
  }

  // O que testa: a borda. Uma carga que já é múltiplo de 2,5 não pode mudar.
  // Como: 102,5 entra e 102,5 sai. Este teste já nasce verde: não guia código novo, mas
  // documenta o limite e protege contra um arredondamento que "passe do ponto".
  @Test
  void keepsExactMultiple() {
    assertThat(Plates.roundDown(102.5))
        .isEqualTo(102.5);
  }

  // O que testa: entrada inválida. Carga negativa não é treino, é erro de dado.
  // Como: assertThatThrownBy executa a chamada e confere que ela lança IllegalArgumentException.
  @Test
  void rejectsNegativeLoad() {
    assertThatThrownBy(() -> Plates.roundDown(-1))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
