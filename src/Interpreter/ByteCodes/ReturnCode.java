package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * RETURN &lt;funcname>
 *      -Return from the current function;
 *      -&lt;funcname> is used as a comment to indicate the current function
 *      -RETURN is generated for intrinsic funcitons
 * @author Sara Pulis
 */
public class ReturnCode extends ByteCode {

    protected String funcName = null; //Name of the function
    protected String editedFuncName = null; //Cleaned-up version of the name of the function
    protected int returnValue; //The value which the function being returned from has generated.

    /**
     * Constructor. Does nothing.
     */
    public ReturnCode() {}

    public void init(Vector args) {
        if (args.size() != 0) {
            funcName = (String)args.get(0);
        }
    }

    /**
     * Return from the current function, saves the return value.
     * @param virtualMachine Caller of the function.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.returnCounterPop();
        editedFuncName = virtualMachine.retrieveCurrentFunctionName();
        returnValue = virtualMachine.peekRunStack();
    }

    public String toString() {
        if (funcName == null) {
            return ("RETURN     " + editedFuncName + ":" + returnValue);
        } else {
            return ("RETURN " + funcName + "     " + editedFuncName + ":" + returnValue);
        }
    }
}
