package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.Commands.StepOver;
import Interpreter.Debugger.DebugVM;

/**
 * UI implementation of StepOver.
 * @author Sara Pulis
 */
public class UIStepOver extends StepOver {

    @Override
    public void execute(DebugVM virtualMachine) {
        System.out.println("Stepping over...");
        super.execute(virtualMachine);
    }

}
