package Interpreter.Debugger.UI.UICommands;

import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Toggles the dumping of the Interpreter information on and off.
 *
 * @author Sara Pulis
 */
public class UIDumping extends UICommand {

    boolean dumpState; //If true, the program status will print out. If false, it won't.

    /**
     * Constructor. Does nothing.
     */
    public UIDumping() {}

    public void init(Vector args) {
        try {
            String state = (String)args.get(0);
            setDumpState(state);
        } catch (Exception e) {
            System.out.println("No arguments");
        }
    }

    /**
     * Changes the dumping status
     * @param state "OFF" turns off dumping, "ON" turns it on.
     */
    private void setDumpState(String state) {
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
    public void execute(UI userInterface) {
        userInterface.getVM().setDumping(dumpState);
        System.out.print("Dumping ");
         if (dumpState) {
            System.out.println("on");
        } else {
            System.out.println("off");
        }
    }

}
