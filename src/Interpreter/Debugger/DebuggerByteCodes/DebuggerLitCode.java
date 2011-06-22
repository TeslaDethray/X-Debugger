package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.*;
import Interpreter.Debugger.*;
import Interpreter.VirtualMachine;

/**
 * Debugger version of LitCode
 * LIT n - load the *literal value* n
 *  LIT  0 i - tis form of the Lit was generated to load 0 on the stack in
 *      order to initialize the variable i to 0 and reserve space on the
 *      runtime stack for i.
 * @author Sara Pulis
 */
public class DebuggerLitCode extends LitCode {

    /**
     * Pushes the previously given value onto the stack.
     * Adds the literal, given it has a name, into the FER
     * @param virtualMachine Caller of this method.
     */
    public void execute(DebugVM virtualMachine) {
        if (literalName != null) {
            virtualMachine.FERaddLiteral(literalName, literal);
        }
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
