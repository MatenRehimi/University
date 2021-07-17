package assignment14.Model;

import java.text.SimpleDateFormat; 
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateVerifier {

	private String day;
	private String orderIndicator;
	private String month;
	private int dayOfMonth;
	private LocalDate dateNow;
	private Calendar calendar;
	public static Pattern pWholeDate = Pattern.compile("((\\A|\\s+)"+ "(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day))"
			+ ("\\s+(\\d?\\d)")+ ("(st|nd|rd|th)") + "(\\s+)([Jj]anuary|[fF]ebruary|[mM]arch|[aA]pril|[mM]ay|[jJ]une|[jJ]uly|[aA]ugust|[sS]eptember|[nN]ovember|[dD]ecember)");
	public static Pattern pDate = Pattern.compile("(\\s*)"+"(\\d\\d(-|/)\\d\\d(-|/)\\d\\d\\d\\d)");
	public static Pattern pOnDay = Pattern.compile("(\\s*)" + "(o|O)n\\s+(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day)" ); 
	public static Pattern pNextDay = Pattern.compile( "(\\s*)" +"(n|N)ext\\s+(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day)");
	

	/*
	 * Constructor
	 */
	public DateVerifier() {
		calendar = Calendar.getInstance();
		dateNow = LocalDate.now(); 
		
		}
	
	/*
	 * checks if string is in "DAY_OF_WEEK DATE_IN_MONTH MONTH"
	 */
	
	/*
	 * finds the pattern in a string
	 */
	public Matcher matcherWholeDate(String text) {
		return pWholeDate.matcher(text);
	}
	
	/*
	 * updates toString
	 */
	public void firstCheck(String input) {
		
		if (pWholeDate.matcher(input).matches()) {
			dayOfMonth = 0;
			String[] words = input.split("\\s+");
			for (int i = 0; i < words.length; i++) {
			    words[i] = words[i].replaceAll("[^\\w]", "");
			}
			day = words[0];
			month = words[2];
			String remainderAndOrderIndicator = words[1];
			
			String[] parts = {remainderAndOrderIndicator.substring(0, 1), remainderAndOrderIndicator.substring(1)};
			dayOfMonth = Integer.parseInt(parts[0]);
			orderIndicator = parts[1];
			
			if (!Character.isUpperCase(day.charAt(0))) {
				String capDay = day.substring(0, 1).toUpperCase() + day.substring(1);
				day = capDay;
			}
			
			if (!Character.isUpperCase(month.charAt(0))) {
				String capMonth = month.substring(0, 1).toUpperCase() + month.substring(1);
				month = capMonth;
			}
		}		
	}
	/*
	 * checks if string is in "DD/MM/YYYY"  format
	 */
	
	public Matcher matcherDate(String text) {
		return pDate.matcher(text);
	}
	
	/*
	 * updates toString 
	 */
	public void secondCheck(String input) {
		if (pDate.matcher(input).matches()) {
			dayOfMonth = 0;
			String[] parts = input.split("/");
			int part1 = Integer.parseInt(parts[0]);
			int part2 = Integer.parseInt(parts[1]);
			int part3 = Integer.parseInt(parts[2]);
			part2 = part2 -1;	//calendar.MONTH starts from 0
			dayOfMonth = part1;
			
			calendar.set(part3, part2, part1);;
			month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG,Locale.getDefault());		
			
			SimpleDateFormat formatter = new SimpleDateFormat("EEEE");
			String text = formatter.format(calendar.getTime());
			day = text;
			findOrderIndicator();
		}		
	}
	
	/*
	 * checks if string is in "on MONTH"  format
	 */
	
	public Matcher matcherDay(String text) {
		return pOnDay.matcher(text);
	}

	/*
	 * updates toString 
	 */
	public void thirdCheck(String input) {

		if (pOnDay.matcher(input).matches()) {
			
			dayOfMonth = 0;

			input = input.trim();
			StringBuilder tempInput = new StringBuilder(input);
			tempInput.delete(0, 3);
			input = tempInput.toString();
			day = input;

			findDifference(input); 
			
			Date futureDate = java.sql.Date.valueOf(dateNow.plusDays(dayOfMonth));
			calendar.setTime(futureDate);
		
			dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

			month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

			findOrderIndicator();
		}

	}

	
	public Matcher matcherNextDay(String text) {
		return pNextDay.matcher(text);
	}

	/*
	 * updates toString if input is in format "next MONTH"
	 */
	public void fourthCheck(String input) {
		if (pNextDay.matcher(input).matches()) {
			dayOfMonth = 0;
			input = input.trim();
			//removes next
			StringBuilder sp = new StringBuilder(input);
			sp.delete(0, 5);
			input = sp.toString();
			day = input;	
			
			findDifference(input);
			Date futureDate = java.sql.Date.valueOf(dateNow.plusDays(dayOfMonth + 7));
			calendar.setTime(futureDate);
			dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

			month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());

			findOrderIndicator();
		}

	}
	/*
	 * finds the difference between current day and day of future date
	 */
	public void findDifference(String testDay) {
		
		String capDay = day.substring(0, 1).toUpperCase() + day.substring(1);
		day = capDay;
		testDay = day;
		
		int startDay = 0;

		
		String tempCurrentDayofWeek = dateNow.getDayOfWeek().toString();
		String currentDayOfWeek = tempCurrentDayofWeek.substring(0,1) +  tempCurrentDayofWeek.substring(1,tempCurrentDayofWeek.length()).toLowerCase(); 
		
		if (currentDayOfWeek.equals("Sunday")) {
			startDay = 1;
		}
		else if (currentDayOfWeek.equals("Monday")) {
			startDay =2;
		}
		else if (currentDayOfWeek.equals("Tuesday")) {
			startDay =3;
		}
		else if (currentDayOfWeek.equals("Wednesday")) {
			startDay =4;
		}
		else if (currentDayOfWeek.equals("Thursday")) {
			startDay =5;
		}
		else if (currentDayOfWeek.equals("Friday")) {
			startDay =6;
		}
		else if (currentDayOfWeek.equals("Saturday")) {
			startDay =7;
		}
		
		
		int endDay = 0;
	
		String[] days = { "", "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday", "Sunday", "Monday",
				"Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		for (int i = startDay; i < 7 + startDay; i++) {
			if (testDay.equals(days[i])) {
				endDay = i;
				dayOfMonth = endDay - startDay;

			}
		}
	}

	/*
	 * finds the orderIndicatior based off dayOfMonth
	 */
	public void findOrderIndicator() {

		if (dayOfMonth == 1 || dayOfMonth == 21 || dayOfMonth == 31) {
			orderIndicator = "st";
		} else if (dayOfMonth == 2 || dayOfMonth == 22) {
			orderIndicator = "nd";
		} else if (dayOfMonth == 3 || dayOfMonth == 23) {
			orderIndicator = "rd";
		} else {
			orderIndicator = "th";
		}
	}
	

	/*
	 * Format "DAY_OF_WEEK, DATE_IN_MONTH, MONTH"
	 */
	public String toString() {
		
		if (day != null && month != null) {
			return day + " " + dayOfMonth + orderIndicator + " " + month;
		} else {
			return "-";
		}
	}
	
	
	
}
