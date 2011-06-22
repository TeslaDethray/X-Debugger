package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.Commands.StepOut;
import Interpreter.Debugger.DebugVM;

/**
 * Steps out of the current function's activaiton.
 * (i.e. Returns to its caller.)
 * @author Sara Pulis
 */
public class UIStepOut extends StepOut{

    @Override
    public void execute(DebugVM virtualMachine) {
        System.out.println("Stepping out...");
        super.execute(virtualMachine);
    }

}
