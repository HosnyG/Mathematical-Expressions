import java.util.Map;

/**
 * Sin class.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructor.
     * given Expression that current expression get.
     *
     * @param e : expression.
     */
    public Sin(Expression e) {
        super(e);
    }

    /**
     * Constructor.
     * given number that current expression get.
     *
     * @param n : number.
     */
    public Sin(double n) {
        super(n);
    }

    /**
     * Constructor.
     * given variable string that current expression get.
     *
     * @param v : variable string.
     */
    public Sin(String v) {
        super(v);
    }

    /**
     * @return this expression as string.
     */
    public String toString() {
        return "sin(" + super.getE().toString() + ")";
    }

    /**
     * evaluate expression as by sin.
     *
     * @return the result.
     * @throws Exception ignored.
     */
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(super.getE().evaluate()));
    }

    /**
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception ignored.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(super.getE().evaluate(assignment)));
    }

    /**
     * assign expression to specified value.
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return new Expression after assignment.
     */
    public Expression assign(String var, Expression expression) {
        return new Sin(super.getE().assign(var, expression));
    }

    /**
     * @param var : variable differentiating relative.
     * @return sin(f ( x))' = cos(f(x))' * f(x)'
     */
    public Expression differentiate(String var) {
        return new Mul(new Cos(super.getE()), super.getE().differentiate(var));
    }

    /**
     * @return a simplified expression.
     */
    public Expression simplify() {
        Expression newE1 = super.getE().simplify();

        // if have number , evaluate the expression.
        try {
            double result = Math.sin(Math.toRadians(newE1.evaluate()));
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        return new Sin(newE1);
    }


}
