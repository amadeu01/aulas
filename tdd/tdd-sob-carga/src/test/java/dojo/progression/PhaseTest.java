package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;
import static org.assertj.core.api.Assertions.assertThat;

import dojo.progression.Phase.MeetWeek;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhaseTest {

  // O que testa: faltando 2 semanas para a prova (polimento), o volume cai para 70%.
  // Como: Phase.of(2) escolhe a fase e volumeFactor devolve o fator dela.
  @Test
  void taperVolumeIs70Percent() {
    assertThat(Phase.volumeFactor(Phase.of(2)))
        .isEqualTo(0.70);
  }

  // O que testa: a tabela de fases por semanas até a prova, incluindo as bordas 8 e 3.
  // Como: teste parametrizado; cada linha diz "com N semanas, a fase é X". Comparamos pelo nome
  // da classe (getSimpleName) porque cada fase é um record diferente.
  @ParameterizedTest
  @CsvSource({"9,Accumulation", "8,Intensification",
              "3,Intensification", "2,Taper",
              "1,MeetWeek"})
  void phaseByWeeksToMeet(int weeks, String name) {
    assertThat(Phase.of(weeks).getClass()
        .getSimpleName()).isEqualTo(name);
  }

  // O que testa: a exceção do terra. Na semana da prova, terra pesado sai do plano.
  // Como: allowsHeavy(semana da prova, DEADLIFT) tem que responder false.
  @Test
  void meetWeekDropsHeavyDeadlift() {
    var meetWeek = new MeetWeek();
    assertThat(Phase.allowsHeavy(meetWeek, DEADLIFT))
        .isFalse();
  }

  // O que testa: que a exceção vale só para o terra; o agachamento continua na semana da prova.
  // Como: mesma fase, outro movimento; agora a resposta é true.
  @Test
  void meetWeekKeepsSquat() {
    assertThat(Phase.allowsHeavy(new MeetWeek(), SQUAT))
        .isTrue();
  }
}
