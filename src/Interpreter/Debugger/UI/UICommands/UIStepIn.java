package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.Commands.StepIn;
import Interpreter.Debugger.DebugVM;

/**
 * UI implementation of StepIn
 * @author Sara Pulis
 */
public class UIStepIn extends StepIn {

    @Override
    public void execute(DebugVM virtualMachine) {
        System.out.println("Stepping in...");
        super.execute(virtualMachine);
    }

}