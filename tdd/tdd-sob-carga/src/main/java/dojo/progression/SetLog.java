package dojo.progression;

/** What the athlete did last week for one lift. */
public record SetLog(Lift lift, double loadKg, int sets, int reps,
                     double rpe, double velocityLossPct) {}
