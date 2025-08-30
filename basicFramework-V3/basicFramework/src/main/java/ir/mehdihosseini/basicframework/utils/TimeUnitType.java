package ir.mehdihosseini.basicframework.utils;

public enum TimeUnitType {
    NANOSECONDS,
    MICROSECONDS,
    MILLISECONDS,
    SECONDS,
    MINUTES,
    HOURS,
    DAYS,
    CUSTOM_SECONDS,
    CUSTOM_MINUTES,
    CUSTOM_HOURS;


    public static TimeUnitType fromString(String name) {
        for (TimeUnitType timeUnit : TimeUnitType.values()) {
            if (timeUnit.name().equalsIgnoreCase(name)) {
                return timeUnit;
            }
        }
        throw new IllegalArgumentException("Unknown Time Unit type by name  : " + name);
    }
}
