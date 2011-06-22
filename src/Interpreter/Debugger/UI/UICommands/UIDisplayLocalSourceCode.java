package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;


/**
 * Displays the source code of the currently running function.
 * @author Sara Pulis
 */
public class UIDisplayLocalSourceCode extends UICommand {

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {}

    @Override
    public void execute(UI userInterface) {
        if ((userInterface.getVM().getFERsize() == 1) && userInterface.getVM().FERgetFunctionName() == null) {
            userInterface.printSourceFile(1, (userInterface.getVM().sourceFileLength() - 1));
        } else {
            userInterface.printSourceFile(userInterface.getVM().FERgetStartLine(), userInterface.getVM().FERgetEndLine());
        }
    }

}