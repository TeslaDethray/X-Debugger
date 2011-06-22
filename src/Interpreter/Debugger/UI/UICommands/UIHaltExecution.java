package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.Commands.HaltExecution;
import Interpreter.Debugger.DebugVM;

/**
 * UI implementation of the Halt command.
 * @author Sara Pulis
 */
public class UIHaltExecution extends HaltExecution {

    @Override
    public void execute(DebugVM virtualMachine) {
        super.execute(virtualMachine);
        System.out.println("Halted - program reset");
    }

}
