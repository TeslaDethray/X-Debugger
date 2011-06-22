package Interpreter.Debugger.Commands;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Stops execution of the program.
 *
 * @author Sara Pulis
 */
public class HaltExecution extends Command {

    /**
     * Constructor. Does nothing.
     */
    public HaltExecution () {}

    @Override
    public void init(int arg) {}

    /**
     * Sets elements in the DebugVM to be as though it has not yet been run.
     * @param virtualMachine The DebugVM object which controls the running program.
     */
    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.runAnew();
    }

    @Override
    public void init(Vector args) {}

}
