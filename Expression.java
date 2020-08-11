import java.util.Map;
import java.util.List;

/**
 * A Expression interface.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values.
     * provided in the assignment, and return the result.  If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     *
     * @param assignment : variable values.
     * @return the result.
     * @throws Exception if there are variable in the expression did'nt assign.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * A convenience method.
     * Like the `evaluate(assignment)` method above, but uses an empty assignment.
     *
     * @return the result.
     * @throws Exception if there are variables.
     */
    double evaluate() throws Exception;


    /**
     * @return a list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * @return a nice string representation of the expression.
     */
    String toString();

    /**
     * replace var with expression.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the
     * current expression).
     *
     * @param var        : the variable we will replaced.
     * @param expression : expression that var will be replaced with.
     * @return the new Expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Automatic Differentitation.
     *
     * @param var : variable differentiating relative.
     * @return the expression resulting from differentiating the current expression relative to variable `var`.
     */
    Expression differentiate(String var);

    /**
     * @return Returned a simplified version of the current expression.
     */
    Expression simplify();

    /**
     * @return a list of the expressions that this expression held.
     */
    List<Expression> getExpressions();


}