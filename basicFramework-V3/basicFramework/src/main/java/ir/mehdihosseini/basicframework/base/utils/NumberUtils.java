package ir.mehdihosseini.basicframework.base.utils;

import java.time.Instant;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberUtils {

    private NumberUtils() {
    }

    public static Long generateUniqueLongNumber() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
        long number = (rng.nextLong() % 9000000000000L) + 1000000000000L;
        return number < 0 ? -number : number;
    }

    public static Integer generateUniqueShortNumber() {
        long millis = System.currentTimeMillis();
        long nano = System.nanoTime();
        long epochSecond = Instant.now().getEpochSecond();
        millis = (millis + nano + epochSecond) * 1000;

        Random rng = new Random(millis);
        int number = (int) ((rng.nextLong() % 900000000) + 100000000);
        return number < 0 ? -number : number;
    }

}
