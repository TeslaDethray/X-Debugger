package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.ByteCodes.HaltCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implementation of HaltCode
 * @author Sara Pulis
 */
public class DebuggerHaltCode extends HaltCode {

    public void execute(DebugVM virtualMachine) {
        super.execute(virtualMachine);
        virtualMachine.refresh();
        virtualMachine.setProgramCounter(-1);
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
