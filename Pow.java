import java.util.Map;

/**
 * A power class.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * given two expression that current expression get.
     *
     * @param e1 :  first expression.
     * @param e2 : second expression.
     */
    public Pow(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param e :  expression.
     * @param n : number.
     */
    public Pow(Expression e, double n) {
        super(e, n);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param n :  number.
     * @param e : expression.
     */
    public Pow(double n, Expression e) {
        super(n, e);
    }

    /**
     * Constructor.
     * given two numbers that the current expression get.
     *
     * @param n1 :  first number.
     * @param n2 : second expression.
     */
    public Pow(double n1, double n2) {
        super(n1, n2);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param e :  expression.
     * @param v : variable.
     */
    public Pow(Expression e, String v) {
        super(e, v);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param v :  variable.
     * @param e : expression.
     */
    public Pow(String v, Expression e) {
        super(v, e);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param n :  number.
     * @param v : variable.
     */
    public Pow(double n, String v) {
        super(n, v);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param v :  variable.
     * @param n : number.
     */
    public Pow(String v, double n) {
        super(v, n);
    }

    /**
     * Constructor.
     * given two variables that the current expression get.
     *
     * @param v1 :  first variable.
     * @param v2 : second variable.
     */
    public Pow(String v1, String v2) {
        super(v1, v2);
    }

    /**
     * @return a nice string of the expression.
     */
    public String toString() {
        return "(" + super.getE1().toString() + "^" + super.getE2().toString() + ")";
    }

    /**
     * evaulate this expression as Power.
     *
     * @return the result.
     * @throws Exception if there are variables in sub expressions.
     */
    public double evaluate() throws Exception {
        return Math.pow(super.getE1().evaluate(), super.getE2().evaluate());
    }

    /**
     * evaulate this expression according to given assignment.
     *
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception if there are variables don't appears in thr given assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(super.getE1().evaluate(assignment), super.getE2().evaluate(assignment));
    }

    /**
     * replace given variable with specified expression.
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return the new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Pow(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
    }

    /**
     * differentiation.
     *
     * @param var : variable differentiating relative.
     * @return (f ^ g)' = (f^g) * ( (f' *g/f) + g'*log(e,f))
     */
    public Expression differentiate(String var) {
        Expression base = super.getE1();
        Expression exponent = super.getE2();
        return new Mul(new Pow(base, exponent),
                new Plus(new Mul(base.differentiate(var), new Div(exponent, base))
                        , new Mul(exponent.differentiate(var), new Log("e", base))));
    }

    /**
     * simplification.
     * simplify first the sub expression.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        Expression newE1 = super.getE1().simplify();
        Expression newE2 = super.getE2().simplify();

        // if the simplified sub expression are number ,evaluate.
        try {
            double result = Math.pow(newE1.evaluate(), newE2.evaluate());
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        return new Pow(newE1, newE2);
    }


}
