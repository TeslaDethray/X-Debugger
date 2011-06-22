package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.ByteCodes.*;
import Interpreter.Debugger.*;
import Interpreter.VirtualMachine;

/**
 * Debugger version of ReturnCode
 * RETURN &lt;funcname>
 *      -Return from the current function;
 *      -&lt;funcname> is used as a comment to indicate the current function
 *      -RETURN is generated for intrinsic funcitons
 * @author Sara Pulis
 */
public class DebuggerReturnCode extends ReturnCode {

    /**
     * Return from the current function, saves the return value.
     * @param virtualMachine Caller of the function.
     */
    public void execute(DebugVM virtualMachine) {
        if(virtualMachine.isTracing()) {
            virtualMachine.functionTrace("exit: " + virtualMachine.FERgetFunctionName() + ": " + virtualMachine.peekRunStack());
        }
        virtualMachine.popFER();
        super.execute(virtualMachine);
    }

    /**
     * Overloaded execute method
     * @param virtualMachine Caller of this method
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }
}
