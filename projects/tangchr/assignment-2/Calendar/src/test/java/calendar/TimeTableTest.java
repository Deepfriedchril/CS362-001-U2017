package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.*;

import static org.junit.Assert.*;

public class TimeTableTest {
    CalDay day;
    Calendar now;
    GregorianCalendar firstday;
    GregorianCalendar lastday;
    int thisMonth;
    int thisYear;
    int thisDay;

    TimeTable table = new TimeTable();

    // Separate list for events
    LinkedList<Appt> listAppts = new LinkedList<Appt>();
    LinkedList<CalDay> listDay;
    @Before 
    public void init() {
        //get todays date
        now = Calendar.getInstance();
        //current month/year/date is today
        thisMonth = now.get(Calendar.MONTH)+1;
        thisYear = now.get(Calendar.YEAR);
        thisDay = now.get(Calendar.DAY_OF_MONTH);
        firstday = new GregorianCalendar(thisYear, thisMonth, thisDay);
        lastday = (GregorianCalendar)firstday.clone();
        

        day = new CalDay(firstday);

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
        appt.setRecurrence( anArray, Appt.RECUR_BY_MONTHLY, 1, 1);
        listAppts.add(appt);

// event with later start time than the previous
        startHour=16;
        startMinute=30;
        startDay=thisDay + 1;      //11
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
        //int[] recurringDays =  {2,3,4};
        //appt.setRecurrence( recurringDays, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);

// event with later start time than the previous repeat daily
        startHour=16;
        startMinute=30;
        startDay=thisDay + 1;      //11
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
        int[] recurringDays = new int[1];
        //recurringDays[0] = 7;
        appt.setRecurrence(null, Appt.RECUR_BY_WEEKLY, 1, 1000);  // recur sun,mon, weekly for 1 week
        listAppts.add(appt);

// event with later start time than the previous repeat monthly
        startHour=16;
        startMinute=30;
        startDay=thisDay + 1;      //11
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
        //int[] recurringDays =  {0,1};
        appt.setRecurrence(null, Appt.RECUR_BY_MONTHLY, 1, 1000);  // recur sun,mon, weekly for 1 week
        listAppts.add(appt);


// event with later start time than the previous repeat yearly
        startHour=16;
        startMinute=30;
        startDay=thisDay + 1;      //11
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
        //int[] recurringDays =  {0,1};
        appt.setRecurrence(null, Appt.RECUR_BY_YEARLY, 1, 1000);  // recur sun,mon, weekly for 1 week
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

    // Firstday == lastday
    @Test
    public void test01() {
        try{
            table.getApptRange(listAppts, firstday, lastday);
            fail();
        } catch (DateOutOfRangeException e){

        }
    }

        // Firstday < lastday
    @Test
    public void test02() throws Throwable {
        //Increase today day to +5
        lastday.add(Calendar.DAY_OF_MONTH,5);
        listDay = table.getApptRange(listAppts, firstday, lastday);
        assertNotNull(listDay);

    }
    // Firstday > lastday
    @Test
    public void test03() {
        try{
            lastday.add(Calendar.DAY_OF_MONTH,-5);
            table.getApptRange(listAppts, firstday, lastday);
            fail();
        } catch (DateOutOfRangeException e){

        }
    }

//monthly repeat
    @Test
    public void test04() throws Throwable {
        lastday.set(thisYear, thisMonth+2, thisDay+5);
        listDay = table.getApptRange(listAppts, firstday, lastday);
        assertNotNull(listDay);

    }
	
//yearly repeat
    @Test
    public void test05() throws Throwable {
        lastday.set(thisYear+1, thisMonth+2, thisDay+5);
        listDay = table.getApptRange(listAppts, firstday, lastday);
        assertNotNull(listDay);

    }

//Invalid end date 
    @Test
    public void test06() throws Throwable {
        //Increase today day to +31
        lastday.set(thisYear, thisMonth, thisDay+31);
        listDay = table.getApptRange(listAppts, firstday, lastday);
        assertNotNull(listDay);

    }

//delete invalid
    @Test
    public void test07() throws Throwable {
        LinkedList<Appt> temp = new LinkedList<Appt>();
        assertNull(table.deleteAppt(temp, listAppts.get(3)) );


    }
//delete not in list
    @Test
    public void test08() throws Throwable {
        Appt temp = new Appt(1,1,1,1,2017,"test", "just a test");
        assertNull(table.deleteAppt(listAppts, temp) );
    }

//delete valid
    @Test
    public void test09() throws Throwable {
        assertNotNull(table.deleteAppt(listAppts, listAppts.get(3)) );

    }

//delete null appt
    @Test
    public void test10() throws Throwable {
        Appt temp = null;
        assertNull(table.deleteAppt(listAppts, temp) );

    }
//delete null appt null list
    @Test
    public void test11() throws Throwable {
        Appt temp = null;
        assertNull(table.deleteAppt(null, temp) );

    }

//delete invalid
    @Test
    public void test12() throws Throwable {
        Appt temp = new Appt(1,1,1,1,2017,"test", "just a test");
        temp.setStartHour(33);
        assertNull(table.deleteAppt(listAppts, temp) );
    }



// permute
    @Test
    public void test13() throws Throwable{
        int[] pv = {1,2,3,4,0};
        LinkedList<Appt> newlist = table.permute(listAppts, pv);
        assertNotNull(newlist);
    }

}
/* 
package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 *
 *
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;


import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTableTest {

     @Test
    // Covered goals for unit test test01():
    // The goal of the test01 is to call the public method getApptRange with the exact objects too cover the following targets:
    // Switch Statement in the private method getNextApptOccurrence
    // in particular case Appt.RECUR_BY_MONTHLY
     public void test01()  throws Throwable  {
         TimeTable t=new TimeTable();


         //create linked list of Appt type
        //create Appt objects
        //add the object to the linked list
        //create first day and last day of type GregorianCalendar today = new GregorianCalendar(2, 3, 4);
            //get todays date
            Calendar rightnow = Calendar.getInstance();
            //current month/year/date is today
            int thisMonth = rightnow.get(Calendar.MONTH)+1;
            int thisYear = rightnow.get(Calendar.YEAR);
            int thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
            
             int startHour=15;
             int startMinute=30;
             int startDay=thisDay;      //11
             int startMonth=thisMonth;  //4
             int startYear=thisYear;    //2017
        String title="Birthday Party";
        String description="This is my birthday party.";
        // Create a valid appointment!
        Appt appt = new Appt(startHour, startMinute , startDay , startMonth , startYear , title, description);
        // Collection of all of the appointments for the calendar day *   
        LinkedList<Appt> appts = new LinkedList<Appt>();
        appts.add(appt);

        //Change the  default recurring information in the object appt using setRecurrence method in the Appt class 
        int[] recurringDays =  new int[0];
       appt.setRecurrence(recurringDays, Appt.RECUR_BY_MONTHLY, 0, 1);
        //get a list of appointments for one day that are occurred between today and tomorrow!
        GregorianCalendar firstDay = new GregorianCalendar(thisYear,thisMonth,thisDay);
        //Create an identical object for the firstDay
        GregorianCalendar lastDay = (GregorianCalendar)firstDay.clone();
        //Increase today day to 1
        lastDay.add(Calendar.DAY_OF_MONTH,1);
       try {
          // Retrieves a range of appointments between two dates (i.e., today and tomorrow).
           t.getApptRange(appts, firstDay, lastDay);
       }
       catch( NullPointerException e)
        {

        }

     }


}

*/