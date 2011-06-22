package Interpreter.Debugger;
import Interpreter.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * Takes care of running the program, interfacing with all parts of the interpreter.
 *
 * @author Sara Pulis
 * Modified and extended from Professor Levine's original file.
 */
public class DebugVM extends VirtualMachine {

    private Vector<String> sourceFile = new Vector(); //Holds the source file line-by-line
    private DebugProgram program;        //bytecode program
    private static Stack<FunctionEnvironmentRecord> functionEnvironmentStack = new Stack();
                                            //maintains the stack of FunctionEnvironmentRecords (FER)
    private Vector<Integer> breakpoints = new Vector(); //Vector of breakpoints
    private Vector<Integer> validBreakpoints = new Vector(); //A Vector of lines which cannot be designated as breakpoints
    private int prevLine = -99; //Set to the current line after it has been stopped at that point
    private boolean alreadyBeenHere = false;
    private boolean isSteppingSet = false; //True when one of the Step commands is being used
    private boolean tracing = false; //True when function tracing has been activated
    private String functionTrace = ""; //The String which will hold the function tracing activity
    private int numOfArgs = 0; //Holds onto the number of args before a function is called

    /**
     * Constructor. Sets up the program and sets all values to their
     * beginning states.
     * @param programArg The program to be run
     */
    public DebugVM (DebugProgram programArg) {
        program = programArg;
        runAnew();
    }

    /**
     * Runs the program.
     */
    @Override
    public void executeProgram() {
        while (isRunning || isSteppingSet) {
            program.getCode(pc).execute(this);
            if (isDumping) {
                if (!(program.getCode(pc).getClass().toString().equals("class Interpreter.ByteCodes.DumpCode"))) {
                    System.out.println(program.getCode(pc) + "\t\t" + FERprintAll());
                    runStack.dump();
                }
            }
            pc++;
            if (isRunning){
                isRunning = !(isBreakpoint(FERgetCurrentLine()) && !alreadyStoppedHere());
                if (FERgetCurrentLine() > 0) {
                    prevLine = FERgetCurrentLine();
                }
                        //If the next line is a breakpoint at which we haven't already stopped in this cycle, stop running.
            }
            if (isSteppingSet) {
                isSteppingSet = false;
                isRunning = false; //This kept getting set to true somehow.
            }
        }
    }

//VIRTUAL MACHINE-RUNNING METHODS

    /**
     * Resets all the DebugVM's program control variables so that it will
     * run as though it was just started.
     */
    public void runAnew() {
        refresh();
        isRunning = true;
    }

    /**
     * Resets all the DebugVM's program control variables so that it will
     * run as though it was just started.
     */
    public void refresh() {
        pc = 0;
        runStack = new RunTimeStack();
        isRunning = false;
        isDumping = false;
        functionEnvironmentStack = new Stack();
        returnAddrs = new Stack();
        pushFER(); //Puts the first FER onto functionEnvironmentStack.
    }

    /**
     * Returns the program counter
     * @return an int representing the program counter.
     */
    public int getProgramCounter() {
        return pc;
    }

    /**
     * Starts the virtual machine running again
     */
    public void setRunning() {
        isRunning = true;
    }

    /**
     * Turns the source file into a Vector of Strings for easy processing.
     * @throws Exception
     * @return sourceFile
     */
    public Vector<String> processSourceFile(String sourceFileName) throws Exception {
        BufferedReader file = new BufferedReader(new FileReader(sourceFileName));
        String line = file.readLine();
        sourceFile.add(" "); //Adding a blank line so the index will always correspond with the line number

        while (line != null) {
            sourceFile.add(line);
            line = file.readLine();         //Get next line
        }
        file.close();                       //Close the BufferedReader
        determineInvalidBreakpoints((sourceFileName + ".cod"));
        return sourceFile;
    }

    /**
     * Returns the length, in lines, of the source file.
     * @return an int representing the length of the source file in lines.
     */
    public int sourceFileLength() {
        return sourceFile.size();
    }

 //FUNCTION ENVIRONMENT RECORD STACK METHODS

    /**
     * Sets this FER's functionName to the submitted String.
     * @param thisFunction the String to be set as the functionName
     */
    public void FETsetFunctionName(String thisFunction) {
        functionEnvironmentStack.peek().setFunctionName(thisFunction);
    }

    /**
     * Sets this FER's startLine to the submitted int.
     * @param start the int to be set as the startLine
     */
    public void FETsetFunctionStartLine(int startLine) {
        functionEnvironmentStack.peek().setStartLine(startLine);
    }

    /**
     * Sets this FER's endLine to the submitted int.
     * @param end the int to be set as the endLine
     */
    public void FETsetFunctionEndLine(int endLine) {
        functionEnvironmentStack.peek().setEndLine(endLine);
    }


    /**
     * Sets the current line to the submitted line.
     * @param thisLine the line to be set as the current line
     */
    public void FERsetCurrentLine(int thisLine) {
        functionEnvironmentStack.peek().setCurrentLine(thisLine);
    }

    /**
     * Returns the int representing the line which the function is currently on.
     * @return the int representing the line which the function is currently on.
     */
    public int FERgetCurrentLine() {
        return functionEnvironmentStack.peek().getCurrentLine();
    }

    public int FERgetCurrentLine(int index) {
        return functionEnvironmentStack.get(index).getCurrentLine();
    }

    /**
     * Returns the int representing the line which the function starts on.
     * @return the int representing the line which the function starts on.
     */
    public int FERgetStartLine() {
        return functionEnvironmentStack.peek().getStartLine();
    }

    /**
     * Returns the int representing the line which the function starts on.
     * @return the int representing the line which the function starts on.
     */
    public int FERgetStartLine(int line) {
        return functionEnvironmentStack.get(line).getStartLine();
    }

    /**
     * Returns the int representing the line which the function ends on.
     * @return the int representing the line which the function ends on.
     */
    public int FERgetEndLine() {
        return functionEnvironmentStack.peek().getEndLine();
    }

    /**
     * Returns the String representing the name of the function at the top
     * of the stack.
     * @return the String representing the name of the function at the top
     * of the stack.
     */
    public String FERgetFunctionName() {
        return functionEnvironmentStack.peek().getFunctionName();
    }

    /**
     * Returns the String representing the name of the function in the FER
     * at the given index.
     * @return the String representing the name of the function in the FER
     * at the given index.
     */
    public String FERgetFunctionName(int index) {
       return functionEnvironmentStack.get(index).getFunctionName();
   }

   /**
    * Returns the variables for the function at the top of the stack.
    * @return A Vector representing the variables for the function at
    * the top of the stack.
    */
   public Vector getVariables() {
       return functionEnvironmentStack.peek().getVariables();
   }

  /**
    * Returns the variables for the function in the FER at the given
    * index.
    * @return A Vector representing the variables for the function in
    * the FER at the given index.
    */
   public Vector getVariables(int index) {
       return functionEnvironmentStack.get(index).getVariables();
   }

    /**
     * Pushes a new FER onto the functionEnvironmentStack.
     */
    public void pushFER() {
        functionEnvironmentStack.push(new FunctionEnvironmentRecord());
    }

    /**
     * Pops the last FER off the functionEnvironmentStack.
     */
    public void popFER() {
        functionEnvironmentStack.pop();
    }

    /**
     * Returns the size of the FER.
     * @return an int representing the number of FERs on the stack.
     */
    public int getFERsize() {
        return functionEnvironmentStack.size();
    }

    /**
     * Adds a literal to the symbolTable
     * @param literalName name of the literal to be added
     * @param literalValue value of the literal to be added
     */
    public void FERaddLiteral(String literalName, int literalValue) {
        functionEnvironmentStack.peek().addLiteral(literalName, literalValue);
    }

    /**
     * Pops the given number of literals
     * @param numberToPop the number of literals to pop
     */
    public void FERpopLiterals(int numberToPop) {
        functionEnvironmentStack.peek().popLiterals(numberToPop);
    }

    /**
     * Creates a String of all the FERs in the functionEnvironmentStack.
     * @return the String representing all FERs in the the FES.
     */
    public String FERprintAll() {
        String list = "";
        for (int i = 0; i < functionEnvironmentStack.size(); i++) {
            list += functionEnvironmentStack.get(i);
        }
        return list;
    }

//BREAKPOINT METHODS

    /**
     * Adds the argument to the breakpoint list.
     * @param bp The line to be added to the list.
     */
    public void setBreakpoint(int bp) {
        breakpoints.add(bp);
    }

    /**
     * Removes the argument from the breakpoint list
     * @param bp The line to be removed from the list.
     */
    public void removeBreakpoint(int bp) {
        if(breakpoints.contains(bp)) {
            breakpoints.remove(breakpoints.indexOf(bp));
        }
    }

    /**
     * Returns a Vector containing all the breakpoints.
     * @return A Vector containing all the breakpoints.
     */
    public Vector<Integer> getBreakpoints() {
        return breakpoints;
    }

    /**
     * Returns true if the line in the argument is a set breakpoint.
     * @param num The line number to check for.
     * @return True if the argument is set as a breakpoint, false if it is not.
     */
    public boolean isBreakpoint(int lineNumber) {
        return breakpoints.contains(lineNumber);
    }

    /**
     * Determines which breakpoints are invalid and cannot be set
     * @param sourceFile the file to scan to determine invalid breakpoints
     */
    private void determineInvalidBreakpoints(String sourceFileName) throws Exception {
        Vector<String> byteCodeFile = new Vector();
        BufferedReader file = new BufferedReader(new FileReader(sourceFileName));
        String line = file.readLine();

        while (line != null) {
            byteCodeFile.add(line);
            line = file.readLine();         //Get next line
        }
        file.close();                       //Close the BufferedReader

        int lineNumber = 0;
        StringTokenizer lineTokenizer = null;
        for (int i = 0; i < byteCodeFile.size(); i++) {
            lineTokenizer = new StringTokenizer(byteCodeFile.get(i));
            line = lineTokenizer.nextToken();
            if (line.equals("LINE")) {
                lineNumber = Integer.parseInt(lineTokenizer.nextToken());
                if((lineNumber > 0) && (lineNumber < sourceFile.size()) && !(validBreakpoints.contains(lineNumber))) {
                    validBreakpoints.add(lineNumber);
                }
            }
        }
    }

    /**
     * Returns true if the given int has not been determined to be an invalid
     * breakpoint
     * @param breakpoint the proposed breakpoint
     * @return boolean
     */
    public boolean isValidBreakpoint(int breakpoint) {
        return (validBreakpoints.contains(breakpoint) && breakpoint > 0 && breakpoint < sourceFile.size());
    }

    /**
     * Solves the problem of the Debugger stopping at several
     * commands on the same line.
     *
     * @param atBreakpoint Is true if the current line is the same as the
     * breakpoint
     * @return
     */
    private boolean alreadyStoppedHere() {
        return (prevLine == FERgetCurrentLine());
    }

//FUNCTION TRACING METHODS

     /**
     * Sets the tracing state according to the parameter.
     * @param isTracing boolean which will be reflected in the tracing state.
     */
    public void setTracing(boolean isTracing) {
        tracing = isTracing;
    }

    /**
     * Returns the tracing state.
     * @return a boolean representing the tracing state.
     */
    public boolean isTracing() {
        return tracing;
    }

    /**
     * Returns a String representing the function trace.
     * @return A String representing the function trace.
     */
    public String functionTrace() {
        String trace = functionTrace;
        return trace;
    }

    public void clearFunctionTrace() {
        functionTrace = "";
    }

    /**
     * Adds the given String to the functionTrace.
     * @param trace A string to be added to the function trace.
     */
    public void functionTrace(String trace) {
        for(int i = 0; i <= getFERsize(); i++) {
            functionTrace += "  ";
        }
        functionTrace += trace + "\n";
    }

    /**
     * Sets and holds onto the number of arguments in the function to follow
     * @param argNum An int representing the number of args to follow
     */
    public void setNumOfArgs(int argNum) {
        numOfArgs = argNum;
    }

    /**
     * Returns the number of args in the function to vollow
     * @return An int representing the number of args to follow
     */
    public int getNumOfArgs() {
        return numOfArgs;
    }

    public int runStackSeeItemAt(int offset) {
        return runStack.seeItemAt(offset);
    }

//STEPPING METHODS

    /**
     * Toggles the Step setting on and off.
     */
    public void toggleStepSetting() {
        isSteppingSet = !isSteppingSet;
    }

    /**
     * Returns isSteppingSet's state
     * @return a boolean reperesenting isSteppingSet's state.
     */
    public boolean isStepping() {
        return isSteppingSet;
    }

    /**
     * Returns the String representation of the class of the next ByteCode
     * @return A String representation of the class of the next ByteCode
     */
    public String peekNextByteCode() {
        return (program.getCode(pc).getClass().toString());
    }

    /**
     * Increments the program counter w/o having run execute().
     */
    public void incrementProgramCounter() {
        pc++;
    }

    /**
     * Pops the top frame pointer off the frame pointer stack.
     */
    public void runStackPopFramePointer() {
        runStack.popFramePointer();
    }

}