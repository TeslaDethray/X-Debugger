package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.LoadCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implemention of LoadCode
 * @author Sara Pulis
 */
public class DebuggerLoadCode extends LoadCode {

    /**
     * Pushes the previously given value onto the stack.
     * Adds the literal, given it has a name, into the FER
     * @param virtualMachine Caller of this method.
     */
    public void execute(DebugVM virtualMachine) {
        super.execute(virtualMachine);
        virtualMachine.FERaddLiteral(id, virtualMachine.peekRunStack());
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
