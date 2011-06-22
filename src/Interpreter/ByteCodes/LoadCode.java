package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * LOAD n &lt;id> - pop the top of the stack; store value in the *offset n from
 *      the start of the frame* from the top of the stack; &lt;id> is used as a
 *      comment, it's the variable name from which the data is loaded.
 * @author Sara Pulis
 */
public class LoadCode extends ByteCode {

    private int offset; //Number of places from the start of the frame to place the popped top of the stack.
    protected String id; //Name of the variable for the popped/placed item.

    /**
     * Constructor. Does nothing.
     */
    public LoadCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        offset = Integer.parseInt(arg);
        id = (String)args.get(1);
    }

    /**
     * Pops the top of the stack; store value in the offset n from
     * the start of the frame from the top of the stack
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.runStackLoad(offset);
    }

    public String toString() {
        return ("LOAD " + id + "     <load " + id + ">");
    }
}
