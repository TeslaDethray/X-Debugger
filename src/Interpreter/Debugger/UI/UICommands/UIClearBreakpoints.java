package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.Commands.ClearBreakpoints;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Clears the break
 * @author Sara Pulis
 */
public class UIClearBreakpoints extends ClearBreakpoints {

    private Vector<Integer> breakpointsToClear = new Vector();
    private boolean clearAllBreakpoints = false;

    /**
     * Constructor. Does nothing.
     */
    public UIClearBreakpoints () {}

    @Override
    public void init(Vector args) {
        try {
            if (args.size() > 0) {
                String arg = (String)args.get(0);
                if (arg.equals("ALL")) {
                    clearAllBreakpoints = true;
                } else {
                    for(int i = 0; i < args.size(); i++) {
                        arg = (String)args.get(i);
                        breakpointsToClear.add(Integer.parseInt(arg));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid argument(s)");
        }
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        try {
            if (breakpointsToClear.isEmpty() && !clearAllBreakpoints) {
                System.out.println("No breakpoints cleared.");
            } else {
                if (clearAllBreakpoints) {
                    Vector<Integer> breakpoints = virtualMachine.getBreakpoints();
                    while(!breakpoints.isEmpty()) {
                        super.init(breakpoints.get(0));
                        super.execute(virtualMachine);
                        breakpoints = virtualMachine.getBreakpoints();
                    }
                    System.out.println("All breakpoints cleared");
                } else {
                    System.out.print("Breakpoint");
                    if (breakpointsToClear.size() > 1) {
                        System.out.print("s ");
                    } else {
                        System.out.print(" ");
                    }
                    for(int i = 0; i < breakpointsToClear.size(); i++) {
                        super.init(breakpointsToClear.get(i));
                        super.execute(virtualMachine);
                        System.out.print(breakpointsToClear.get(i));
                        if((breakpointsToClear.size() - 1) == i) {
                            if(breakpointsToClear.size() == 1) {
                                System.out.print(" cleared.\n");
                            }
                        } else {
                            System.out.print(", ");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid argument(s)");
        }
    }

}
