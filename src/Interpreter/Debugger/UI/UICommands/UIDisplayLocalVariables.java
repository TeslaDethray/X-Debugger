package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Displays the variables of the function currently running.
 * @author Sara Pulis
 */
public class UIDisplayLocalVariables extends UICommand {

    Vector variables = new Vector();

    /**
     * Constructor. Does nothing.
     */
    public UIDisplayLocalVariables() {};
    
    /**
     * Overloaded constructor. Sets the variables Vector to the given Vector.
     * @param vars vector holding the variables and their values.
     * @param function String representing the function name
     */
    public UIDisplayLocalVariables(Vector vars) {
        variables = vars;
    }

    public void init(Vector args) {}

    /**
     * Prints out a list of all variables in the current function.
     */
    public void execute() {
        if (variables.isEmpty()) {
            System.out.print("No variables");
        }
        while (!variables.isEmpty()) {
            if(variables.size() > 2) {
                System.out.print(variables.get(0) + " = " + variables.get(1) + ", ");
            } else {
                System.out.print(variables.get(0) + " = " + variables.get(1));
            }
            variables.remove(0);
            variables.remove(0);
        }
        System.out.print("\n");
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        variables = virtualMachine.getVariables();
        execute();
    }

    @Override
    public void execute(UI userInterface) {
        execute(userInterface.getVM());
    }

}
