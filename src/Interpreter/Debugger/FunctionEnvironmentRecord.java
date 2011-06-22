package Interpreter.Debugger;

import java.util.Vector;

/* these were for testing the main method
    import java.io.*;
    import java.util.*;
*/

/**
 * Maintains a record of the function environment including all
 * named literals, the function name, the start line, the end line, and
 * the current line.
 *
 * @author Sara Pulis
 */
public class FunctionEnvironmentRecord {

    private SymbolTable symbolTable = new SymbolTable();
    private int currentLine, startLine, endLine;
    private String functionName = null;

    /**
     * Constructor. Does nothing.
     */
    public FunctionEnvironmentRecord() {}

    /**
     * Sets this FER's functionName to the submitted String.
     * @param thisFunction the String to be set as the functionName
     */
    public void setFunctionName(String thisFunction) {
        functionName = thisFunction;
    }

    /**
     * Sets this FER's startLine to the submitted int.
     * @param start the int to be set as the startLine
     */
    public void setStartLine(int start) {
        startLine = start;
    }

    /**
     * Sets this FER's endLine to the submitted int.
     * @param end the int to be set as the endLine
     */
    public void setEndLine(int end) {
        endLine = end;
    }

    /**
     * Returns the String representing the funciton's name
     * @return the string representing the function's name
     */
    public String getFunctionName(){
        return functionName;
    }

    /**
     * Returns the int representing the function's start line
     * @returnthe int representing the function's start line
     */
    public int getStartLine() {
        return startLine;
    }

    public Vector getVariables() {
        return symbolTable.getVariables();
    }

    /**
     * Returns the int representing the function's end line
     * @returnthe int representing the function's end line
     */
    public int getEndLine() {
        return endLine;
    }

    /**
     * Returns the int representing the function's current line
     * @returnthe int representing the function's current line
     */
    public int getCurrentLine() {
        return currentLine;
    }

    /**
     * Sets the current line to the submitted line.
     * @param thisLine the int to be set as the current line
     */
    public void setCurrentLine(int thisLine) {
        currentLine = thisLine;
    }

    /**
     * Adds a literal to the symbolTable
     * @param literalName name of the literal to be added
     * @param literalValue value of the literal to be added
     */
    public void addLiteral(String literalName, int literalValue) {
        symbolTable.put(literalName, literalValue);
    }

    /**
     * Pops the given number of literals
     * @param numberToPop the number of literals to pop
     */
    public void popLiterals(int numberToPop) {
        symbolTable.pop(numberToPop);
    }

    /**
     * Returns a String representing this function environment record.
     * @return a String representing this function environment record.
     */
    @Override
    public String toString() {
        String returnString = ("<(");
        returnString += symbolTable.toString();
        if (functionName != null) {
            returnString += (")," +
                getFunctionName()  + "," +
                getStartLine() + "," +
                getEndLine()  + "," +
                getCurrentLine());
        } else {
            returnString += "),-,-,-,-";
        }
        returnString += ">";
        return returnString;
    }

    /**
     * Casts an object to an int
     * @param string an object to be casted to int
     * @return the int version of the given object
     */
    private static int castToInt(Object string) {
        return Integer.parseInt((String)string);
    }


    /**
     * I originally had this class working perfectly well with the
     * VirtualMachine, but then I saw that this main method was required for
     * the milestone, and wrote it. Much had to be changed to "static" in order
     * to get this to work, and "toString()" had to be changed to "getString()."
     * It will all be changed back the moment I turn in this milestone. :(
     *
     * @param args args[0] contains the file name to be processed.
     * @throws FileNotFoundException
     * @throws IOException
     */
 /*   public void main(String[] args) throws FileNotFoundException, IOException {
        boolean isRunning = true;
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        String line = file.readLine(), command;
        StringTokenizer lineTokenizer;
        Vector lineArgs = new Vector();

        while (line != null && isRunning) {
            line = file.readLine();
            System.out.print(line + "\t\t");
            lineTokenizer = new StringTokenizer(line);
            while (lineTokenizer.hasMoreTokens()) {
                lineArgs.add(lineTokenizer.nextToken());
            }

            //I didn't know whether a real file would be used or one like in the
            //reader, so I provided cases for either.
            command = (String)lineArgs.get(0);
            if (command.equals("BS")) {
            } else if (command.equals("FUNCTION") || command.equals("Function")) {
                setFunctionName((String)lineArgs.get(1));
                setStartLine(castToInt(lineArgs.get(2)));
                setEndLine(castToInt(lineArgs.get(3)));
            } else if (command.equals("LINE") || command.equals("Line")) {
                setCurrentLine(castToInt(lineArgs.get(1)));
            } else if ((command.equals("LIT") || command.equals("Enter"))  && (lineArgs.size() > 2)) {
                symbolTable.put((String)lineArgs.get(2), castToInt(lineArgs.get(1)));
            } else if (command.equals("POP") || command.equals("Pop")) {
                symbolTable.pop(castToInt(lineArgs.get(1)));
            } else if (command.equals("HALT")) {
                isRunning = false;
            }

            System.out.println(getString());

            lineArgs.removeAllElements();
        }
        file.close();
    }*/

}