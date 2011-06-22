package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.Commands.Command;
import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * UI version of the Command class
 * @author Sara Pulis
 */
public abstract class UICommand extends Command {

    /**
     * Initializes the command
     * @param args arguments for the command
     */
    public abstract void init(Vector args);

    @Override
    public void init(int arg) {}

    /**
     * Executes the command
     * @param userInterface caller of this function
     */
    public abstract void execute(UI userInterface);

    @Override
    public void execute(DebugVM virtualMachine) {}

}
