package util;

import java.util.Random;

public class ThreadUtil {

    private static Random rd = new Random();

    public static boolean interval(long time){
        if(time < 1){
            return true;
        }
        try {
            Thread.sleep(time);
            return false;
        } catch (InterruptedException ignore) {}

        return true;
    }

    public static boolean interval(long min, long max){
        return interval(min + rd.nextInt((int)(max - min) + 1));
    }
    
}
