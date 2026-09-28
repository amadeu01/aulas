package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

class RpeRuleProperties {

  // O que testa: "o que nunca pode acontecer": um RPE maior (treino mais difícil) sugerir mais
  // carga que um RPE menor.
  // Como: o jqwik sorteia dois RPEs entre 6 e 10; Assume.that descarta os pares em que a > b, e
  // conferimos que next(100, a) >= next(100, b). Pega bugs que os exemplos de RPE 7, 8, 9 e 10
  // não pegam (veja a demonstração em docs/robustez.md).
  @Property
  void higherRpeNeverSuggestsMoreLoad(
      @ForAll @DoubleRange(min = 6, max = 10) double a,
      @ForAll @DoubleRange(min = 6, max = 10) double b) {
    Assume.that(a <= b);
    assertThat(RpeRule.next(100, a))
        .isGreaterThanOrEqualTo(RpeRule.next(100, b));
  }
}
