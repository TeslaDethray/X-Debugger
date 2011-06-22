package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * WRITE - Outputs the top item of the stack onto the screen.
 * @author Sara Pulis
 */
public class WriteCode extends ByteCode {

    /**
     * Constructor. Does nothing.
     */
    public WriteCode() {}

    public void init(Vector args) {}

    /**
     * Outputs the top stack item onto the screen.
     * @param virtualMachine The caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        System.out.println(virtualMachine.peekRunStack());
    }

    public String toString() {
        return ("WRITE");
    }
}
