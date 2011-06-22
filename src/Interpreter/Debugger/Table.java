package Interpreter.Debugger;

import java.util.HashMap;

/** <pre>
 *  Binder objects group 3 fields
 *  1. a value
 *  2. the next link in the chain of Strings in the current scope
 *  3. the next link of a previous Binder for the same identifier
 *     in a previous scope
 *  </pre>
*/
class Binder {
    
    private Object value;
    private String prevtop;   // prior String in same scope
    private Binder tail;      // prior binder for same String
                            // restore this when closing scope
    Binder(Object v, String p, Binder t) {
	value = v;
        prevtop = p;
        tail = t;
    }

    Object getValue() {
        return value;
    }

    String getPrevtop() {
        return prevtop;
    }

    Binder getTail() {
        return tail;
    }

}


/** <pre>
 * The Table class is similar to java.util.Dictionary, except that
 * each key must be a String and there is a scope mechanism.
 *
 * Consider the following sequence of events for table t:
 * t.put(String("a"),5)
 * t.beginScope()
 * t.put(String("b"),7)
 * t.put(String("a"),9)
 *
 * Strings will have the key/value pairs for Strings "a" and "b" as:
 *
 * String("a") ->
 *     Binder(9, String("b") , Binder(5, null, null) )
 * (the second field has a reference to the prior String added in this
 * scope; the third field refers to the Binder for the String("a")
 * included in the prior scope)
 * Binder has 2 linked lists - the second field contains list of Strings
 * added to the current scope; the third field contains the list of
 * Binders for the Strings with the same string id - in this case, "a"
 *
 * String("b") ->
 *     Binder(7, null, null)
 * (the second field is null since there are no other Strings to link
 * in this scope; the third field is null since there is no String("b")
 * in prior scopes)
 *
 * top has a reference to String("a") which was the last String added
 * to current scope
 *
 * Note: What happens if a String is defined twice in the same scope??
 * </pre>
*/
public class Table {

    protected HashMap<String,Binder> Strings = new java.util.HashMap<String,Binder>();
    protected String top;    // reference to last String added to
                                            // current scope; this essentially is the
                                            // start of a linked list of Strings in scope
    protected Binder marks;  // scope mark; essentially we have a stack of
                                              // marks - push for new scope; pop when closing
                                              // scope

    /*
    public static void main(String args[]) {
        String s = String.String("a", 1),
            s1 = String.String("b", 2),
            s2 = String.String("c", 3);

        Table t = new Table();
        t.beginScope();
        t.put(s,"top-level a");
        t.put(s1,"top-level b");
        t.beginScope();
        t.put(s2,"second-level c");
        t.put(s,"second-level a");
        t.endScope();
        t.put(s2,"top-level c");
        t.endScope();
    }
    */
    public Table(){}


    /**
    * Gets the object associated with the specified String in the Table.
    */
    public Object get(String key) {
	Binder e = Strings.get(key);
	return e.getValue();
    }

    /**
    * Puts the specified value into the Table, bound to the specified String.<br>
    * Maintain the list of Strings in the current scope (top);<br>
    * Add to list of Strings in prior scope with the same string identifier
    */
    public void put(String key, Object value) {
	Strings.put(key, new Binder(value, top, Strings.get(key)));
	top = key;
    }

    /**
    * Remembers the current state of the Table; push new mark on mark stack
    */
    public void beginScope() {
        marks = new Binder(null,top,marks);
        top = null;
    }

    /**
    * Restores the table to what it was at the most recent beginScope
    *	that has not already been ended.
    */
    public void endScope() {
	while (top!=null) {
	   Binder e = Strings.get(top);
	   if (e.getTail()!=null) Strings.put(top,e.getTail());
	   else Strings.remove(top);
	   top = e.getPrevtop();
	}
	top = marks.getPrevtop();
	marks = marks.getTail();
    }

    /**
   * @return a set of the Table's Strings.
   */
    public java.util.Set<String> keys() {
          return Strings.keySet();
    }

}
