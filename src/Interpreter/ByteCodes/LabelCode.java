package Interpreter.ByteCodes;
import Interpreter.*;
import java.util.Vector;

/**
 * LABEL &gt;label> - a target for branches.
 * @see FalsebranchCode, GotoCode, CallCode
 * @author Sara Pulis
 */
public class LabelCode extends ByteCode {

    private int labelLocation; //Number denoting the places from the start of the program where this label is located.
    private String labelName; //name of the label.

    /**
     * Constructor. Does nothing.
     */
    public LabelCode() {}

    public void init(Vector args) {
        labelName = (String)args.get(0);
    }

    /**
     * Returns the line number where the label is located.
     * @return The int representing the line number where the label is located.
     */
    public int getLocation() {
        return labelLocation;
    }

    /**
     * Holds an int which denotes the label's line number from the start of the program.
     * @param location An int representing the line number.
     */
    public void setLocation(int location) {
        labelLocation = location;
    }

    /**
     * Returns a string denoting the label's name.
     * @return The unedited name of the label.
     */
    public String getLabelName() {
        return labelName;
    }

    /**
     * Returns the cleaned-up version of the label name.
     * @return A String representing the cleaned-up version of the label name.
     */
    public String getEditedLabelName() {
        String[] editedLabelName = labelName.split("<");
        return editedLabelName[0];
    }

    /**
     * Does nothing.
     * @param virtualMachine Caller of this method.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {}

    public String toString() {
        return ("LABEL " + getLabelName() + " @ " + getLocation());
    }
}
