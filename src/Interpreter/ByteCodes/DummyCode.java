package Interpreter.ByteCodes;

import Interpreter.VirtualMachine;
import java.util.Vector;

/**
 * A ByteCode whose sole purpose is to stand in for unprocessable ByteCodes
 * so as to allow the program to continue smoothly.
 *
 * @author Sara Pulis
 */
public class DummyCode extends ByteCode{

    @Override
    public void init(Vector args) {}

    @Override
    public void execute(VirtualMachine virtualMachine) {}

    @Override
    public String toString() {return ("--Unrecognized ByteCode--");}

}
