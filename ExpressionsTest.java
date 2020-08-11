import java.util.Map;
import java.util.TreeMap;

/**
 * An Expression test class.
 */
public class ExpressionsTest {

    /**
     * main function.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        try {
            Expression e = new Plus(new Plus(new Mul(2, "x"), new Sin(new Mul(4, "y"))), new Pow("e", "x"));
            System.out.println(e);
            Map<String, Double> assignment = new TreeMap<String, Double>();
            assignment.put("x", 2.0);
            assignment.put("y", 0.25);
            assignment.put("e", 2.71);
            double value = e.evaluate(assignment);
            System.out.println("The result is: " + value);
            System.out.println(e.differentiate("x"));
            System.out.println(e.differentiate("x").evaluate(assignment));
            System.out.println(e.differentiate("x").simplify());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
