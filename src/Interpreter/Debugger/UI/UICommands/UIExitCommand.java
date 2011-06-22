package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Exits from the debugger program.
 *
 * @author Sara Pulis
 */
public class UIExitCommand extends UICommand {

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(UI userInterface) {
        System.out.println("Exiting...");
        userInterface.stopRunning();
    }

}