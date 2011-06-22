package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.Debugger.*;
import java.util.Vector;

/**
 * LINE n
 *      n - an int representing the line number the original source code
 *          from which this ByteCode was generated.
 * @author Sara Pulis
 */
public class DebuggerLineCode extends DebuggerByteCode {

    private int thisLine;

    /**
     * Constructor. Does nothing.
     */
    public DebuggerLineCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        thisLine = Integer.parseInt(arg);
    }

    /**
     * Returns this lineCode
     * @return this LineCode
     */
    public DebuggerLineCode getLine() {
        return this;
    }

    /**
     * Returns the int denoting the numeric location of this line
     * @return the int denoting the numeric location of this line
     */
    public int getLineNumber() {
        return thisLine;
    }

    /**
     * Sets the current FunctionEnvironmentRecord's current line to this
     * LineCode
     * @param virtualMachine the caller of this function
     */
    public void execute(DebugVM virtualMachine) {
        virtualMachine.FERsetCurrentLine(getLineNumber());
    }

    public String toString() {
        return ("LINE " + getLineNumber());
    }

}
