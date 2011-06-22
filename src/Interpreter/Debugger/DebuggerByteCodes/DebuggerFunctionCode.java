package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.Debugger.*;
import java.util.Vector;

/**
 * FUNCTION name start end
 *      name - name of the funciton
 *      start - the line number this funciton starts on
 *      end - the line number this function ends on
 * @author Sara Pulis
 */
public class DebuggerFunctionCode extends DebuggerByteCode {

    private String functionName;
    private int startLine, endLine;

    /**
     * Constructor. Does nothing.
     */
    public DebuggerFunctionCode() {}

    public void init(Vector args) {
        functionName = (String)args.get(0);
        String arg = (String)args.get(1);
        startLine = Integer.parseInt(arg);
        arg = (String)args.get(2);
        endLine = Integer.parseInt(arg);
    }

    /**
     * Returns a String denoting the name of this function
     * @return a String denoting the name of this function
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Returns the start line number
     * @return int representing the start line number
     */
    public int getStartLine() {
        return startLine;
    }

     /**
     * Returns the ending line number
     * @return int representing the ending line number
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     * Fills in the start, end, and name fields of the current
     * FunctionEnvironmentRecord.
     * @param virtualMachine the caller of the method
     */
    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.FETsetFunctionName(getFunctionName());
        virtualMachine.FETsetFunctionStartLine(getStartLine());
        virtualMachine.FETsetFunctionEndLine(getEndLine());
        }

    public String toString() {
        return ("FUNCTION " + getFunctionName() + " " + getStartLine() + " " + getEndLine());
    }


}
