package dojo.progression;

import java.util.List;

/** Last week's training plus the athlete's readiness score (1 to 5). */
public record WeekLog(List<SetLog> sets, int readiness) {}
