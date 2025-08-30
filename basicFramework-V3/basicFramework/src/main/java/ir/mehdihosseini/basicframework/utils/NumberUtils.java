package ir.mehdihosseini.basicframework.utils;

import java.util.Random;

public class NumberUtils {
    private NumberUtils(){}
    public static Long generateUniqueNumber() {
        long seed = System.currentTimeMillis();
        Random rng = new Random(seed);
//        long number = rng.nextLong();
//        long number = rng.nextLong() % 10000000000000;
        long number = (rng.nextLong() % 9000000000000l) + 1000000000000l;
        return number < 0 ? -number : number ;
    }
}
