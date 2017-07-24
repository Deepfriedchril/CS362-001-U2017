package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Iterator;

import org.junit.*;

import static org.junit.Assert.*;


public class CalDayTest {
    CalDay day = new CalDay();
    Calendar now;
    int thisMonth;
    int thisYear;
    int thisDay;

    // Separate list for events
    LinkedList<Appt> listAppts = new LinkedList<Appt>();

    @Before 
    public void init() {
        //get todays date
        now = Calendar.getInstance();
        //current month/year/date is today
        thisMonth = now.get(Calendar.MONTH)+1;
        thisYear = 2016;//now.get(Calendar.YEAR);
        thisDay = now.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
        day = new CalDay(today);

        int startHour=15;
        int startMinute=30;
        int startDay=thisDay;      //11
        int startMonth=thisMonth;  //4
        int startYear=thisYear;    //2017/2017
        String title="Birthday Party";
        String description="This is my birthday party.";
        
        //Construct a new Appointment object with the initial data  
        Appt appt = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
        int[] anArray={2,3,4};
        appt.setRecurrence( anArray, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);

// event with later start time than the previous
        startHour=16;
        startMinute=30;
        startDay=thisDay;      //11
        startMonth=thisMonth;  //4
        startYear=thisYear;    //2017
        title="Class";
        description="Rescheduled class.";
        //Construct a new Appointment object with the initial data  
        appt = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
        listAppts.add(appt);

// invalid event
        startHour=1;
        startMinute=30;
        startDay=thisDay;      //11
        startMonth=thisMonth;  //4
        startYear=thisYear;    //2017
        title="Class";
        description="Rescheduled class.";
        //Construct a new Appointment object with the initial data  
        appt = new Appt(startHour,
                 startMinute ,
                 startDay ,
                 startMonth ,
                 startYear ,
                 title,
                description);
        appt.setStartHour(-1);
        listAppts.add(appt);
    }

// Check adding to list
    @Test
    public void test01()  throws Throwable  {
        assertTrue(listAppts.get(0).getValid());
        day.addAppt(listAppts.get(0));
        assertTrue(listAppts.get(1).getValid());
        day.addAppt(listAppts.get(1));
        day.addAppt(listAppts.get(2));
        assertEquals(2, day.getSizeAppts());   
    }

// Month
    @Test
    public void test02()  throws Throwable  {
        assertEquals(thisMonth, day.getMonth());
    }
    
    // day
    @Test
    public void test03()  throws Throwable  {
        assertEquals(thisDay, day.getDay());
        assertEquals(thisYear, day.getYear());
    }
    
    // year
    @Test
    public void test04()  throws Throwable  {
        assertEquals(thisYear, day.getYear());
    }

    // printout valid
    @Test
    public void test05() throws Throwable {
        Assume.assumeTrue(listAppts.get(0).getValid());
        day.addAppt(listAppts.get(0));
        Assume.assumeTrue(listAppts.get(1).getValid());
        day.addAppt(listAppts.get(1));

        StringBuilder sb = new StringBuilder();
        String todayDate = thisMonth + "/" + thisDay + "/" + thisYear;
        sb.append("\t --- " + todayDate + " --- \n");
        sb.append(" --- -------- Appointments ------------ --- \n");
        Object element = listAppts.get(0);
        sb.append(element);
        element = listAppts.get(1);
        sb.append(element + " \n");
        // System.out.println(sb.toString());
        // System.out.println(day.toString());
        assertEquals(sb.toString(), day.toString());
    }

    // printout invalid
    @Test
    public void test06() throws Throwable {
        StringBuilder sb = new StringBuilder();
        String todayDate = thisMonth + "/" + thisDay + "/" + thisYear;
        sb.append("\t --- " + todayDate + " --- \n");
        sb.append(" --- -------- Appointments ------------ --- \n");
       

        CalDay invalid = new CalDay();
        // System.out.println(sb.toString());
        // System.out.println(day.toString());
        assertEquals(sb.toString(), invalid.toString());
    }

    // invalid itr
    @Test
    public void test07() throws Throwable {
       CalDay invalid = new CalDay();
       Iterator<?> temp = invalid.iterator();
       assertNull(temp);
    }

    // valid itr
    @Test
    public void test08() throws Throwable {
       Iterator<?> temp = day.iterator();
       assertNotNull(temp);
    }

}


