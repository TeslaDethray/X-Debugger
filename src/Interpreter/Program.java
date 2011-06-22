package Interpreter;
import Interpreter.ByteCodes.BranchingByteCodes.*;
import Interpreter.ByteCodes.*;
import java.util.*;

/**
 * Holds the bytecode program loaded from the file
 * Resolves symbolic addresses in the program
 * @author Sara Pulis
 */
public class Program {

    private ArrayList program = new ArrayList(); //Holds all the ByteCodes
    private Hashtable<String, LabelCode> labels = new Hashtable(); //Contains all labels for linking to BranchingByteCodes.
    private Vector<BranchingByteCode> codesToLink = new Vector(); //Contains the BranchingByteCodes for linking.
    protected static int addCounter = -1; //Begins program counter at -1.

    /**
     * Constructor. Does nothing.
     */
    public Program() {}

    /**
     * Searches for the labels to link to from the BranchingByteCodes and
     * bestows the proper LabelCode upon them.
     */
    public void resolveAddress() {
        BranchingByteCode branchingByteCode;
        while (!codesToLink.isEmpty()) {
            branchingByteCode = (BranchingByteCode)codesToLink.get(0);
            branchingByteCode.setLabel(labels.get(branchingByteCode.getLabelString()));
            codesToLink.remove(0);
        }
    }

    /**
     * Fills the program with the ByteCodes
     * If the ByteCode is a LabelCode, GotoCode, FalsebranchCode, or CallCode,
     * it will try to resolve its address. If it cannot, it will log it
     * in a Hashtable.
     * @param byteCode The ByteCode to be added to the program.
     */
    public void addByteCode(ByteCode byteCode) {
        addCounter++;
        program.add(byteCode);
        String codeName;
        if (byteCode.getClass().toString().equals("class Interpreter.ByteCodes.LabelCode")) {
            LabelCode labelCodeType = (LabelCode)byteCode;
            codeName = labelCodeType.getLabelName();
            labelCodeType.setLocation(addCounter);
            labels.put(codeName, labelCodeType);
        }
        if(((byteCode.getClass().toString().equals("class Interpreter.ByteCodes.BranchingByteCodes.GotoCode"))) ||
             (byteCode.getClass().toString().equals("class Interpreter.ByteCodes.BranchingByteCodes.FalsebranchCode")) ||
             (byteCode.getClass().toString().equals("class Interpreter.ByteCodes.BranchingByteCodes.CallCode"))) {
             BranchingByteCode branchingByteCode = (BranchingByteCode)byteCode;
             if(labels.contains(branchingByteCode.getLabelString())){
                 branchingByteCode.setLabel(labels.get(branchingByteCode.getLabelString()));
             } else {
                 codesToLink.add(branchingByteCode);
             }
        }
    }

    /**
     * Returns the ByteCode at the given line in the program.
     * @param pc An int representing the location of the desired ByteCode
     * @return Returns the ByteCode at the location given.
     */
    public ByteCode getCode(int pc) {
        return (ByteCode)program.get(pc);
    }

}
