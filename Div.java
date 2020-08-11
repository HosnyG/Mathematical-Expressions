import java.util.Map;

/**
 * A divide class.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * given two expression that current expression get.
     *
     * @param e1 :  first expression.
     * @param e2 : second expression.
     */
    public Div(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param e :  expression.
     * @param n : number.
     */
    public Div(Expression e, double n) {
        super(e, n);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param n :  number.
     * @param e : expression.
     */
    public Div(double n, Expression e) {
        super(n, e);
    }

    /**
     * Constructor.
     * given two numbers that the current expression get.
     *
     * @param n1 :  first number.
     * @param n2 : second expression.
     */
    public Div(double n1, double n2) {
        super(n1, n2);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param e :  expression.
     * @param v : variable.
     */
    public Div(Expression e, String v) {
        super(e, v);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param v :  variable.
     * @param e : expression.
     */
    public Div(String v, Expression e) {
        super(v, e);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param n :  number.
     * @param v : variable.
     */
    public Div(double n, String v) {
        super(n, v);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param v :  variable.
     * @param n : number.
     */
    public Div(String v, double n) {
        super(v, n);
    }

    /**
     * Constructor.
     * given two variables that the current expression get.
     *
     * @param v1 :  first variable.
     * @param v2 : second variable.
     */
    public Div(String v1, String v2) {
        super(v1, v2);
    }

    /**
     * @return a nice string of the expression.
     */
    public String toString() {
        return "(" + super.getE1().toString() + " / " + super.getE2().toString() + ")";

    }

    /**
     * evaulate this expression as divide.
     *
     * @return the result.
     * @throws Exception if there are variables in sub expressions.
     */
    public double evaluate() throws Exception {
        double denominator = super.getE2().evaluate();
        double numerator = super.getE1().evaluate();
        if (denominator == 0 || (denominator == 0 && numerator == 0)) {
            throw new Exception("Divided by Zero!");
        } else {
            return numerator / denominator;
        }
    }

    /**
     * evaulate this expression according to given assignment.
     *
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception if there are variables don't appears in thr given assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double denominator = super.getE2().evaluate(assignment);
        double numerator = super.getE1().evaluate(assignment);
        if (denominator == 0 || (denominator == 0 && numerator == 0)) {
            throw new Exception("Divided by Zero!");
        } else {
            return numerator / denominator;
        }

    }

    /**
     * replace given variable with specified expression.
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return the new expression.
     */
    public Expression assign(String var, Expression expression) {
        return new Div(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
    }

    /**
     * differentiation.
     *
     * @param var : variable differentiating relative.
     * @return (fx)/g(x))' = (f(x)'g(x)-f(x)g(x)') / g(x)^2
     */
    public Expression differentiate(String var) {
        Expression numerator = super.getE1();
        Expression denominator = super.getE2();

        return new Div(new Minus(new Mul(numerator.differentiate(var), denominator)
                , new Mul(denominator.differentiate(var), numerator)), new Pow(denominator, 2));

    }

    /**
     * simplification.
     * simplify first the sub expression.
     *
     * @return simplified expression.
     */
    public Expression simplify() {
        Expression numerator = super.getE1().simplify();
        Expression denominator = super.getE2().simplify();
        // if the simplified sub expression are number ,evaluate.
        try {
            double result = numerator.evaluate() / denominator.evaluate();
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        // x/1=x
        try {
            if (denominator.evaluate() == 1) {
                return numerator;
            }
        } catch (Exception e) {
            ignoreException();
        }
        // x/x=1
        if (numerator.toString().equals(denominator.toString())) {
            return new Num(1);
        }
        return new Div(numerator, denominator);
    }
}
