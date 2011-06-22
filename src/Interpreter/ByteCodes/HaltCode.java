package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * Halts execution
 * @author Sara Pulis
 */
public class HaltCode extends ByteCode {

    /**
     * Constructor. Does nothing.
     */
    public HaltCode() {}

    public void init(Vector args) {}

    /**
     * Halts the program execution.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setRunning(false);
    }

    public String toString() {
        return ("HALT");
    }
}
