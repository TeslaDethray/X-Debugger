package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.ByteCodes.*;
import Interpreter.Debugger.*;
import Interpreter.VirtualMachine;


/**
 * Debugger extention of ByteCode.
 * @author Sara Pulis
 */
public abstract class DebuggerByteCode extends ByteCode {
    public abstract void execute(DebugVM virtualMachine);

         /**
     * Overloaded execute method
     * @param virtualMachine Caller of this method
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }
}