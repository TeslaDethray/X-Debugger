package Interpreter.ByteCodes.BranchingByteCodes;
import Interpreter.*;
import Interpreter.ByteCodes.*;
import java.util.Vector;

/**
 * FALSEBRANCH &lt;label> - pop the top of the stack; if it's false (0) then
 * branch to &lt;label> else execute the next bytecode
 * @author Sara Pulis
 */
public class FalsebranchCode extends BranchingByteCode {

    private String labelString; //String representation of the label
    private LabelCode label;    //LabelCode object to which this code links

    /**
     * Constructor. Does nothing.
     */
    public FalsebranchCode() {}

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

    /**
     * Moves control of the function to the address given in the LabelCode
     * associated with this code if the top of the stack is false.
     * @param virtualMachine The caller of this code.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        if (virtualMachine.popRunStack() == 0) {
            virtualMachine.setProgramCounter(label.getLocation());
        }
    }

    public String toString() {
        return ("FALSEBRANCH " + getLabelString());
    }
}
