package trying.generic;

public class Multiply implements CalcAction {
    int a;
    int b;

    public Multiply(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int result(){
        return a*b;
    }

}
