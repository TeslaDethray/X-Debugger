package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * ARGS n - Used prior to calling a function
 *      n - the number of arguments on the stack which will be used for the following function.
 * @author Sara Pulis
 */
public class ArgsCode extends ByteCode {

    protected int newFrameLocation; //Number of arguements for the following function.

    /**
     * Constructor. Does nothing.
     */
    public ArgsCode() {}

    public void init(Vector args) {
        String arg = (String)args.get(0);
        newFrameLocation = Integer.parseInt(arg);
    }

    /**
     * Begins a new frame at the given location, thereby allocatin the previously
     * given number of arguments on the stack to the following function.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.runStackNewFrameAt(newFrameLocation);
    }

    public String toString() {
        return ("ARGS " + newFrameLocation);
    }
}
