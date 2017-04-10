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
    public TimestampConstant(String date) {
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); ft.setLenient(false);
        try {
            val = ft.parse(date);
        } catch (ParseException e) {
            throw new BadSyntaxException();
        }
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
        return ic != null && val.compareTo(ic.val)==0;
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