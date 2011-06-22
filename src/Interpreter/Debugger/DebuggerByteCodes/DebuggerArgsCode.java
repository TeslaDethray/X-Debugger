package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.ArgsCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implementation of ArgsCode
 * @author Sara Pulis
 */
public class DebuggerArgsCode extends ArgsCode {

    public void execute(DebugVM virtualMachine) {
        super.execute(virtualMachine);
        virtualMachine.setNumOfArgs(newFrameLocation);
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
