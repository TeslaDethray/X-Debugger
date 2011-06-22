package Interpreter.Debugger.Commands;

import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Step In - Ideally steps into the function on the current line, handles the
 * formals then stops but if there is no function call on the next line, it
 * does one of the following things:
 *  -If there is a return statement, it performs a StepOut.
 *  -If there is no return statement, it simply stops once it has processed
 *      the line it is on.
 * 
 * @author Sara Pulis
 */
public class StepIn extends Command {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        virtualMachine.toggleStepSetting();

        int envSize = virtualMachine.getFERsize(),
                currentLine = virtualMachine.FERgetCurrentLine();
        
        //Search the current line
        while(!virtualMachine.peekNextByteCode().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerFunctionCode") &&
                    !virtualMachine.peekNextByteCode().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerReturnCode") &&
                     (envSize == virtualMachine.getFERsize()) &&
                     (currentLine == virtualMachine.FERgetCurrentLine())) {

            //If neither ReturnCode, FunctionCode, nor CallCode have been found, continue processing ByteCodes as normal.
            virtualMachine.executeProgram();
            virtualMachine.toggleStepSetting();
        }

        //If we found a FunctionCode or a new FER was pushed
        if ((envSize != virtualMachine.getFERsize()) ||
                    virtualMachine.peekNextByteCode().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerFunctionCode")) {

            //Advance to the FunctionCode if we've due to a new FER.
            while(!virtualMachine.peekNextByteCode().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerFunctionCode")) {
                virtualMachine.executeProgram();
                virtualMachine.toggleStepSetting();
            }

            //Process through the FunctionCode and any FormalCodes following.
            for (int i = (virtualMachine.getNumOfArgs() + 1); i != 0; i--) {
                virtualMachine.executeProgram();
                virtualMachine.toggleStepSetting();
            }

        }

        //If we found a ReturnCode in that line, return to the caller (i.e. step out).
        if (virtualMachine.peekNextByteCode().equals("class Interpreter.Debugger.DebuggerByteCodes.DebuggerReturnCode")) {
            envSize = virtualMachine.getFERsize();

            //If we are starting at a breakpoint, we have to move past it before we can contine with the out-stepping.
            while((envSize <= virtualMachine.getFERsize()) &&
                    (currentLine == virtualMachine.FERgetCurrentLine()) &&
                    (virtualMachine.isBreakpoint(virtualMachine.FERgetCurrentLine()))) {
                virtualMachine.executeProgram();
                virtualMachine.toggleStepSetting();
            }

            //If we did not stop because we stepped out on the breakpoint line, we must continue until we have stepped out!
            if (!virtualMachine.isBreakpoint(virtualMachine.FERgetCurrentLine())) {
                while ((envSize <= virtualMachine.getFERsize()) && !virtualMachine.isBreakpoint(virtualMachine.FERgetCurrentLine())) {
                    virtualMachine.executeProgram();
                    virtualMachine.toggleStepSetting();
                }
            }
        }

        //Turn stepping off.
        virtualMachine.toggleStepSetting();
    }

}
