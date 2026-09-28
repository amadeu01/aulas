package dojo.progression;

import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FatigueRuleTest {

  // O que testa: "atleta cansado" medido pela velocidade: perda acima de 30% tira uma série.
  // Como: given/when/then. Uma série com 35% de perda e 5 séries deve virar 4 séries.
  @Test
  void velocityLossAbove30RemovesOneSet() {
    // given
    var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0);
    // when
    var sets = FatigueRule.nextSets(last);
    // then
    assertThat(sets).isEqualTo(4);
  }

  // O que testa: a borda da regra. "Acima de 30%" quer dizer que exatamente 30% NÃO tira série.
  // Como: mesmo cenário, com 30,0% de perda; as séries continuam 5.
  // É este teste que decide entre > e >= no código (e que mata o mutante do PIT).
  @Test
  void velocityLossOfExactly30KeepsSets() {
    var last = new SetLog(SQUAT, 150, 5, 3, 8.0, 30.0);
    assertThat(FatigueRule.nextSets(last)).isEqualTo(5);
  }

  // O que testa: um limite de segurança. Mesmo cansado, o atleta nunca fica com zero séries.
  // Como: começa com 1 série e 45% de perda; o resultado continua 1.
  @Test
  void neverGoesBelowOneSet() {
    var last = new SetLog(SQUAT, 150, 1, 3, 8.0, 45.0);
    assertThat(FatigueRule.nextSets(last)).isEqualTo(1);
  }
}
