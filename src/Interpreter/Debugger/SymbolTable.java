package Interpreter.Debugger;

import java.util.Vector;

public class SymbolTable extends Table {

    public SymbolTable() {}

    @Override
    public String toString() {
        String  returnString = "";
        Object keys[] = Strings.keySet().toArray();

        for (int i = 0; i < keys.length; i++) {
            returnString = ("<" + keys[i] + "," + get((String)keys[i]) + ">") + returnString;
        }
        return returnString;
    }

    /**
     * Returns a Vector in which every odd object is a variable name and every
     * even is its corresponding value.
     * @return Vector representing the literals entered into this function.
     */
    public Vector getVariables() {
        Vector variables = new Vector();
        Object keys[] = Strings.keySet().toArray();

        for (int i = 0; i < keys.length; i++) {
            variables.add(keys[i]);
            variables.add(get((String) keys[i]));
        }
        return variables;
    }

    void pop(Integer integer) {
        if (!Strings.isEmpty() && integer != 0) {
            Object keys[] = Strings.keySet().toArray();                 //Make an array of keys
            String dumpKey = (String)keys[0];                                     //Get the final key

            top = Strings.get(dumpKey).getPrevtop();                      //Revert the top binder key
            Strings.remove(dumpKey);                                                    //Remove the old top from the hashmap

            integer--;                                                                                //Decrement integer
            if(integer != 0) {
                pop(integer);                                                                      //If the popping isn't done, run it again with the decremented integer.
            }
        }
    }

    /**
    * Puts the specified value into the Table, bound to the specified String.<br>
    * Maintain the list of Strings in the current scope (top);<br>
    * Add to list of Strings in prior scope with the same string identifier
    */
    @Override
    public void put(String key, Object value) {
        if (Strings.get(key) != null) {
            Strings.remove(key);
        }
        Strings.put(key, new Binder(value, top, Strings.get(key)));
        top = key;
    }

}
