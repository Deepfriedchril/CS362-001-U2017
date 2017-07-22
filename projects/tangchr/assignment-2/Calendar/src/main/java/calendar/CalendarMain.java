package calendar;
/*
 * CalendarMain.java
 *
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;




public class CalendarMain {
	public static void main(String[] args) throws DateOutOfRangeException {
        //Internal Diagnostic Messages turned on when true
		 boolean diagnose = true;

		/** Collection of all of the appointments for the calendar day */		
		LinkedList<Appt> listAppts = new LinkedList<Appt>();
		System.out.println("Calendar Main: \n" );
		
		
     	/** the month the user is currently viewing **/
     	int thisMonth;
    	
    	/** the year the user is currently viewing **/
    	 int thisYear;
    	
    	/** todays date **/
    	int thisDay;
    	
		//get todays date
    	Calendar rightnow = Calendar.getInstance();
    	//current month/year/date is today
    	thisMonth = rightnow.get(Calendar.MONTH)+1;
		thisYear = rightnow.get(Calendar.YEAR);
		thisDay = rightnow.get(Calendar.DAY_OF_MONTH);
		
		 int startHour=15;
		 int startMinute=30;
		 int startDay=thisDay;  	//11
		 int startMonth=thisMonth; 	//4
		 int startYear=thisYear; 	//2017/2017
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
         if(diagnose){
          	System.out.println(appt.toString());
          } 
         
         listAppts.add(appt);
          
      // create another appointment
         startHour=14;
		 startMinute=30;
		 startDay=thisDay;  	//11
		 startMonth=thisMonth; 	//4
		 startYear=thisYear; 	//2017
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
         
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);
         // create another appointment
         startHour=13;
		 startMinute=30;
		 startDay=thisDay;		//11
		 startMonth=thisMonth;	//4
		 startYear=thisYear;	//2017
		 title="Meeting Today";
		 description="Meeting with the students.";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);
         // create another appointment
         startHour=16;
		 startMinute=30;
		 startDay=thisDay+1;		//11
		 startMonth=thisMonth+1;	//4
		 startYear=thisYear;	//2017
		 title="Visit";
		 description="Visiting my parents!";
		 //Construct a new Appointment object with the initial data	 
         appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);
         
         if(diagnose){
         	System.out.println(appt.toString());
         }
         listAppts.add(appt);

		//get a list of appointments for one day, which is today
		GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
		GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
		tomorrow.add(Calendar.DAY_OF_MONTH,1);
		if(diagnose){
			System.out.println("today is:" + today.get(Calendar.MONTH)+ "/"+ today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
			System.out.println("tomorrow is:" + tomorrow.get(Calendar.MONTH)+ "/"+ tomorrow.get(Calendar.DAY_OF_MONTH)+"/"+tomorrow.get(Calendar.YEAR));
		}
		
		TimeTable timeTable=new TimeTable();
        //Create a linked list of calendar days to return
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();

		
        int[] pv={1,2,3,0};
        LinkedList<Appt> listUpdatedAppts=timeTable.permute(listAppts, pv);
		if (diagnose) {
			if( listUpdatedAppts!=null){
				System.out.println(" Before Sorting" );
			for (int i = 0; i < listAppts.size(); i++)
				System.out.println(listAppts.get(i).toString());
			System.out.println(" After Sorting" );

			for (int i = 0; i < listUpdatedAppts.size(); i++)
				System.out.println(listUpdatedAppts.get(i).toString());
			}
		}		
		
		
		//delete a particular appointment from the list
		if(diagnose){
			System.out.println("The number of appointments:  "+ listAppts.size() );

			System.out.println("Delete  "+ listAppts.get(2) );
		}
		LinkedList<Appt> listDeletedAppts=timeTable.deleteAppt(listAppts, listAppts.get(2));
		if(diagnose){
			System.out.println("The number of appointments:  "+ listDeletedAppts.size() );

		}


        System.exit(0);
		
	}
}
