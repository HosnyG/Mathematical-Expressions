import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A number class.
 * that hold double.
 */
public class Num implements Expression {
    private double num;

    /**
     * Constructor.
     *
     * @param n : number.
     */
    public Num(double n) {
        this.num = n;
    }


    /**
     * evaluate.
     *
     * @param assignment : variable values, ignored here.
     * @return this number.
     */
    public double evaluate(Map<String, Double> assignment) {
        return this.num;
    }

    /**
     * @return this number.
     */
    public double evaluate() {
        return this.num;
    }

    /**
     * return list of variables in the expression.
     *
     * @return empty list.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>(); //an empty list
        return list;
    }

    /**
     * @return number's string.
     */
    public String toString() {
        return Double.toString(this.num);
    }

    /**
     * @param var        : the variable we will replaced. ignored here.
     * @param expression : expression that var will be replaced with. ignored here.
     * @return this number.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * @return a list contain this number.
     */
    public List<Expression> getExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    /**
     * Differentiation.
     *
     * @param var : variable differentiating relative.
     * @return always 0;
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * @return the same number.
     */
    public Expression simplify() {
        return this;
    }


}
