package Interpreter;
import Interpreter.ByteCodes.*;
import java.io.*;
import java.io.IOException;
import java.util.*;

/**
 * Loads ByteCodes from the file into the VirtualMachine.
 * @author Sara Pulis
 */
public class ByteCodeLoader {

    protected String progFile; //Holds the name of the file to be processed.

    /**
     * Default constructor. Using it will cause an IOException to be thrown.
     */
    public ByteCodeLoader() {
        progFile = "";
    }

    /**
     * Constructor. Saves the name of the file to be processed.
     * @param programFile A String representation of the name of the file to be processed.
     * @throws IOException
     */
    public ByteCodeLoader(String programFile) throws IOException {
         progFile = programFile;
    }

    /**
     * Load all codes from file, resolves any branch addresses from symbols to their address in code memory.
     * @return Returns the ByteCode program in an appropriate data structure for processing by VirtualMachine.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     */
    public Program loadCodes() throws FileNotFoundException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        BufferedReader file = new BufferedReader(new FileReader(progFile));
        String line = file.readLine(), codeClass, code;
        Program program = new Program();
        ByteCode byteCode = null;           //Holds the generic ByteCode object
        Vector args = new Vector();         //Holds arguments for each ByteCode

        StringTokenizer lineTokenizer = new StringTokenizer(line);

        while ((line != null) && (lineTokenizer.hasMoreTokens())) {
            code = lineTokenizer.nextToken();

            if (CodeTable.contains(code)) {
                while (lineTokenizer.hasMoreTokens()) {
                    args.add(lineTokenizer.nextToken());
                }
                codeClass = CodeTable.get(code);
                byteCode = (ByteCode)(Class.forName("Interpreter.ByteCodes."+codeClass).newInstance());
                byteCode.init(args);
                program.addByteCode(byteCode);  //Shove ByteCode into Program
            } else {
                program.addByteCode(new DummyCode());
            }

            line = file.readLine();         //Get next line
            if (line != null) {
                lineTokenizer = new StringTokenizer(line);
            }
            args.removeAllElements();       //Empty argument vector
        }
        file.close();                       //Close the BufferedReader
        program.resolveAddress();           //Resolves the addresses
        return program;
    }

}
