package Interpreter.Debugger.Commands;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Continues execution of the program from the current point.
 *
 * @author Sara Pulis
 */
public class ContinueExecution extends Command {

    /**
     * Constructor. Does nothing.
     */
    public ContinueExecution () {}

    @Override
    public void init(int arg) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.setRunning();
        virtualMachine.executeProgram();
    }

    @Override
    public void init(Vector args) {}

}
