package Interpreter.ByteCodes.BranchingByteCodes;
import Interpreter.ByteCodes.*;
import Interpreter.*;
import java.util.Vector;

/**
 * An abstract child of ByteCode which deals with the codes which branch.
 * @author Sara Pulis
 */
public abstract class BranchingByteCode extends ByteCode {

    //The following three methods are inheirited from the ByteCode class.
    public abstract void init(Vector args);
    public abstract String toString();

    /**
     * Moves the control of the program to the location indicated by the label.
     * @param virtualMachine The caller of this method.
     */
    @Override
    public abstract void execute(VirtualMachine virtualMachine);

    /**
     * Returns the name of the label corresponding to this code.
     * @return A String representing the name of the label corresponding to this code.
     */
    public abstract String getLabelString();

    /**
     * Sets the name of the label corresponding to this code
     * @param labelCode A String representing the name of the label corresponding to this code.
     */
    public abstract void setLabelString(String labelCode);

    /**
     * Sets the label of the code to the given LabelCode.
     * @param labelCode The LabelCode to which this code links.
     */
    public abstract void setLabel(LabelCode labelCode);

    /**
     * Returns the LabelCode to which this code links.
     * @return The LabelCode to which this code links.
     */
    public abstract LabelCode getLabel();

}
