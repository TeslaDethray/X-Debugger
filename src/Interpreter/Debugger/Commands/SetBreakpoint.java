package Interpreter.Debugger.Commands;

import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Sets the breakpoint requested by the user, if the requested breakpoint
 * is valid.
 *
 * @author Sara Pulis
 */
public class SetBreakpoint extends Command {

    private int breakpointToSet;

    /**
     * Constructor. Does nothing.
     */
    public SetBreakpoint() {}

    public void init(int breakpoint) {
        breakpointToSet = breakpoint;
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        if (virtualMachine.isValidBreakpoint(breakpointToSet)) {
            virtualMachine.setBreakpoint(breakpointToSet);
        }
    }
    
    @Override
    public void init(Vector args) {}

}
