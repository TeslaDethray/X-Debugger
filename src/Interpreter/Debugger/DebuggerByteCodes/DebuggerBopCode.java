package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.BopCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;

/**
 * Debugger implementation of BopCode.
 * @author Sara Pulis
 */
public class DebuggerBopCode extends BopCode {

    private Exception ArithmeticException;

    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }
    
    public void execute(DebugVM virtualMachine) {
        int second = virtualMachine.popRunStack();
        long result = super.execute(virtualMachine.popRunStack(), second);
        try{
            if ((result < -2147483648) || (result > 2147483647)) {
                throw ArithmeticException;
            } else {
                virtualMachine.pushRunStack((int)result);
            }
        } catch (Exception e) {
            System.out.println("Your result is outside of the int type's bounds of -2147483648 and 2147483647.");
            System.out.println("Starting the program over...");
            virtualMachine.refresh();
            virtualMachine.setProgramCounter(-1);
        }
    }

}
