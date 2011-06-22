package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.Commands.ContinueExecution;
import Interpreter.Debugger.DebugVM;

/**
 * UI Implementation of Continue Execution command.
 * @author Sara Pulis
 */
public class UIContinueExecution extends ContinueExecution {

    @Override
    public void execute(DebugVM virtualMachine) {
        System.out.println("Executing...");
        super.execute(virtualMachine);
    }

}
