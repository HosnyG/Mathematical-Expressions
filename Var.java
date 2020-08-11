import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Variable Class.
 */
public class Var implements Expression {
    private String variable;

    /**
     * Constructor.
     *
     * @param v : variable string.
     */
    public Var(String v) {
        this.variable = v;
    }

    /**
     * Evaluate the expression using the variable values.
     * provided in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception if this variables is not e or Pi or not contained in the assignment.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else if (this.variable.equals("e") && !assignment.containsKey("e")) {
            return Math.E;
        } else if (this.variable.equals("Pi") && !assignment.containsKey("Pi")) {
            return Math.PI;
        } else {
            throw new Exception("there are variables did not assign!");
        }
    }

    /**
     * A convenience method.
     * Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * if the variable is e or pi evaluate as it's known value , else
     * throw exception .
     *
     * @return the result if the variable e or pi else throw exception.
     * @throws Exception if this variable are not e or pi .
     */
    public double evaluate() throws Exception {
        if (this.variable.equals("e")) {
            return Math.E;
        } else if (this.variable.equals("Pi")) {
            return Math.PI;
        } else {
            throw new Exception("there are variables did not assign!");
        }
    }

    /**
     * @return a list contain this variable.
     */
    public List<String> getVariables() {
        List l1 = new ArrayList();
        l1.add(this.variable);
        return l1;

    }

    /**
     * @return a variable string.
     */
    public String toString() {
        return this.variable;
    }


    /**
     * replace var with expression.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression .
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return the new Expression.
     */
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * @return a list contain this variable.
     */
    public List<Expression> getExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this);
        return list;
    }

    /**
     * differentiation.
     *
     * @param var : variable differentiating relative.
     * @return 1 if this variable equal to given var , 0 else.
     */
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * simplify.
     * @return same variable.
     */
    public Expression simplify() {
        return this;
    }


}
