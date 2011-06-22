package Interpreter.Debugger.Commands;

import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 *
 * @author sarapulis
 */
public class SetFunctionTracing extends Command {

    protected boolean tracingState; //If true, the program trace will print out. If false, it won't.

    @Override
    public void init(int arg) {}

    /**
     * Changes the tracing status
     * @param args - args(0) "OFF" turns off tracing, "ON" turns it on.
     */
    @Override
    public void init(Vector args) {
        String state = (String)args.get(0);
        if (state.equalsIgnoreCase("ON")) {
            tracingState = true;
        } else {
            tracingState = false;
        }
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.setTracing(tracingState);
    }

}
