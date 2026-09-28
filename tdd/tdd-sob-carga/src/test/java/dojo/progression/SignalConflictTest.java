package dojo.progression;

import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SignalConflictTest {

  // O que testa: a decisão de produto para regras que brigam. O RPE diz "fácil" (<= 7), mas a
  // velocidade diz "cansado" (perda acima de 30%): isso é um conflito.
  // Como: RPE 7 com 35% de perda deve ser detectado (true).
  @Test
  void easyRpeWithHighVelocityLossIsAConflict() {
    assertThat(SignalConflict.detect(new SetLog(SQUAT, 150, 5, 3, 7.0, 35.0))).isTrue();
  }

  // O que testa: o outro lado. Com RPE 8 (no alvo), perda alta é só fadiga, não conflito.
  // Como: mesmo cenário mudando só o RPE; a resposta é false. Mudar uma variável por vez mostra
  // exatamente o que causa o conflito.
  @Test
  void targetRpeWithHighVelocityLossIsNotAConflict() {
    assertThat(SignalConflict.detect(new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0))).isFalse();
  }
}
