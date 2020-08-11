import java.util.Map;

/**
 * Cos class.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Constructor.
     * given Expression that current expression get.
     *
     * @param e : expression.
     */
    public Cos(Expression e) {
        super(e);
    }

    /**
     * Constructor.
     * given number that current expression get.
     *
     * @param n : number.
     */
    public Cos(double n) {
        super(n);
    }

    /**
     * Constructor.
     * given variable string that current expression get.
     *
     * @param v : variable string.
     */
    public Cos(String v) {
        super(v);
    }

    /**
     * @return this expression as string.
     */
    public String toString() {
        return "cos(" + super.getE().toString() + ")";
    }

    /**
     * evaluate expression as by cos.
     *
     * @return the result.
     * @throws Exception ignored.
     */
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(super.getE().evaluate()));
    }

    /**
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception ignored.
     */

    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(super.getE().evaluate(assignment)));
    }

    /**
     * assign expression to specified value.
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Cos(super.getE().assign(var, expression));
    }

    /**
     * @param var : variable differentiating relative.
     * @return (cos ( f ( x)))'= -sin(f(x))*(f(x))'.
     */
    public Expression differentiate(String var) {
        return new Mul(new Neg(new Sin(super.getE())), super.getE().differentiate(var));
    }

    /**
     * @return a simplified expression.
     */
    public Expression simplify() {
        Expression newE1 = super.getE().simplify();

        // if have number , evaluate the expression.
        try {
            double result = Math.cos(Math.toRadians(newE1.evaluate()));
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        return new Cos(newE1);
    }

}
