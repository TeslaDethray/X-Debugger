package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.Commands.SetFunctionTracing;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * UI implementation of SetFunctionTracing command.
 * @author Sara Pulis
 */
public class UISetFunctionTracing extends SetFunctionTracing {

    @Override
    public void init(Vector args) {
    try {
        super.init(args);
    } catch (Exception e) {
            System.out.println("No argument");
        }
    }

    @Override
    public void execute(DebugVM virtualMachine) {
        super.execute(virtualMachine);
        System.out.print("Tracing is ");
        if (tracingState) {
            System.out.println("on");
        } else {
            System.out.println("off");
        }
    }

}
