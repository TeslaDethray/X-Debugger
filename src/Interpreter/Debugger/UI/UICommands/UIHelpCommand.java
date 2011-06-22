package Interpreter.Debugger.UI.UICommands;
import Interpreter.Debugger.UI.UI;
import java.util.Vector;

/**
 * Provides the user with the list of commands which the debugger is
 * capable of executing and how to use them.
 *
 * @author Sara Pulis
 */
public class UIHelpCommand extends UICommand {

    /**
    * Constructor. Does nothing.
    */
    public UIHelpCommand() {}

    @Override
    public void execute(UI userInterface) {
        System.out.println("The following is a list of abilites which this debugger has and the commands with which to execute them.");
        System.out.println("The format in which you ought to enter the commands is this:");
        System.out.println("COMMAND ARGUMENT [ARGUMENT]\n" +
                                                "Note that commands are NOT case-sensitive.");
        System.out.println("Ability:                    Command:                                Arguments:");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Help                        HELP, ?                                 N/A");
        System.out.println("Exit                        EXIT, X                                 N/A");
        System.out.println("Set Function Tracing        SET_TRACING, T                          ON, OFF");
        System.out.println("Set Breakpoints             SET_BREAKPOINT, B                       1+ numbers");
        System.out.println("Clear Breakpoints           CLEAR_BREAKPOINT, R                     1+ numbers, ALL");
        System.out.println("Print Call Stack            PRINT_CALL_STACK, P                     N/A");
        System.out.println("List Breakpoints            LIST_BREAKPOINTS, L                     N/A");
        System.out.println("Continue Execution          CONTINUE_EXECUTION, GO, C               N/A");
        System.out.println("Halt Execution              HALT_EXECUTION, H                       N/A");
        System.out.println("Display Local Variables     DISPLAY_LOCAL VARIABLES, D              N/A");
        System.out.println("Display Source Code         DISPLAY_SOURCE CODE, S                  N/A");
        System.out.println("Display Function            DISPLAY_FUNCTION, F                     N/A");
        System.out.println("Step Out of Function        STEP_OUT, O                             N/A");
        System.out.println("Step Over Line              STEP_OVER, V                            N/A");
        System.out.println("Step Into Function          STEP_INTO, I                            N/A");
//  System.out.println("Set Dumping                 DUMPING, DUMP                           ON, OFF");
//  System.out.println("Display Global Variables    DISPLAY_VARIABLES, DVARS, DV            N/A");
    }

    @Override
    public void init(Vector args) {}

}
