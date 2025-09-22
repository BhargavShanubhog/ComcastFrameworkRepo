package generic;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumber() {
		Random random=new Random();
		int randomNumber=random.nextInt(5000);
		return randomNumber;
	}
	
	public String getSystemdateYYYYDDMM() {
		/*Date dateobj=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String date=sdf.format(dateobj);
		return date;*/
		Date d = new Date();
	    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	    return sim.format(d);
	}
	
	public String getSystemdateYYYYDDMM(int days) {
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate=sdf.format(cal.getTime());
		return reqDate;*/
		Date d = new Date();
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(d);
	    cal.add(Calendar.DAY_OF_MONTH, 30);
	    SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	    return sim.format(cal.getTime());
	}
		

}
