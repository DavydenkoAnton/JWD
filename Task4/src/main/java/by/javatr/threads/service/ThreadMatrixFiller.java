package by.javatr.threads.service;

import by.javatr.threads.bean.Matrix;
import by.javatr.threads.presentation.Runner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadMatrixFiller implements Runnable {
    private static final Logger LOG = LogManager.getLogger(ThreadMatrixFiller.class);
    String threadName = "Thread ";
    Matrix matrix;
    ReentrantLock locker;

    public ThreadMatrixFiller(int number, ReentrantLock locker) {
        matrix = Matrix.getInstance();
        this.locker = locker;
        threadName += String.valueOf(number);
    }

    @Override
    public void run() {
        System.out.print(threadName + " start | ");

        while (!matrix.isDiagonalFilled()) {
            changeDiagonalValue();
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        }

        System.out.println(threadName + " end");


    }


    private void changeDiagonalValue() {
        locker.lock();
        System.out.print(threadName + " lock");
        for (int i = 0; i < matrix.getSize(); i++) {
            int value = matrix.getValue(i, i);
            if (value == 0) {
                matrix.setValue(i, i, i + 1);
                System.out.print(" change " + i + "," + i);
                break;
            }
        }
        System.out.println(" | unlock");
        locker.unlock();
    }
}

