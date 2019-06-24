package by.javatr.threads.presentation;

import by.javatr.threads.bean.Matrix;
import by.javatr.threads.service.MatrixService;
import by.javatr.threads.service.ThreadMatrixFiller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private static final Logger LOG = LogManager.getLogger(Runner.class);

    public static void main(String[] args) {

        ReentrantLock locker = new ReentrantLock();
        Matrix matrix = Matrix.getInstance();
        MatrixService matrixService = new MatrixService();
        String fileName = "matrix.txt";
        List<Thread> matrixFiller = new ArrayList<>();

        matrixService.initFromFile(matrix, fileName);

        int matrixSize = matrixService.getMatrixSize();

        for (int i = 1; i <= 6; i++) {
            Thread filler = new Thread(new ThreadMatrixFiller(i, locker));
            matrixFiller.add(filler);
        }

        for (Thread thread : matrixFiller) {
            thread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i <matrix.getSize() ; i++) {
            for (int j = 0; j <matrix.getSize() ; j++) {
                System.out.print(matrix.getValue(i,j)+"\t");
            }
            System.out.println();
        }

    }
}