package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.DebugVM;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Lists the breakpoints which have been set.
 * @author Sara Pulis
 */
public class UIListBreakpointSettings extends UICommand {

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        Vector<Integer> breakpoints = virtualMachine.getBreakpoints();
        if (!breakpoints.isEmpty()) {
            System.out.print("Breakpoints set at ");
            for (int i = 0; i < breakpoints.size(); i++) {
                System.out.print(breakpoints.get(i));
                if ((breakpoints.size() - 1) != i) {
                    System.out.print(", ");
                }
            }
        } else {
            System.out.print("No breakpoints set");
        }
        System.out.print("\n");

    }

    @Override
    public void execute(UI userInterface) {
        execute(userInterface.getVM());
    }

}