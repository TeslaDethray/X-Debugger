package Interpreter.ByteCodes.BranchingByteCodes;
import Interpreter.*;
import Interpreter.ByteCodes.*;
import java.util.Vector;

/**
 * GOTO &lt;label>
 * @author Sara Pulis
 */
public class GotoCode extends BranchingByteCode{

    private String labelString; //String representation of the label
    private LabelCode label;    //LabelCode object to which this code links

    /**
     * Constructor. Does nothing.
     */
    public GotoCode() {}

    public void init(Vector args) {
        labelString = (String)args.get(0);
        String[] editedLabelString = labelString.split(" ");
        labelString = editedLabelString[0];
    }

    public String getLabelString() {
        return labelString;
    }

    public void setLabelString(String labelCode) {
        labelString = labelCode;
    }

    public void setLabel(LabelCode labelCode) {
        label = labelCode;
    }

    public LabelCode getLabel() {
        return label;
    }

    public void execute(VirtualMachine virtualMachine) {
            virtualMachine.setProgramCounter(label.getLocation());
    }

    public String toString() {
        return ("GOTO " + getLabelString());
    }
}