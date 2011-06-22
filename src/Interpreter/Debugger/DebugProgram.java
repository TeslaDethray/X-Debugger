package Interpreter.Debugger;
import Interpreter.ByteCodes.BranchingByteCodes.*;
import Interpreter.ByteCodes.*;
import Interpreter.Debugger.DebuggerByteCodes.*;
import Interpreter.Program;
import java.util.*;

/**
 * Debugger implementation of Program.
 * 
 * @author Sara Pulis
 */
public class DebugProgram extends Program {

    private ArrayList program = new ArrayList(); //Holds all the ByteCodes
    private Hashtable<String, LabelCode> labels = new Hashtable(); //Contains all labels for linking to BranchingByteCodes.
    private Vector codesToLink = new Vector(); //Contains the BranchingByteCodes for linking.

    /**
     * Searches for the labels to link to from the BranchingByteCodes and
     * bestows the proper LabelCode upon them.
     */
    @Override
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
     * If the ByteCode is a DebuggerLabelCode, DebuggerGotoCode, DebuggerFalsebranchCode, or DebuggerCallCode,
     * it will try to resolve its address. If it cannot, it will log it
     * in a Hashtable.
     * @param byteCode The ByteCode to be added to the program.
     */
    @Override
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
             (byteCode.getClass().toString().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerCallCode"))) {
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
    @Override
    public ByteCode getCode(int pc) {
        return (ByteCode)program.get(pc);
    }

}