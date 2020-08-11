import java.util.ArrayList;
import java.util.List;

/**
 * A Unary Expression Class.
 * super class for expressions get one arguments.
 *
 * @author : Ganaiem Hosny
 */
public class UnaryExpression extends BaseExpression {

    private Expression e;

    /**
     * Constructor.
     * expression that current expression get.
     *
     * @param e :  Expression.
     */
    public UnaryExpression(Expression e) {
        this.e = e;
    }

    /**
     * Constructor.
     * number that current expression get.
     *
     * @param n :  number.
     */
    public UnaryExpression(double n) {
        this.e = new Num(n);
    }

    /**
     * Constructor.
     * variable string that current expression get.
     *
     * @param v :  variable string.
     */
    public UnaryExpression(String v) {
        this.e = new Var(v);
    }

    /**
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> l1; //initialize new list.
        l1 = this.e.getVariables();
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l1.size(); j++) {
                if (l1.get(i) == l1.get(j) && i != j) { //remove variable appears more than one time.
                    l1.remove(j);
                }
            }
        }
        return l1;
    }

    /**
     * @return a list of the expression that this expression held.
     */
    public List<Expression> getExpressions() {
        List<Expression> list = new ArrayList<>();
        list.add(this.e);
        return list;
    }

    /**
     * @return expression.
     */
    Expression getE() {
        return this.e;
    }
}
