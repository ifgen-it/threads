package com.evgen;

/**
 * NEW CLASS FOR SOLUTION
 */
public class Worker implements Runnable {

    @Override
    public void run(){

        while (true) {
            int i = 0;
            synchronized (Test.key) {
                if (Test.counter >= TestConsts.N || Test.testExceptionMessage != null)
                    break;
                i = Test.counter++;
            }

            try {
                Test.commonResult.addAll(TestCalc.calculate(i));
            } catch (TestException e) {
                Test.testExceptionMessage = e.getMessage();
            }


        }
    }
}
