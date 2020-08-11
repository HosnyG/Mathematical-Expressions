import java.util.Map;

/**
 * A log class.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Constructor.
     * given two expression that current expression get.
     *
     * @param e1 :  first expression.
     * @param e2 : second expression.
     */
    public Log(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param e :  expression.
     * @param n : number.
     */
    public Log(Expression e, double n) {
        super(e, n);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param n :  number.
     * @param e : expression.
     */
    public Log(double n, Expression e) {
        super(n, e);
    }

    /**
     * Constructor.
     * given two numbers that the current expression get.
     *
     * @param n1 :  first number.
     * @param n2 : second expression.
     */
    public Log(double n1, double n2) {
        super(n1, n2);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param e :  expression.
     * @param v : variable.
     */
    public Log(Expression e, String v) {
        super(e, v);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param v :  variable.
     * @param e : expression.
     */
    public Log(String v, Expression e) {
        super(v, e);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param n :  number.
     * @param v : variable.
     */
    public Log(double n, String v) {
        super(n, v);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param v :  variable.
     * @param n : number.
     */
    public Log(String v, double n) {
        super(v, n);
    }

    /**
     * Constructor.
     * given two variables that the current expression get.
     *
     * @param v1 :  first variable.
     * @param v2 : second variable.
     */
    public Log(String v1, String v2) {
        super(v1, v2);
    }

    /**
     * @return a nice string of the expression.
     */
    public String toString() {
        return "log(" + super.getE1().toString() + ", " + super.getE2().toString() + ")";
    }

    /**
     * evaulate this expression as Log.
     *
     * @return the result.
     * @throws Exception if there are variables in sub expressions.
     */
    public double evaluate() throws Exception {
        double base = super.getE1().evaluate();
        double number = super.getE2().evaluate();
        if ((base <= 0 || base == 1) && number <= 0) {
            throw new Exception("invalid base and number in log function!");
        } else if (base <= 0 || base == 1) {
            throw new Exception("invalid base in log function!");
        } else if (number <= 0) {
            throw new Exception("invalid number in log function!");
        } else {
            return Math.log(number) / Math.log(base);
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
        double base = super.getE1().evaluate(assignment);
        double number = super.getE2().evaluate(assignment);
        if (base <= 0 || (base == 1 && number != 1)) {
            throw new Exception("invalid base in log function!");
        } else if (number <= 0) {
            throw new Exception("invalid number in log function!");
        } else {
            return Math.log(number) / Math.log(base);
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
        return new Log(super.getE1().assign(var, expression), super.getE2().assign(var, expression));
    }

    /**
     * differentiation.
     *
     * @param var : variable differentiating relative.
     * @return (log ( f, g))' = 1/ g*log(e,f)
     */
    public Expression differentiate(String var) {
        Expression base = super.getE1();
        Expression number = super.getE2();
        return new Mul(new Div(new Num(1), new Mul(number, new Log("e", base))), number.differentiate(var));
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
            double result = Math.log(newE2.evaluate()) / Math.log(newE1.evaluate());
            return new Num(result);
        } catch (Exception e) {
            ignoreException();
        }
        // log(x,x)=1
        if (newE1.toString().equals(newE2.toString())) {
            return new Num(1);
        }
        return new Log(newE1, newE2);
    }

}
