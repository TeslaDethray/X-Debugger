package Interpreter.Debugger.DebuggerByteCodes;
import Interpreter.ByteCodes.BranchingByteCodes.CallCode;
import Interpreter.Debugger.*;
import Interpreter.VirtualMachine;
import java.util.Stack;

/**
 * Debugger version of CallCode
 * Handles the called function tracing in addition to CallCode's responsibilities.
 * 
 * @author Sara Pulis
 */
public class DebuggerCallCode extends CallCode {

    public void execute(DebugVM virtualMachine) {
        virtualMachine.pushFER();
        if(virtualMachine.isTracing() && !label.getEditedLabelName().equals("Write")) {
            Stack<Integer> poppedRunStack = new Stack();
            int numOfArgs = virtualMachine.getNumOfArgs();
            String traceString = label.getEditedLabelName() + "(";
            while(numOfArgs > 0) {
                poppedRunStack.push(virtualMachine.popRunStack());
                numOfArgs--;
            }
            while (!poppedRunStack.isEmpty()) {
                traceString += poppedRunStack.peek();
                if (poppedRunStack.size() > 1) {
                    traceString += ",";
                }
                virtualMachine.pushRunStack(poppedRunStack.pop());
            }
            traceString += ")";
            virtualMachine.functionTrace(traceString);
        }
        super.execute(virtualMachine);
    }

    /**
     * Overloaded execute method
     * @param virtualMachine Caller of this method
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }

}
