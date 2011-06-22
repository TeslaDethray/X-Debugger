package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.StoreCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implemention of StoreCode
 * @author Sara Pulis
 */
public class DebuggerStoreCode extends StoreCode {

    /**
     * Pushes the previously given value onto the stack.
     * Adds the literal, given it has a name, into the FER
     * @param virtualMachine Caller of this method.
     */
    public void execute(DebugVM virtualMachine) {
        virtualMachine.FERaddLiteral(id, virtualMachine.peekRunStack());
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
