package Interpreter.ByteCodes;
import Interpreter.*;
import java.io.*;
import java.util.Vector;

/**
 * READ:
 *       -read an integer
 *       -prompt the user for input
 *       -put the value just read on top of the stack
 * @author Sara Pulis
 */
public class ReadCode extends ByteCode {

    /**
     * Constructor. Does nothing.
     */
    public ReadCode() {}

    public void init(Vector args) {}

    /**
     * Prompts the user for an integer, reads it, and pushes it atop the stack.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        try {
            System.out.print("Enter an integer: "); //prompt user for integer entry
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputInt = br.readLine();
            Integer integerArg = Integer.parseInt(inputInt);
            virtualMachine.pushRunStack(integerArg);
        } catch (Exception e) {
            System.out.println("The data which has been entered is invalid.");
            System.exit(1);
        }
    }

    public String toString() {
        return ("READ");
    }
}
