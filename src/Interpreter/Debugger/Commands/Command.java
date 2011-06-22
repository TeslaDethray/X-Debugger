package Interpreter.Debugger.Commands;
import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * An abstract class outlining the methods to be implemented by the
 * Debugger's commands.
 *
 * @author Sara Pulis
 */
public abstract class Command {

    /**
    * Initiates the command with the given arguments.
    *
    * @param arg An int given as an argument
    */
    public abstract void init(int arg);

    public abstract void init(Vector args);

    /**
    * Executes the command.
    */
    public abstract void execute(DebugVM virtualMachine);
    
}
