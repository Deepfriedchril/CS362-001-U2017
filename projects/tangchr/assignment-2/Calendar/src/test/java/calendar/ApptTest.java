package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.*;

import static org.junit.Assert.*;

public class ApptTest {
    /**
     * Test that the gets methods work as expected.
     */
	 @Test
	  public void test01()  throws Throwable  {
		 int startHour=13;
		 int startMinute=30;
		 int startDay=10;
		 int startMonth=4;
		 int startYear=2017;
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
	// assertions
		 assertTrue(appt.getValid());
		 assertEquals(13, appt.getStartHour());
		 assertEquals(30, appt.getStartMinute());
		 assertEquals(10, appt.getStartDay());
		 assertEquals(04, appt.getStartMonth());
		 assertEquals(2017, appt.getStartYear());
		 assertEquals("Birthday Party", appt.getTitle());
		 assertEquals("This is my birthday party.", appt.getDescription());         		
	 }

	 // Test invalid times
	@Test
	public void test02()  throws Throwable  {
	  	int startHour = 0;
		int startMinute = 0;
		int startDay = 0;
		int startMonth = 0;
		int startYear = 0;
		String title = "Birthday Party";
		String description ="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		        description);

		int NumDaysInMonth = CalendarUtil.NumDaysInMonth(startYear,startMonth);
	// assertions
		appt.setStartHour(-1);
		assertFalse(appt.getValid());
		assertEquals(null, appt.toString());
	 	appt.setStartHour(25);
	 	assertFalse(appt.getValid());
	 	appt.setStartHour(10);
	 	appt.setStartMinute(-1);
	 	assertFalse(appt.getValid());
	 	appt.setStartMinute(61);
	 	assertFalse(appt.getValid());
	 	appt.setStartMinute(1);
	 	appt.setStartDay(0);
	 	assertFalse(appt.getValid());
	 	appt.setStartDay(NumDaysInMonth + 1);
	 	assertFalse(appt.getValid());
	 	appt.setStartDay(5);
	 	appt.setStartMonth(0);
	 	assertFalse(appt.getValid());
	 	try{
	 		appt.setStartMonth(14);
	 		assertTrue(!appt.getValid());
	 	} catch(IndexOutOfBoundsException e){}

	 	appt.setStartMonth(1);
	 	appt.setStartYear(2018);
	 	assertEquals(2018, appt.getStartYear());
	 }

	// Test recurrence
	@Test
	public void test03()  throws Throwable  {
	  	int startHour = 0;
		int startMinute = 0;
		int startDay = 0;
		int startMonth = 0;
		int startYear = 0;
		String title = "Birthday Party";
		String description ="This is my birthday party.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		        description);

		int[] array = {1,2,3,4,5,6,7};
		assertFalse(appt.isRecurring());
		appt.setRecurrence(array, 5, 1, 1);
		assertArrayEquals(array, appt.getRecurDays());
		try{
			appt.setRecurrence(null, 1, 2, 3);
			assertEquals(0, appt.getRecurDays());
		} catch(AssertionError e) { }
		assertEquals(3, appt.getRecurNumber());
		assertEquals(2, appt.getRecurIncrement());
		assertEquals(1, appt.getRecurBy());
		assertTrue(appt.isRecurring());
	// assertions
	}

	// Test printout
	@Test
	public void test04()  throws Throwable  {
	  	int startHour = 18;
		int startMinute = 0;
		int startDay = 23;
		int startMonth = 7;
		int startYear = 2017;
		String title = "Dinner";
		String description ="Dinner with SO.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		        description);
		String comp = "\t" + startMonth + "/" + startDay + "/" + startYear + " at " + "6:0pm ," + title + ", " + description + "\n";
		assertEquals(comp, appt.toString());
	}

	@Test
	public void test05()  throws Throwable  {
	  	int startHour = 11;
		int startMinute = 0;
		int startDay = 23;
		int startMonth = 7;
		int startYear = 2017;
		String title = "Dinner";
		String description ="Dinner with SO.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		        description);
		String comp = "\t" + startMonth + "/" + startDay + "/" + startYear + " at " + "11:0am ," + title + ", " + description + "\n";
		assertEquals(comp, appt.toString());
	}

	@Test
	public void test06()  throws Throwable  {
	  	int startHour = 0;
		int startMinute = 0;
		int startDay = 23;
		int startMonth = 7;
		int startYear = 2017;
		String title = "Dinner";
		String description ="Dinner with SO.";
		//Construct a new Appointment object with the initial data	 
		Appt appt = new Appt(startHour,
		        startMinute ,
		        startDay ,
		        startMonth ,
		        startYear ,
		        title,
		        description);
		String comp = "\t" + startMonth + "/" + startDay + "/" + startYear + " at " + "12:0am ," + title + ", " + description + "\n";
		assertEquals(comp, appt.toString());
	}
}
