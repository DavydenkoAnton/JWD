package trying.generic;

public class Calc<T extends CalcAction> {
    public Calc() {
    }

    public int result(T action){
        return action.result();
    }
}
