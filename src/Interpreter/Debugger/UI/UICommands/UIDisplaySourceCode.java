package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;


/**
 * Displays the source code of the source program.
 * @author Sara Pulis
 */
public class UIDisplaySourceCode extends UICommand {

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {}

    @Override
    public void execute(UI userInterface) {
        userInterface.printSourceFile(1, (userInterface.getVM().sourceFileLength() - 1));
    }

}
