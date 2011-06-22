package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.Debugger.*;
import java.util.Stack;
import java.util.Vector;

/**
 * FORMAL name offset
 *      name - a String representing the name of the formal
 *      offset - an int representing the offset of this formal
 * @author Sara Pulis
 */
public class DebuggerFormalCode extends DebuggerByteCode {

    private String formalName;
    private int offset;

    /**
     * Constructor. Does nothing.
     */
    public DebuggerFormalCode() {}

    public void init(Vector args) {
        formalName = (String)args.get(0);
        String arg = (String)args.get(1);
        offset = Integer.parseInt(arg);
    }

    /***
     * Returns the String denoting the name of the formal
     * @return String denoting the name of the formal
     */
    public String getFormalName() {
        return formalName;
    }

    /**
     * Returns the int denoting the offset
     * @return int denoting the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * Execute method. Does nothing.
     * @param virtualMachine Caller of the method
     */
    @Override
    public void execute(DebugVM virtualMachine) {
        Stack<Integer> runStackHolder = new Stack();
        for (int i = 0; i < offset; i++) {
            runStackHolder.add(virtualMachine.popRunStack());
        }
        virtualMachine.FERaddLiteral(formalName, virtualMachine.peekRunStack());
        for (int i = 0; i < offset; i++) {
            virtualMachine.pushRunStack(runStackHolder.pop());
        }
    }

    public String toString() {
        return ("FORMAL " + getFormalName() + " " + getOffset());
    }

}
