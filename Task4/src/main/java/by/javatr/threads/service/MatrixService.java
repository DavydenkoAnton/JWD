package by.javatr.threads.service;

import by.javatr.threads.bean.Matrix;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixService {

    private static final Logger LOGGER = LogManager.getLogger(MatrixService.class.getName());
    List<String> matrixLines;
    ParserTxtFile parserTxtFile;
    Matrix matrix;
    ReentrantLock locker;

    public MatrixService() {
        parserTxtFile = new ParserTxtFile();
        matrix = Matrix.getInstance();
    }

    public void initFromFile(Matrix matrix, String fileName) {
        matrixLines = new ArrayList<>();
        try {
            matrixLines = parserTxtFile.parse(fileName);
        } catch (ParserTxtFileException e) {
            LOGGER.error(e);
        }
        initMatrix();
    }

    private void initMatrix() {
        int matrixSize = matrixLines.size();
        matrix.setSize(matrixSize);
        int row = 0;
        String[] tempArray;
        String delimiter = " ";
        try {
            for (String matrixLine : matrixLines) {
                tempArray = matrixLine.split(delimiter);
                for (int j = 0; j < matrixSize; j++) {
                    int value = Integer.valueOf(tempArray[j]);
                    matrix.setValue(row, j, value);
                }
                row++;
            }
        } catch (NumberFormatException e) {
            LOGGER.error(e);
        }
    }

    public int getMatrixSize() {
        return matrixLines.size();
    }


}
