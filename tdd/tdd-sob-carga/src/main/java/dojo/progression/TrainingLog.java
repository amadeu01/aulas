package dojo.progression;

/** Port: where last week's training comes from (database, app, spreadsheet...). */
public interface TrainingLog {
  WeekLog lastWeek(String athleteId);
}
