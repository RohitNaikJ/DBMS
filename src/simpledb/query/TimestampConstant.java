package simpledb.query;

import simpledb.parse.BadSyntaxException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class that wraps Java ints as database constants.
 * @author Rohit Naik
 */
public class TimestampConstant implements Constant {
    private Date val;

    /**
     * Create a constant by wrapping the specified int.
     * @param date the String date value
     */
    public TimestampConstant(Date date) {
        val = date;
    }

    /**
     * Unwraps the Integer and returns it.
     * @see simpledb.query.Constant#asJavaVal()
     */
    public Object asJavaVal() {
        return val;
    }

    public boolean equals(Object obj) {
        TimestampConstant ic = (TimestampConstant) obj;
        return ic != null && val.equals(ic.val);
    }

    public int compareTo(Constant c) {
        TimestampConstant ic = (TimestampConstant) c;
        return val.compareTo(ic.val);
    }

    public int hashCode() {
        return val.hashCode();
    }

    public String toString() {
        return val.toString();
    }
}