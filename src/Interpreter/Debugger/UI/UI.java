package Interpreter.Debugger.UI;

import Interpreter.Debugger.*;
import Interpreter.Debugger.Commands.Command;
import Interpreter.Debugger.UI.UICommands.UICommand;
import java.io.*;
import java.util.*;

/**
 * Debugger User Interface
 * 
 * @author Sara Pulis
 */
public class UI extends Debugger {

    private Vector<String> sourceFile = new Vector();
    private boolean isRunning = true;

    /**
     * Constructor. Points to Debugger's constructor.
     * @param codeFile Name of the file which to call.
     */
    public UI(String codeFile) {
	super(codeFile);
    }

    /**
    * Executes the program. Almost a virtual machine of sorts. It will continue
    * executing until it receives an EXIT command.
    * @throws Exception
    */
    public void run() throws Exception {
        BufferedReader br;
        String inputCommand, command;
        StringTokenizer lineTokenizer;
        CommandTable commandTable = new CommandTable();
        Vector args = new Vector();
        UICommand uiCommandCode;
        Command commandCode;
        sourceFile = vm.processSourceFile(sourceFileName);

        System.out.println("Enter HELP or a command.");

        while(isRunning) {

            if (vm.isTracing()) {
                if (!vm.functionTrace().equals("")) {
                    System.out.println(vm.functionTrace());
                    vm.clearFunctionTrace();
                }
            }

            printSourceFile(1, (vm.sourceFileLength() - 1));

            System.out.print(">> ");
            br = new BufferedReader(new InputStreamReader(System.in));
            inputCommand = br.readLine();
            lineTokenizer = new StringTokenizer(inputCommand);

            if (!inputCommand.equals("")) {
                command = lineTokenizer.nextToken().trim().toUpperCase();
                while (lineTokenizer.hasMoreTokens()) {
                    args.add(lineTokenizer.nextToken().trim().toUpperCase());
                }

                if (commandTable.isUICommand(command)) {
                    uiCommandCode = (UICommand)(Class.forName("Interpreter.Debugger.UI.UICommands." + commandTable.get(command)).newInstance());
                    uiCommandCode.init(args);
                    uiCommandCode.execute(this);
                } else if (commandTable.isVMCommand(command)) {
                    commandCode = (Command)(Class.forName("Interpreter.Debugger.UI.UICommands." + commandTable.get(command)).newInstance());
                    commandCode.init(args);
                    commandCode.execute(getVM());
                } else {
                    System.out.println("Invalid command");
                }

                args.removeAllElements();       //Empty argument vector
            } else {
                System.out.println("Invalid command");
            }
        }
        
        System.out.println("Goodbye!");
	System.exit(1);
        
    }


    /**
     * Prints the source file from a given line to a given line.
     * @param start Line to start printing at
     * @param end Line to stop printing at
     */
    public void printSourceFile(int start, int end) {
        Vector<Integer> breakpoints = vm.getBreakpoints();
        for (int i = start; i <= end; i++) {
            if(vm.isBreakpoint(i)) {
                System.out.print(" **");
            } else {
                System.out.print("   ");
            }
            if(i < 10) {
                System.out.print("  " + i + ".");
            } else if(i < 100) {
                System.out.print(" " + i + ".");
            } else {
                System.out.print(i + ".");
            }
            System.out.print(sourceFile.get(i));
            if((i == vm.FERgetCurrentLine()) ||
                    ((vm.getProgramCounter() == 0) && (i == 1)) ||
                    ((vm.FERgetCurrentLine() == -1) && (i == vm.FERgetCurrentLine(vm.getFERsize() - 2)))) {
                System.out.println("   <-----");
            } else {
                System.out.print("\n");
            }
        }
    }

    /**
     * Sets the isRunning variable to false, thereby ceasing execution.
     */
    public void stopRunning() {
        isRunning = false;
    }

    /**
     * Returns the DebugVM of this object.
     * @return the DebugVM of this object
     */
    public DebugVM getVM() {
        return vm;
    }

}
