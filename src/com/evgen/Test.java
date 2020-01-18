package com.evgen;

import java.util.*;

/**
 * Should be improved to reduce calculation time.
 *
 * 1. Change this file or create new one using parallel calculation mode.
 * 2. Do not use `executors`, only plain threads  (max threads count which should be created for calculations is com.evgen.TestConsts#MAX_THREADS)
 * 3. Try to provide simple solution, do not implement frameworks.
 * 4. Don't forget that calculation method can throw exception, process it right way. 
 *   (Stop calculation process and print error message. Ignore already calculated intermediate results, user doesn't need it.)
 *   
 */

/**
 * SOLUTION TEST CLASS
 */
public class Test {
    public static volatile int counter = 0;
    public static Object key = new Object();
    public static volatile Set<Double> commonResult = new HashSet<>();
    public static volatile String testExceptionMessage = null;

    public static void main(String[] args){
        Set<Double> res = new HashSet<>();

        long timeStart = System.currentTimeMillis();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int t = 0; t < TestConsts.MAX_THREADS; t++){
            threads.add(new Thread(new Worker()));
        }
        threads.forEach(thread -> thread.start());
        try {
            for (int t = 0; t < threads.size(); t++){
                threads.get(t).join();
            }
            long timeFinish = System.currentTimeMillis();
            long time = timeFinish - timeStart;
            System.out.println("Execution time = " + time + " ms");

            if (testExceptionMessage == null){
                res.addAll(commonResult);
                System.out.println("Res size = " + res.size() + ", Res = " + res);
            }
            else {
                System.out.println(testExceptionMessage);
            }

        } catch (InterruptedException e) {
            System.out.println("Program failed!");
        }
    }
}

/**
 * SOURCE TEST CLASS
 */

/*public class Test {
    public static void main(String[] args) throws TestException {
        Set<Double> res = new HashSet<>();

        for (int i = 0; i < TestConsts.N; i++) {
            res.addAll(TestCalc.calculate(i));
        }

        System.out.println(res);
    }
}*/
