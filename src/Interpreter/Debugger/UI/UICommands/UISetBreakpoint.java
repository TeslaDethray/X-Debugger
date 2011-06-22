package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.Commands.SetBreakpoint;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Sets breakpoints at valid locations.
 *
 * @author Sara Pulis
 */
public class UISetBreakpoint extends SetBreakpoint {

    private Vector<Integer> breakpointsToSet = new Vector();

    /**
     * Constructor. Does nothing.
     */
    public UISetBreakpoint() {}

    @Override
    public void init(Vector args) {
        try {
            for(int i = 0; i < args.size(); i++) {
                String arg = (String)args.get(i);
                breakpointsToSet.add(Integer.parseInt(arg));
            }
        } catch (Exception e) {
            System.out.println("Invalid argument(s)");
        }
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        try {
            Vector<Integer> invalidBreakpoints = new Vector();
            for(int i = 0; i < breakpointsToSet.size(); i++) {
                if(!virtualMachine.isValidBreakpoint(breakpointsToSet.get(i))) {
                    invalidBreakpoints.add(breakpointsToSet.get(i));
                }
            }
            for(int i = 0; i < invalidBreakpoints.size(); i++) {
                breakpointsToSet.remove(invalidBreakpoints.get(i));
            }
            if (breakpointsToSet.isEmpty()) {
                System.out.println("No breakpoints set");
            } else {
                System.out.print("Breakpoint(s) set at ");
                for(int i = 0; i < breakpointsToSet.size(); i++) {
                    if(!virtualMachine.isBreakpoint(breakpointsToSet.get(i))) {
                        super.init(breakpointsToSet.get(i));
                        super.execute(virtualMachine);
                    }
                    System.out.print(breakpointsToSet.get(i));
                    if((breakpointsToSet.size() - 1) != i) {
                        System.out.print(", ");
                    }
                }
                System.out.print("\n");
            }
            if (!invalidBreakpoints.isEmpty()) {
                for(int i = 0; i < invalidBreakpoints.size(); i++) {
                    System.out.print(invalidBreakpoints.get(i));
                    if((invalidBreakpoints.size() - 1) == i) {
                        if(invalidBreakpoints.size() == 1) {
                            System.out.print(" is invalid\n");
                        } else {
                            System.out.print(" are invalid\n");
                        }
                    } else {
                        System.out.print(", ");
                    }
                }
        }
        } catch (Exception e) {
            System.out.println("Invalid argument(s)");
        }
    }

}
