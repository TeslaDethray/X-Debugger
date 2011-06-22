package Interpreter.Debugger;
import Interpreter.Debugger.UI.UI;
import Interpreter.Interpreter;

/**
 * Debugger implementation of Interpreter.
 *
 * @author Sara Pulis
 */
public class Debugger extends Interpreter {

    	protected DebugByteCodeLoader bcl;
        protected static DebugVM vm;
        protected String sourceFileName;

        /**
         * Default constructor.
         */
        public Debugger() {}

        /**
      * Constructor. Takes the file at the location given and begins
      * processing the DebuggerByteCodes therein, then initiates the DebugVM.
      * @param codeFile A String representing the file name to be processed.
      */
	public Debugger(String codeFile) {
            sourceFileName = codeFile + ".x";
            DebugCodeTable.init();
            bcl = new DebugByteCodeLoader((codeFile + ".x.cod"));
            try {
                DebugProgram program = bcl.loadCodes();
                vm = new DebugVM(program);
            } catch (Exception e) {}
	}

        /**
         * Runs the UI - new UIs will require this to be retargeted.
         * @param codeFile Basename of the X and ByteCode files.
         * @throws Exception
         */
        public void run(String codeFile) throws Exception {
            (new UI(codeFile)).run();
        }

}
