package Interpreter;
import java.util.Hashtable;

/**
 * Holds and initializes the ability to translate codes to create instances of the classes.
 * @author Sara Pulis
 */
public class CodeTable {

    protected static Hashtable<String, String> ByteCodes = new Hashtable(); //Holds the commands and their translations.

    /**
     * Constructor. Does nothing.
     */
    public CodeTable() {}

    /**
     * Returns the translation of the ByteCode command.
     * @param code A String representing the ByteCode command to be processe.
     * @return A String representing the name of the class corresponding to the ByteCode command.
     */
    public static String get(String code) {
        return ByteCodes.get(code);
    }

    public static boolean contains(String code) {
        return ByteCodes.containsKey(code);
    }

    /**
     * Fills in all the possible values for ByteCodes.
     */
    public static void init() {
        ByteCodes.put("ARGS", "ArgsCode");
        ByteCodes.put("BOP", "BopCode");
        ByteCodes.put("CALL", "BranchingByteCodes.CallCode");
        ByteCodes.put("DUMP", "DumpCode");
        ByteCodes.put("FALSEBRANCH", "BranchingByteCodes.FalsebranchCode");
        ByteCodes.put("GOTO", "BranchingByteCodes.GotoCode");
        ByteCodes.put("HALT", "HaltCode");
        ByteCodes.put("LABEL", "LabelCode");
        ByteCodes.put("LIT", "LitCode");
        ByteCodes.put("LOAD", "LoadCode");
        ByteCodes.put("POP", "PopCode");
        ByteCodes.put("READ", "ReadCode");
        ByteCodes.put("RETURN", "ReturnCode");
        ByteCodes.put("STORE", "StoreCode");
        ByteCodes.put("WRITE", "WriteCode");
    }

}