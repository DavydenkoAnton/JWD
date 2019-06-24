package by.javatr.threads.bean;

public class Matrix {

    private static Matrix instance = new Matrix();
    private int size;
    private int MIN_SIZE = 8;
    private int MAX_SIZE = 12;
    private int[][] matrix;

    private Matrix() {
        matrix = new int[MIN_SIZE][MIN_SIZE];
    }

    public static Matrix getInstance() {
        return instance;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        matrix = new int[size][size];
    }

    public int getMIN_SIZE() {
        return MIN_SIZE;
    }

    public void setMIN_SIZE(int MIN_SIZE) {
        this.MIN_SIZE = MIN_SIZE;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public void setMAX_SIZE(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
    }

    public void setValue(int i, int j, int value) {
        matrix[i][j] = value;
    }

    public int getValue(int i, int j) {
        return matrix[i][j];
    }

    public boolean isDiagonalFilled() {
        int count=0;
        for (int i = 0; i <matrix.length ; i++) {
            if(matrix[i][i]!=0){
                count++;
            }else {
                break;
            }
        }
        if(count==matrix.length){
            return true;
        }
        return false;
    }
}
