package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * An abstract class which deals with the byte codes.
 * @author Sara Pulis
 */
public abstract class ByteCode {

    /**
     * Initializes the Code
     * @param args A Vector representing the arguments to be used for the code.
     */
    public abstract void init(Vector args);

    /**
     * Runs the code.
     * @param virtualMachine The caller of this method.
     */
    public abstract void execute(VirtualMachine virtualMachine);
    
    /**
     * Returns a String representation of the code
     * @return Returns a String representation of the code.
     */
    @Override
    public abstract String toString();

}