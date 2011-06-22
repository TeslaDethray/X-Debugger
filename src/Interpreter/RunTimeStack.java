package Interpreter;
import java.util.*;

/**
 * Records and processes the stack of active frames
 * @author Sara Pulis
 */
public class RunTimeStack {

    private Stack framePointers = new Stack(), framePointersHolder, framePointersHolder2;
    private Vector runStack = new Vector();

    public RunTimeStack() {
        framePointers.add((Integer)0);
    }

    /**
     * Dumps the runtime stack for debugging
     */
    public void dump() {
        int framePointersSize = (Integer)framePointers.size(); //Size will chance, so saving size
        boolean printComma = true;
        if (!runStack.isEmpty()) {
            framePointersHolder = new Stack();
            framePointersHolder2 = new Stack();
            for(int i = 0; i < framePointersSize; i++) { //Reversing stack
                framePointersHolder.push(framePointers.peek());
                framePointersHolder2.push(framePointers.pop());
            }
            for(int i = 0; i < framePointersSize; i++) { //Reversing stack
                framePointers.push(framePointersHolder2.pop());
            }
            System.out.print("[");
            for(int i = 0; i < runStack.size(); i++) {
                  if (!framePointersHolder.empty()) {
                       if (((Integer)framePointersHolder.peek() == i)) {
                            if (i != 0) {
                                System.out.print("] [");
                            }
                            framePointersHolder.pop();
                        }
                  }
                  if (!framePointersHolder.empty()) {
                       if (((Integer)framePointersHolder.peek() == (i + 1))) {
                            printComma = false;
                        }
                  }
                System.out.print(runStack.get(i));
                    if ((runStack.size() != 1) && ((runStack.size() - 1) != i) && printComma){
                            System.out.print(",");
                    }
                if (!printComma) {
                    printComma = true;
                }
            }
            System.out.println("]");
        }
    }

    /**
     * Returns the top item of runStack without removing it.
     * @return item The item at the top of the stack
     */
    public int peek() {
        Integer integerArg = (Integer)runStack.get((runStack.size() - 1));
        int item = integerArg.intValue();
        return item;
    }

    /**
     * Pops the top item in the runtime stack, returns it
     * @return item The item that was at the top of the stack.
     */
    public int pop() {
        int item = peek();
        runStack.remove((runStack.size()-1));
        return item;
    }

    /**
     * Pushes i onto the runtime stack, returns pushed item.
     * @param i An int to push atop the stack
     * @return Returns the selfsame int pushed onto the stack.
     */
    public int push(int i) {
        runStack.addElement(i);
        return i;
    }

    /**
     * Starts new frame at the given location.
     * @param offset Indicates the number of slots down from the top of the RunTimeStack for starting the new frame - these will all become arguments for the function to be called.
     */
    public void newFrameAt(int offset) {
        framePointers.push(runStack.size() - offset);
    }

    /** 
     * Pops the top frame when we return from a function.
     * @return returnValue The int resulting from the function being reurned from.
     */
    public void popFrame() {
        int returnValue = pop(), //Value to push back on the stack
        popTo = (Integer)framePointers.pop(); //Index of the last number to discard.
        for (int i = (runStack.size()); i > popTo; i--) {
            pop();
        }
        push(returnValue);
    }

    /**
     * Pops the top of the frame pointers stack.
     */
    public void popFramePointer() {
        framePointers.pop();
    }

    /** 
     * Returns the value of the top frame location without popping it.
     * @return The location of the first value in the current frame.
     */
    public int peekFrame() {
        return (Integer)framePointers.peek();
    }

    /**
     * Used to store the top item of the stack into the desired location in the current frame.
     * @param offset The location of the int desired in the current frame.
     * @return Returns the item at the requested location.
     */
    public int store (int offset) {
        runStack.set((offset + peekFrame()), pop());
        return (Integer)runStack.get((offset + peekFrame()));
    }

    /**
     * Used to load variables from locations anywhere in the current frame.
     * @param offset The location of the int desired in the current frame
     * @return The item at the requested location.
     */
    public int load(int offset) {
        return push((Integer)runStack.get((offset + peekFrame())));
    }

    public int seeItemAt(int offset) {
        return (Integer)runStack.get((runStack.size() - offset - 1));
    }

    /**
     * Pushes i onto the runtime stack, returns pushed item.
     * @param i An Integer to push atop the stack
     * @return Returns the int version of the Integer pushed onto the stack.
     */
    public Integer push(Integer i) {
        return push((int)i);
    }

    /**
     * Returns a vector containing all the items in the top frame.
     * Does not remove any items.
     * @return Returns a vector containing the top frame of items in reverse order.
     */
    //Returns a vector containing the top frame.
    public Vector getCurrentFrame() {
        Vector currentFrameContents = new Vector();
        int popTo = (Integer)framePointers.pop(); //Index of the last item in the frame
        int lastIndex = 0;
        while (runStack.size() > popTo) {
            currentFrameContents.add(pop());
        }
        framePointers.push(popTo);
        for (int i = 0; i < currentFrameContents.size(); i++) {
            push((Integer)currentFrameContents.get(i));
        }
        return currentFrameContents;
    }

    public boolean empty() {
        return runStack.isEmpty();
    }

}
