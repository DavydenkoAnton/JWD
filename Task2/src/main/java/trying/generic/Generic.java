package trying.generic;

public class Generic {
    public static void main(String[] args) {
        Sum sum = new Sum(5, 5);
        Multiply multiply = new Multiply(5, 5);
        Calc calc = new Calc();

        System.out.println(calc.result((CalcAction) sum));
        System.out.println(calc.result(multiply));

    }
}
