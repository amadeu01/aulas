package dojo.progression;

import static dojo.progression.Lift.DEADLIFT;
import static dojo.progression.Lift.SQUAT;

import java.util.List;

/** Runs the engine once so the class can see a plan outside the tests (IntelliJ: green arrow next to main). */
public final class Main {
  private Main() {}

  public static void main(String[] args) {
    var lastWeek = new WeekLog(List.of(
        new SetLog(SQUAT, 150, 5, 3, 8.0, 35.0),
        new SetLog(DEADLIFT, 200, 3, 2, 9.0, 10.0)), 4);

    for (int weeksToMeet : new int[] {5, 1}) {
      System.out.println("Weeks to meet: " + weeksToMeet);
      new RuleBasedEngine().nextWeek(lastWeek, weeksToMeet)
          .forEach(p -> System.out.println("  " + p));
    }
  }
}
