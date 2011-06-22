package Interpreter;
import Interpreter.ByteCodes.*;
import java.util.*;

/**
 * Takes care of running the program, interfacing with all parts of the interpreter
 * @author Sara Pulis
 * Modified from Professor Levine's original file.
 */
public class VirtualMachine {

    protected RunTimeStack runStack;
    protected int pc;         //the program counter
    protected Stack returnAddrs;      //push/pop when call/return functions
    protected boolean isRunning;      //true while VM is running
    protected boolean isDumping;      //dumping false until set to true
    private Program program;        //bytecode program
    protected Stack currentFunctionName = new Stack();
                            //holds the names of the functions as they are called

    /**
     * Constructor. It does nothing.
     */
    public VirtualMachine() {}

    /**
     * Overloaded constructor. Begins and loads a program.
     * @param programArg the Program instance to which to set the program
     * variable.
     */
    VirtualMachine(Program programArg) {
        program = programArg;
    }

    /**
     * Runs the loaded program.
     */
    public void executeProgram() {
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack();
        isRunning = true;
        isDumping = false;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (isDumping) {
                if (!(code.getClass().toString().equals("class Interpreter.ByteCodes.DumpCode")) &&
                        !(code.getClass().toString().equals("class Interpreter.ByteCodes.DummyCode"))) {
                    System.out.println(code);
                    runStack.dump();
                }
            }
            pc++;
        }
    }

    /**
     * Moves the current location in the program to the requested location,
     * saves the former location for recall, and returns the arguments for the called function.
     * @param location The int representing the line from which the program will be continued from.
     * @param functionName A String representing the name of the function being called.
     * @return Returns a vector containing all contents of the frame for this call.
     */
    public Vector callCounterPush(int location, String functionName) {
        Vector currentFrameContents = runStack.getCurrentFrame();
        returnAddrs.push(pc);
        setProgramCounter(location);
        currentFunctionName.push(functionName);
        return currentFrameContents;
    }

    /**
     * Returns from the called location to where the program was before it was called.
     */
    public void returnCounterPop() {
        if (!returnAddrs.empty()) {
            setProgramCounter((Integer)returnAddrs.pop());
            runStack.popFrame();
        }
    }

    /**
     * Returns the current function name and removes it from the list of called function.
     * @return The String representing the name of the function being returned from.
     */
    public String retrieveCurrentFunctionName() {
        if (!currentFunctionName.empty()) {
            return (String)currentFunctionName.pop();
        } else {
            return ("");
        }
    }

    /**
     * Changes the active location of the program to the line specified
     * @param location An int representing the line to move to.
     */
    public void setProgramCounter(int location) {
        pc = location;
    }

    /**
     * Changes the running status of the program.
     * @param runSet Boolean; set to false to stop the program, true to run it.
     */
    public void setRunning(boolean runSet) {
        isRunning = runSet;
    }

    /**
     * Changes the dumping (printing) status of the program.
     * @param dumpSet Boolean; set to false to stop dumping, true to start.
     */
    public void setDumping(boolean dumpSet) {
        isDumping = dumpSet;
    }

    /**
     * Removes and returns the top item from the Run Time Stack.
     * @return An int representing what was the top item on the stack.
     */
    public int popRunStack() {
        return runStack.pop();
    }

    /**
     * Pushes the given item onto the top of the Run Time Stack
     * @param arg The int to be pushed onto the stack.
     * @return The int given in the parameter.
     */
    public int pushRunStack(int arg) {
        return runStack.push(arg);
    }

    /**
     * Returns the top item from the Run Time Stack without removing it
     * @return The top int from the Run Time Stack.
     */
    public int peekRunStack() {
        if (!runStack.empty()) {
            return runStack.peek();
        } else {
            return -1;
        }
    }

    /**
     * Begins a new frame at the location given, allocating all items on the
     * Run Time Stack beyond that point into a new frame.
     * @param offset An int representing the number of parameters loaded onto the stack.
     */
    public void runStackNewFrameAt(int offset) {
        runStack.newFrameAt(offset);
    }

    /**
     * Used to load variables from locations anywhere in the current frame.
     * @param offset The location of the int desired in the current frame
     * @return The item at the requested location.
     */
    public int runStackLoad(int offset) {
        return runStack.load(offset);
    }

    /**
     * Used to store the top item of the stack into the desired location in the current frame.
     * @param offset The location of the int desired in the current frame.
     * @return Returns the item at the requested location.
     */
    public int runStackStore(int offset) {
        return runStack.store(offset);
    }

}