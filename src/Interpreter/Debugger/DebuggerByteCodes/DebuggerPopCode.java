package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.ByteCodes.*;
import Interpreter.Debugger.*;
import Interpreter.VirtualMachine;

/**
 * Debugger version of PopCode
 * POP n: Pop the top n levels of runtime stack
 * @author Sara Pulis
 */
public class DebuggerPopCode extends PopCode {

    /**
     * Pops the initialized number of items off of the stack.
     * Pops literals from the FER
     * @param virtualMachine Caller of this method.
     */
    public void execute(DebugVM virtualMachine) {
        virtualMachine.FERpopLiterals(popNumber);
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
