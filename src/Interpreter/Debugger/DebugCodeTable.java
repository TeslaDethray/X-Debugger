package Interpreter.Debugger;
import Interpreter.CodeTable;

/**
 * Debugger implementation of CodeTable.
 * Added are PROGRAM, FORMAL, and LINE.
 * The rest are updates to point to their Debugger equivalents.
 *
 * @author Sara Pulis
 */
public class DebugCodeTable extends CodeTable {

    /**
    * Fills in all the possible values for ByteCodes.
    */
    public static void init() {
        ByteCodes.put("ARGS", "Interpreter.Debugger.DebuggerByteCodes.DebuggerArgsCode");
        ByteCodes.put("BOP", "Interpreter.Debugger.DebuggerByteCodes.DebuggerBopCode");
        ByteCodes.put("CALL", "Interpreter.Debugger.DebuggerByteCodes.DebuggerCallCode");
        ByteCodes.put("DUMP", "Interpreter.ByteCodes.DumpCode");
        ByteCodes.put("FALSEBRANCH", "Interpreter.ByteCodes.BranchingByteCodes.FalsebranchCode");
        ByteCodes.put("FORMAL", "Interpreter.Debugger.DebuggerByteCodes.DebuggerFormalCode");
        ByteCodes.put("FUNCTION", "Interpreter.Debugger.DebuggerByteCodes.DebuggerFunctionCode");
        ByteCodes.put("GOTO", "Interpreter.ByteCodes.BranchingByteCodes.GotoCode");
        ByteCodes.put("HALT", "Interpreter.Debugger.DebuggerByteCodes.DebuggerHaltCode");
        ByteCodes.put("LABEL", "Interpreter.ByteCodes.LabelCode");
        ByteCodes.put("LINE", "Interpreter.Debugger.DebuggerByteCodes.DebuggerLineCode");
        ByteCodes.put("LIT", "Interpreter.Debugger.DebuggerByteCodes.DebuggerLitCode");
        ByteCodes.put("LOAD", "Interpreter.Debugger.DebuggerByteCodes.DebuggerLoadCode");
        ByteCodes.put("POP", "Interpreter.Debugger.DebuggerByteCodes.DebuggerPopCode");
        ByteCodes.put("READ", "Interpreter.Debugger.DebuggerByteCodes.DebuggerReadCode");
        ByteCodes.put("RETURN", "Interpreter.Debugger.DebuggerByteCodes.DebuggerReturnCode");
        ByteCodes.put("STORE", "Interpreter.Debugger.DebuggerByteCodes.DebuggerStoreCode");
        ByteCodes.put("WRITE", "Interpreter.Debugger.DebuggerByteCodes.DebuggerWriteCode");
    }

}
