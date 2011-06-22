package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * POP n: Pop the top n levels of runtime stack
 * @author Sara Pulis
 */
public class PopCode extends ByteCode {

    protected int popNumber; //Stored number of items to pop

    /**
     * Constructor. Does nothing.
     */
    public PopCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        Integer integerArg = Integer.parseInt(arg);
        popNumber = integerArg;
    }

    /**
     * Pops the initialized number of items off of the stack.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        for (int i = 1; i <= popNumber; i++) {
            virtualMachine.popRunStack();
        }
    }

    public String toString() {
        return ("POP " + popNumber);
    }
}
