package dojo.progression;

import static org.assertj.core.api.Assertions.assertThat;

import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

class RpeRuleProperties {

  @Property
  void higherRpeNeverSuggestsMoreLoad(
      @ForAll @DoubleRange(min = 6, max = 10) double a,
      @ForAll @DoubleRange(min = 6, max = 10) double b) {
    Assume.that(a <= b);
    assertThat(RpeRule.next(100, a))
        .isGreaterThanOrEqualTo(RpeRule.next(100, b));
  }
}
