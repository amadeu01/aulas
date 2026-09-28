package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

class PlatesProperties {

  // O que testa: uma propriedade que vale para QUALQUER carga, não só para os exemplos: o
  // arredondado nunca passa da carga original e erra menos de uma anilha (2,5 kg).
  // Como: o jqwik gera 1000 cargas entre 0 e 500 kg (@ForAll @DoubleRange) e roda o teste para
  // cada uma. Se achar uma falha, encolhe até o menor exemplo que quebra.
  @Property
  void roundDownStaysWithinOnePlate(
      @ForAll @DoubleRange(min = 0, max = 500) double kg) {
    double r = Plates.roundDown(kg);
    assertThat(r).isLessThanOrEqualTo(kg);
    assertThat(kg - r).isLessThan(Plates.INCREMENT);
  }
}
