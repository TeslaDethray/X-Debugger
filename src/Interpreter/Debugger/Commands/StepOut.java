package Interpreter.Debugger.Commands;

import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 * Steps out of the current function's activaiton.
 * (i.e. Returns to its caller.)
 *
 * @author Sara Pulis
 */
public class StepOut extends Command {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        int envSize = virtualMachine.getFERsize();
        int currentLine = virtualMachine.FERgetCurrentLine();

        virtualMachine.toggleStepSetting();
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
        virtualMachine.toggleStepSetting();
    }


}
