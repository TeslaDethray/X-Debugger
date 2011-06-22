package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * LIT n - load the *literal value* n
 *  LIT  0 i - tis form of the Lit was generated to load 0 on the stack in
 *      order to initialize the variable i to 0 and reserve space on the
 *      runtime stack for i.
 * @author Sara Pulis
 */
public class LitCode extends ByteCode {

    protected String literalName = null; //Name of the variable, if given.
    protected int literal; //Value of the variable

    /**
     * Constructor. Does nothing.
     */
    public LitCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        Integer integerArg = Integer.parseInt(arg);
        literal = integerArg.intValue();
        if (args.size() > 1) {
            literalName = (String)args.get(1);
        }
    }

    /**
     * Execute method. Does nothing.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.pushRunStack(literal);
    }

    public String toString() {
        String outString = ("LIT " + literal);
        if (literalName != null) {
            outString += (" int " + literalName);
        }
        return outString;
    }
}
