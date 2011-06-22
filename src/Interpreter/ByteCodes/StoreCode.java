package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * STORE n &lt;id> -Stores the top item from the stack into the desired location.
 *      n - place to store the item
 *      &ltid> - variable denoting the item.
 * @author Sara Pulis
 */
public class StoreCode extends ByteCode {

    private int offset, //Place to store the variable.
            value = 0;  //Value of the variable, initialized to 0.
    protected String id;  //Name of the variable.

    /**
     * Constructor. Does nothing.
     */
    public StoreCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        offset = Integer.parseInt(arg);
        if (args.size() > 1) {
            id = (String)args.get(1);
        }
    }

    /**
     * Stores the top item from the stack into the desired location
     * @param virtualMachine Caller of the function.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        value = virtualMachine.runStackStore(offset);
    }

    public String toString() {
        return ("STORE " + id + "     " + id + " = " + value);
    }
}
