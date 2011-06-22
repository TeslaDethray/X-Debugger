package Interpreter.Debugger.UI;
import java.util.Hashtable;

/**
 * Holds and initializes the ability to translate codes to create instances
 * of the command classes.
 * 
 * @author Sara Pulis
 */
public class CommandTable {

    public static Hashtable<String, String> VMCommands = new Hashtable();
        //Holds the commands which affect the virtual machine and their translations.
    public static Hashtable<String, String> UICommands = new Hashtable();
        //Holds the commands which affect the user interface and their translations.

    /**
     * Constructor. Fills in all possible values for commands.
     */
    public CommandTable() {
        //HELP
        UICommands.put("HELP","UIHelpCommand");
        UICommands.put("?","UIHelpCommand");

        //EXIT
        UICommands.put("EXIT", "UIExitCommand");
        UICommands.put("X", "UIExitCommand");

        //SET FUNCTION TRACING
        VMCommands.put("SET_TRACING","UISetFunctionTracing");
        VMCommands.put("T","UISetFunctionTracing");
        
        //PRINT CALL STACK
        UICommands.put("PRINT_CALL_STACK","UIPrintCallStack");
        UICommands.put("P","UIPrintCallStack");

        //DISPLAY LOCAL VARIABLES
        UICommands.put("DISPLAY_LOCAL_VARIABLES","UIDisplayLocalVariables");
        UICommands.put("D","UIDisplayLocalVariables");

        //DISPLAY FUNCTION
        UICommands.put("DISPLAY_FUNCTION","UIDisplayLocalSourceCode");
        UICommands.put("F","UIDisplayLocalSourceCode");

        //DISPLAY FUNCTION
        UICommands.put("DISPLAY_SOURCE_CODE","UIDisplaySourceCode");
        UICommands.put("S","UIDisplaySourceCode");

        //LIST BREAKPOINTS
        UICommands.put("LIST_BREAKPOINTS","UIListBreakpointSettings");
        UICommands.put("L","UIListBreakpointSettings");

        //SET BREAKPOINT
        VMCommands.put("SET_BREAKPOINT","UISetBreakpoint");
        VMCommands.put("B","UISetBreakpoint");

        //CLEAR BREAKPOINT
        VMCommands.put("CLEAR_BREAKPOINT","UIClearBreakpoints");
        VMCommands.put("R","UIClearBreakpoints");

        //CONTINUE EXECUTION
        VMCommands.put("CONTINE_EXECUTION","UIContinueExecution");
        VMCommands.put("GO","UIContinueExecution");
        VMCommands.put("C","UIContinueExecution");

        //QUIT EXECUTION
        VMCommands.put("HALT_EXECUTION","UIHaltExecution");
        VMCommands.put("H","UIHaltExecution");

        //STEP OVER
        VMCommands.put("STEP_OVER","UIStepOver");
        VMCommands.put("V","UIStepOver");

        //STEP INTO
        VMCommands.put("STEP_INTO","UIStepIn");
        VMCommands.put("I","UIStepIn");

        //STEP OUT
        VMCommands.put("STEP_OUT","UIStepOut");
        VMCommands.put("O","UIStepOut");

        //The user will not be informed about these commands.

        //DISPLAY GLOBAL VARIABLES
        UICommands.put("DISPLAY_VARIABLES","UIDisplayGlobalVariables");
        UICommands.put("DV","UIDisplayGlobalVariables");

        //SET DUMPING
        UICommands.put("DUMPING","UIDumping");
        UICommands.put("DUMP","UIDumping");

    }

    /**
     * Returns the translation of the Command.
     * @param code A String representing the Command to be processed.
     * @return A String representing the name of the class corresponding to the Command.
     */
    public String get(String code) {
        if (isVMCommand(code)) {
            return VMCommands.get(code);
        } else if (isUICommand(code)) {
            return UICommands.get(code);
        } else {
            return null;
        }
    }

    /**
     * Returns true if code is found in the UICommand Hashtable.
     * @param code string to search the Hashtable for
     * @return true if code is found in the UICommand Hashtable
     */
    public boolean isUICommand (String code) {
        return (UICommands.containsKey(code));
    }

    /**
     * Returns true if code is found in the VMCommand Hashtable.
     * @param code string to search the Hashtable for
     * @return true if code is found in the VMCommand Hashtable
     */
    public boolean isVMCommand (String code) {
        return (VMCommands.containsKey(code));
    }

}
