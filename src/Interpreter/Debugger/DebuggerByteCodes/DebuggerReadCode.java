package Interpreter.Debugger.DebuggerByteCodes;

import Interpreter.ByteCodes.ReadCode;
import Interpreter.Debugger.DebugVM;
import Interpreter.VirtualMachine;
import java.io.*;

/**
 * Debugger implementation of ReadCode
 * @author Sara Pulis
 */
public class DebuggerReadCode extends ReadCode {

    @Override
    public void execute(VirtualMachine virtualMachine) {
        execute((DebugVM)virtualMachine);
    }

    public void execute(DebugVM virtualMachine) {
        try {
            System.out.print("Enter an integer: "); //prompt user for integer entry
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inputInt = br.readLine();
            Integer integerArg = Integer.parseInt(inputInt);
            virtualMachine.pushRunStack(integerArg);
        } catch (Exception e) {
            System.out.println("The data which has been entered is invalid. Try again.");
            execute(virtualMachine);
        }
    }

}
