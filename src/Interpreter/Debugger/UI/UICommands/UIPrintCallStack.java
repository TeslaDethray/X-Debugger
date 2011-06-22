package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Prints the call stack in the following format:
 * Function Name: Last line this function was being executed on
 * @author Sara Pulis
 */
public class UIPrintCallStack extends UICommand{

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        for (int i = (virtualMachine.getFERsize() - 1); i >= 0; i--) {
            if (virtualMachine.FERgetFunctionName(i) == null) {
                System.out.println("Unnamed function:\n" + virtualMachine.FERgetCurrentLine(i));
            } else {
                System.out.println(virtualMachine.FERgetFunctionName(i) + ": " + virtualMachine.FERgetCurrentLine(i));
            }
        }
    }

    @Override
    public void execute(UI userInterface) {
        execute(userInterface.getVM());
    }

}