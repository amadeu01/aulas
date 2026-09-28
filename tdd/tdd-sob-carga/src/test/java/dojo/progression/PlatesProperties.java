package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

class PlatesProperties {

  @Property
  void roundDownStaysWithinOnePlate(
      @ForAll @DoubleRange(min = 0, max = 500) double kg) {
    double r = Plates.roundDown(kg);
    assertThat(r).isLessThanOrEqualTo(kg);
    assertThat(kg - r).isLessThan(Plates.INCREMENT);
  }
}
