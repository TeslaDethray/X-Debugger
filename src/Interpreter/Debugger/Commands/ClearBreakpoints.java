package Interpreter.Debugger.Commands;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Clears one or all of the breakpoints which have been set.
 *
 * @author Sara Pulis
 */
public class ClearBreakpoints extends Command {

    private int breakpointToClear;

    /**
     * Constructor. Does nothing.
     */
    public ClearBreakpoints () {}

    @Override
    public void init(int arg) {
        breakpointToClear = arg;
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.removeBreakpoint(breakpointToClear);
    }

    @Override
    public void init(Vector args) {}

}
