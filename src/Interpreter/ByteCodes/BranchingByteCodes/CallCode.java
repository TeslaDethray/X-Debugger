package Interpreter.ByteCodes.BranchingByteCodes;
import Interpreter.*;
import Interpreter.ByteCodes.*;
import java.util.Vector;

/**
 * CALL &lt;funcname> - transfer control to the indicated function
 * @author Sara Pulis
 */
public class CallCode extends BranchingByteCode {

    protected String labelString; //String representation of the label
    protected LabelCode label;    //LabelCode object to which this code links
    protected Vector argVector;   //Arguments used in the function call.

    /**
     * Constructor. Does nothing.
     */
    public CallCode() {}

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
        argVector = virtualMachine.callCounterPush(label.getLocation(), label.getEditedLabelName());
    }

    public String toString() {
        String outString = "", outString2 = "";
        outString = ("CALL " + getLabelString() + "    " +  label.getEditedLabelName() + "(");
        for (int i = 0; i < argVector.size(); i++) {
            outString2 = argVector.get(i) + outString2;
            if ((i + 1) != argVector.size()) {
                outString2 = "," + outString2;
            }
        }
        outString2 += ")";
        return (outString + outString2);
    }
}
