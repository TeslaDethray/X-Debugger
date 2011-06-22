package Interpreter.Debugger;
import Interpreter.*;
import Interpreter.ByteCodes.ByteCode;
import java.io.*;
import java.util.*;

/**
 * Debugger implementation of DebuggerByteCodeLoader.
 *
 * @author Sara Pulis
 */
public class DebugByteCodeLoader extends ByteCodeLoader{

    public DebugByteCodeLoader(String codeFile) {
        progFile = codeFile;
    }

    @Override
    public DebugProgram loadCodes() throws FileNotFoundException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
        BufferedReader file = new BufferedReader(new FileReader(progFile));
        String line = file.readLine(), codeClass, code;
        DebugProgram program = new DebugProgram();
        ByteCode byteCode = null;           //Holds the generic DebuggerByteCode object
        Vector args = new Vector();         //Holds arguments for each DebuggerByteCode

        StringTokenizer lineTokenizer = new StringTokenizer(line);

        while ((line != null) && (lineTokenizer.hasMoreTokens())) {
            code = lineTokenizer.nextToken();
            while (lineTokenizer.hasMoreTokens()) {
                args.add(lineTokenizer.nextToken());
            }
            codeClass = DebugCodeTable.get(code);
            byteCode = (ByteCode) (Class.forName(codeClass).newInstance());
            byteCode.init(args);
            program.addByteCode(byteCode);  //Shove DebuggerByteCode into DebugProgram
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
