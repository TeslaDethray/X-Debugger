package Interpreter;
import Interpreter.Debugger.Debugger;
import java.io.*;

/**
 * Takes care of running the virtual machine.
 * @author Professor Levine
 */
public class Interpreter {

	ByteCodeLoader bcl;

        /**
      * Default constructor. Does nothing.
      */
        public Interpreter() {}

        /**
      * Constructor. Takes the file at the location given and begins
      * processing the ByteCodes therein.
      * @param codeFile A String representing the file name to be processed.
      */
	public Interpreter(String codeFile) {
		try {
			CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}

        /**
      * Runs the processes and runs the program.
      * @throws FileNotFoundException
      * @throws IOException
      * @throws InstantiationException
      * @throws ClassNotFoundException
      * @throws IllegalAccessException
      */
	void run() throws FileNotFoundException, IOException, InstantiationException, ClassNotFoundException, IllegalAccessException {
		Program program = bcl.loadCodes();
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}

        /**
      * Runs the interpreter.
      * @param args The first argument is a string representation of the location of the file containing ByteCodes to be processed. No other items in args will be accessed.
      * @throws FileNotFoundException
      * @throws IOException
      * @throws InstantiationException
      * @throws ClassNotFoundException
      * @throws IllegalAccessException
      */
	public static void main(String args[]) throws Exception {
		if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java Interpreter.Interpreter <file>");
			System.exit(1);
		}
                if (args[0].equals("-d")) {
                    if (args.length < 2) {
                        System.out.println("***Incorrect usage, try: java Interpreter.Interpreter -d <file name sans extension>");
			System.exit(1);
                    } else {
                        (new Debugger()).run(args[1]);
                    }
                } else {
                    (new Interpreter(args[0])).run();
                }
	}
}