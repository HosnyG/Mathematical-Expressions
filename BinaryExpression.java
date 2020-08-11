import java.util.ArrayList;
import java.util.List;

/**
 * A BinaryExpression Class.
 * super class for expressions get two arguments.
 *
 * @author : Ganaiem Hosny
 */
public class BinaryExpression extends BaseExpression {
    private Expression e1;
    private Expression e2;


    /**
     * Constructor.
     * given two expression that current expression get.
     *
     * @param e1 :  first expression.
     * @param e2 : second expression.
     */
    public BinaryExpression(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param e :  expression.
     * @param n : number.
     */
    public BinaryExpression(Expression e, double n) {
        this.e1 = e;
        this.e2 = new Num(n);
    }

    /**
     * Constructor.
     * given expression and number that the current expression get.
     *
     * @param n :  number.
     * @param e : expression.
     */
    public BinaryExpression(double n, Expression e) {
        this.e1 = new Num(n);
        this.e2 = e;
    }

    /**
     * Constructor.
     * given two numbers that the current expression get.
     *
     * @param n1 :  first number.
     * @param n2 : second expression.
     */
    public BinaryExpression(double n1, double n2) {
        this.e1 = new Num(n1);
        this.e2 = new Num(n2);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param e :  expression.
     * @param v : variable.
     */
    public BinaryExpression(Expression e, String v) {
        this.e1 = e;
        this.e2 = new Var(v);
    }

    /**
     * Constructor.
     * given expression and variable that the current expression get.
     *
     * @param v :  variable.
     * @param e : expression.
     */
    public BinaryExpression(String v, Expression e) {
        this.e1 = new Var(v);
        this.e2 = e;
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param n :  number.
     * @param v : variable.
     */
    public BinaryExpression(double n, String v) {
        this.e1 = new Num(n);
        this.e2 = new Var(v);
    }

    /**
     * Constructor.
     * given number and variable that the current expression get.
     *
     * @param v :  variable.
     * @param n : number.
     */
    public BinaryExpression(String v, double n) {
        this.e1 = new Var(v);
        this.e2 = new Num(n);
    }

    /**
     * Constructor.
     * given two variables that the current expression get.
     *
     * @param v1 :  first variable.
     * @param v2 : second variable.
     */
    public BinaryExpression(String v1, String v2) {
        this.e1 = new Var(v1);
        this.e2 = new Var(v2);
    }

    /**
     * @return a list of the expressions that this expression held.
     */
    public List<Expression> getExpressions() {
        List<Expression> list = new ArrayList<>(); //initialize new array list.
        list.add(this.e1);
        list.add(this.e2);
        return list;
    }

    /**
     * @return a list of the variables in the expression.
     */
    public List<String> getVariables() {
        List<String> l1;
        List<String> l2;
        //get the variables in the two expression.
        l1 = this.e1.getVariables();
        l2 = this.e2.getVariables();
        l1.addAll(l2); //combine the two lists.
        //removes any variables appears more than one time.
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l1.size(); j++) {
                if (l1.get(i) == l1.get(j) && i != j) {
                    l1.remove(j);
                }
            }
        }
        return l1; //return final list of the variables.
    }

    /**
     * @return first expression.
     */
    Expression getE1() {
        return this.e1;
    }

    /**
     * @return second expression.
     */
    Expression getE2() {
        return this.e2;
    }
}
