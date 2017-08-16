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
 * Random Test Generator  for TimeTable class.
 */

public class TimeTableRandomTest {
        private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 30 seconds */
	
    /**
     * Generate Random Tests that tests TimeTable Class.
     */
	@Test
	public void radnomtestdeleteappt()  throws Throwable  {
	    //get todays date
        Calendar now = Calendar.getInstance();
        TimeTable time = new TimeTable();

        long randomseed = System.currentTimeMillis();
        Random random = new Random(randomseed);

        //current month/year/date is today
        int thisMonth = now.get(Calendar.MONTH)+1;
        int thisYear = 2016;//now.get(Calendar.YEAR);
        int thisDay = now.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
//        day = new CalDay(today);
        LinkedList<Appt> listAppts = new LinkedList<Appt>();
        LinkedList<Appt> emptylistAppts = new LinkedList<Appt>();
        Appt fakeappt = null;
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
            } else {
                listAppts.add(appt);
            }
            time.deleteAppt(emptylistAppts, appt);
            time.deleteAppt(listAppts,fakeappt);
        }

        //delete the entire list randomly
        while (listAppts.size() > 0){
            temp = ThreadLocalRandom.current().nextInt(numappts);
            numappts--;
            Appt target = listAppts.get(temp);
            
            if ( (target == null) || (target.getValid() == false) ){
                assertNull(time.deleteAppt(listAppts,target));
            } else {
                assertNotNull(time.deleteAppt(listAppts,target));
            }
        }
    }

    @Test(timeout=TestTimeout)
    public void radnomtestApptRange()  throws Throwable  {
        
        //get todays date
        Calendar now = Calendar.getInstance();
        TimeTable time = new TimeTable();

        long randomseed = System.currentTimeMillis();
        Random random = new Random(randomseed);

        //current month/year/date is today
        int thisMonth = now.get(Calendar.MONTH)+1;
        int thisYear  = now.get(Calendar.YEAR);
        int thisDay   = now.get(Calendar.DAY_OF_MONTH);

        LinkedList<Appt> listAppts = new LinkedList<Appt>();
        LinkedList<Appt> emptylistAppts = new LinkedList<Appt>();
        Appt fakeappt = null;
        
        int min = -100;
        int max = 100;
        int temp = 0;
        int numappts = 100 + ValuesGenerator.RandInt(random);
        int invalid = 0;
        
        for (temp = 0; temp < numappts; temp++){
            int startYear   =  thisYear;
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
            else {
                listAppts.add(appt);
            }
        }

        
        GregorianCalendar date1 = new GregorianCalendar(thisYear,thisMonth,thisDay);
        GregorianCalendar date2;
        int lastiteration = ThreadLocalRandom.current().nextInt(100, 300);

        for (int iterations = 0; iterations < lastiteration; iterations++){
            date2 = (GregorianCalendar)date1.clone();
            date2.add(Calendar.DAY_OF_MONTH, ThreadLocalRandom.current().nextInt(-10,100) );

            // invalid use - day before last day
            if (!date1.before(date2)){
                try{
                    time.getApptRange(listAppts, date1, date2);
                    fail();
                } catch (DateOutOfRangeException e) {}
            } 
            // normal use
            else {
                assertNotNull(time.getApptRange(listAppts, date1, date2));
            }
        }
    }


}