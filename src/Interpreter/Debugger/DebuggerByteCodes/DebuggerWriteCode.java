package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.WriteCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implemention of WriteCode
 * @author Sara Pulis
 */
public class DebuggerWriteCode extends WriteCode {

    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }

    public void execute(DebugVM virtualMachine) {
        if(virtualMachine.isTracing()) {
            virtualMachine.functionTrace("Write(" + virtualMachine.peekRunStack() + ")");
        }

        super.execute(virtualMachine);
    }

}
