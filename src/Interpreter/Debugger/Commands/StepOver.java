package Interpreter.Debugger.Commands;

import Interpreter.Debugger.DebugVM;
import java.util.Vector;

/**
 *
 * @author Sara Pulis
 */
public class StepOver extends Command {

    @Override
    public void init(int arg) {}

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(DebugVM virtualMachine) {
        int currentLine = virtualMachine.FERgetCurrentLine(),
                envSize = virtualMachine.getFERsize();

        //Turn stepping on
        virtualMachine.toggleStepSetting();

        while (((envSize <= virtualMachine.getFERsize()) &&
                (currentLine == virtualMachine.FERgetCurrentLine())) ||
                (virtualMachine.FERgetCurrentLine() == 0)) {
            virtualMachine.executeProgram();
            virtualMachine.toggleStepSetting();
        }


        //Turn stepping off
        virtualMachine.toggleStepSetting();
    }

}
