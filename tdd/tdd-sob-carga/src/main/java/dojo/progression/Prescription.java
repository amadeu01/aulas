package dojo.progression;

/** What the athlete should do next week for one lift. */
public record Prescription(Lift lift, double loadKg, int sets, int reps) {}
