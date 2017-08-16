package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.*;

import static org.junit.Assert.*;



/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
    private static final int NUM_TESTS=100;
    
    long randomseed = System.currentTimeMillis();
    Random random = new Random(randomseed);

    CalDay day = new CalDay();
    Calendar now;

    // Separate list for events
    LinkedList<Appt> listAppts = new LinkedList<Appt>();

    @Test
    public void randomtestaddappt() {
        //get todays date
        now = Calendar.getInstance();
        //current month/year/date is today
        int thisMonth = now.get(Calendar.MONTH)+1;
        int thisYear = 2016;//now.get(Calendar.YEAR);
        int thisDay = now.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
        day = new CalDay(today);

        int min = -100;
        int max = 100;
        int temp = 0;
        int numappts = 100 + ValuesGenerator.RandInt(random);
        int invalid = 0;
        for (temp = 0; temp < numappts; temp++){
            int startYear   = ThreadLocalRandom.current().nextInt(1000);
            int startHour   = ThreadLocalRandom.current().nextInt(min, max);
            int startMinute = ThreadLocalRandom.current().nextInt(min, max);
            int startDay    = ThreadLocalRandom.current().nextInt(min, max);
            int startMonth  = ThreadLocalRandom.current().nextInt(min, max);
            String title    = ValuesGenerator.getString(random);
            String description = ValuesGenerator.getString(random);

            Appt appt = new Appt(startHour,
                      startMinute ,
                      startDay ,
                      startMonth ,
                      startYear ,
                      title,
                      description);
            if ( !appt.getValid() ) {
                invalid++;
                temp--;
            }
            day.addAppt(appt);
        }

        temp = 0;
        Iterator<?> cur = day.iterator(); 
        while (cur.hasNext()){
            temp++;
            cur.next();
        }

        assertEquals(numappts, temp);
    }
}