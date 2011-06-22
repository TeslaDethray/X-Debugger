package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * bop &lt;binary op> - *pop top 2 levels* of the stack and perform indicated
 *      operation - operations are + - / * == != &lt;= &gt; &gt;= &lt; | &
 *          | and & are logical operators, not bit operators
 *          lower level is the first operand:
 *          e.g. &lt;second-level> + &lt;top-level>
 * @author Sara Pulis
 */
public class BopCode extends ByteCode {

    private String operand; //Holds the operand to be used.
    private Exception ArithmeticException;

    /**
     * Constructor. Does nothing.
     */
    public BopCode() {}

    public void init(Vector args) {
        operand = (String)args.get(0);
    }

    /**
     * Carries out the previously asked-for operation.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        int second = virtualMachine.popRunStack();
        long result = execute(virtualMachine.popRunStack(), second);
        try{
            if ((result < -2147483648) || (result > 2147483647)) {
                throw ArithmeticException;
            } else {
                virtualMachine.pushRunStack((int)result);
            }
        } catch (Exception e) {
            System.out.println("Your result is outside of the int type's bounds of -2147483648 and 2147483647.");
            System.exit(1);
        }
    }

    protected long execute(int firstOperand, int secondOperand) {
         Hashtable<String, Long> operHash = new Hashtable();
        operHash.put("+", addOperator(firstOperand, secondOperand));
        operHash.put("-", subOperator(firstOperand, secondOperand));
        operHash.put("*", multOperator(firstOperand, secondOperand));
        operHash.put("/", divOperator(firstOperand, secondOperand));
        operHash.put("==", equalOperator(firstOperand, secondOperand));
        operHash.put("!=", unequalOperator(firstOperand, secondOperand));
        operHash.put("<=", ltequalOperator(firstOperand, secondOperand));
        operHash.put("<", ltOperator(firstOperand, secondOperand));
        operHash.put(">=", gtequalOperator(firstOperand, secondOperand));
        operHash.put(">", gtOperator(firstOperand, secondOperand));
        operHash.put("&", bitAndOperator(firstOperand, secondOperand));
        operHash.put("|", bitOrOperator(firstOperand, secondOperand));
        return operHash.get(operand).longValue();
    }

    public String toString() {
        return ("BOP " + operand);
    }

    /**
     * Carries out the addition operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long addOperator(int firstOperand, int secondOperand) {
        return (long)firstOperand + (long)secondOperand;
    }

    /**
     * Carries out the subtraction operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long subOperator(int firstOperand, int secondOperand) {
        return (long)firstOperand - (long)secondOperand;
    }

    /**
     * Carries out the multiplication operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long multOperator(int firstOperand, int secondOperand) {
        return (long)firstOperand * (long)secondOperand;
    }

    /**
     * Carries out the division operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long divOperator(int firstOperand, int secondOperand) {
        return (long)firstOperand / (long)secondOperand;
    }

    /**
     * Carries out the equality operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long equalOperator(int firstOperand, int secondOperand) {
        if (firstOperand == secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Carries out the inequality operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long unequalOperator(int firstOperand, int secondOperand) {
        if (firstOperand != secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

     /**
     * Carries out the less-than-or-equal-to operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long ltequalOperator(int firstOperand, int secondOperand) {
        if (firstOperand <= secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Carries out the less-than operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long ltOperator(int firstOperand, int secondOperand) {
        if (firstOperand < secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Carries out the greater-than-or-equal-to operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long gtequalOperator(int firstOperand, int secondOperand) {
        if (firstOperand >= secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Carries out the greater-than operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long gtOperator(int firstOperand, int secondOperand) {
        if (firstOperand > secondOperand) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Carries out the logical AND operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long bitAndOperator(int firstOperand, int secondOperand) {
        return (firstOperand & secondOperand);
    }

    /**
     * Carries out the logical OR operation.
     * @param firstOperand
     * @param secondOperand
     * @return Returns the result of the operation
     */
    private long bitOrOperator(int firstOperand, int secondOperand) {
        return (firstOperand | secondOperand);
    }
}