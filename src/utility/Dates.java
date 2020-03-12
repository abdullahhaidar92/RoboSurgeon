package utility;

import java.util.Date;
import java.util.GregorianCalendar;

public class Dates {
    private static GregorianCalendar calendar1=new GregorianCalendar();
    private static GregorianCalendar calendar2=new GregorianCalendar();

    public static long subtract(Date date1,Date date2){
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.getTimeInMillis()-calendar2.getTimeInMillis();

    }
}
