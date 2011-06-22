package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * Prints out the program execution information.
 * @author Sara Pulis
 */
public class DumpCode extends ByteCode {

    boolean dumpState; //If true, the program status will print out. If false, it won't.

    /**
     * Constructor. Does nothing.
     */
    public DumpCode() {}

    public void init(Vector args) {
        String state = (String)args.get(0);
        setDumpState(state);
    }

    /**
     * Returns true if dumping is on, false if it is off.
     * @return Returns true if dumping is on, false if it is off.
     */
    public boolean getDumpState() {
        return dumpState;
    }

    /**
     * Changes the dumping status
     * @param state "OFF" turns off dumping, "ON" turns it on.
     */
    public void setDumpState(String state) {
        if (state.equals("ON")) {
            dumpState = true;
        } else {
            dumpState = false;
        }
    }

    /**
     * Changes the dumping state based on the previously given information.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.setDumping(dumpState);
    }

    public String toString() {
        if (dumpState) {
            return ("DUMP ON");
        } else {
            return ("DUMP OFF");
        }
    }
}
