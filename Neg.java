import java.util.Map;

/**
 * Negate class.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor.
     * given Expression that current expression get.
     *
     * @param e : expression.
     */
    public Neg(Expression e) {
        super(e);
    }

    /**
     * Constructor.
     * given number that current expression get.
     *
     * @param n : number.
     */
    public Neg(double n) {
        super(n);
    }

    /**
     * Constructor.
     * given variable string that current expression get.
     *
     * @param v : variable string.
     */
    public Neg(String v) {
        super(v);
    }

    /**
     * @return expression as string.
     */
    public String toString() {
        return "(-" + super.getE().toString() + ")";
    }

    /**
     * evaluate expression as by cos.
     *
     * @return the result.
     * @throws Exception ignored.
     */
    public double evaluate() throws Exception {
        return -1 * super.getE().evaluate();
    }

    /**
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception ignored.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -super.getE().evaluate(assignment);
    }

    /**
     * assign expression to specified value.
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getE().assign(var, expression));
    }

    /**
     * @param var : variable differentiating relative.
     * @return (- f ( x))'= -(f(x)')
     */
    public Expression differentiate(String var) {
        return new Neg(super.getE().differentiate(var));

    }

    /**
     * @return a simplified expression.
     */
    public Expression simplify() {
        Expression newE1 = super.getE().simplify(); // a simplified expression

        // if have number , evaluate the expression.
        try {
            double result = -1 * newE1.evaluate();
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        return new Neg(newE1);
    }

}
