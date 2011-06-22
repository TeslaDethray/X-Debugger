package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Displays the variables of all functions currently operating in the program.
 * @author Sara Pulis
 */
public class UIDisplayGlobalVariables extends UICommand {

    UIDisplayLocalVariables variableDisplayer = new UIDisplayLocalVariables();

    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        System.out.println("Program contains:");
        if (virtualMachine.getFERsize() == 0) {
            System.out.println("Nothing initialized");
        } else {
            for (int i = 0; i < virtualMachine.getFERsize(); i++) {
                if (virtualMachine.FERgetFunctionName(i) == null) {
                    System.out.print(makeSpaces(i) + "Unnamed function:\n" + makeSpaces(i));
                } else {
                    System.out.print(makeSpaces(i) + virtualMachine.FERgetFunctionName(i) + ":\n" + makeSpaces(i));
                }
                variableDisplayer = new UIDisplayLocalVariables(virtualMachine.getVariables(i));
                variableDisplayer.execute();
            }
        }
    }
    
    private String makeSpaces(int num) {
        String returnString = "";
        for(int i = 0; i <= num; i++) {
            returnString += "    ";
        }
        return returnString;
    }

    @Override
    public void execute(UI userInterface) {
        execute(userInterface.getVM());
    }

}
