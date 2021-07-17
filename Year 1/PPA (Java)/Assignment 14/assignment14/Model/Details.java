package assignment14.Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Details {
	private String input;
	private String event;
	private String reminder;
	private String date;
	private String time;
	private String location;
	
	private static Pattern pTime = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9](\\z|\\s+)");
	private static Pattern pLocation = Pattern.compile("(\\A|\\s+)(a|A)t" + "\\s+[a-zA-Z]*" + "(\\z|\\s+)");
	private static Pattern pEveningorMorning = Pattern.compile("evening|morning");
	private static Pattern pAmorPm = Pattern.compile("[01]?[0-9]((am|Am)|(pm|Pm))");
	
	/*
	 * Converts input to desired input
	 */
	public Details(String input) {
		this.input = input;
		location = "-";
		date = "-";
		time = "-";
		event = "-";
		parseLocation();
		parseDate();
		parseTime();
		parseEvent();
		parseReminder();
		
	}
	/*
	 * parses input into location
	 */
	public void parseLocation() {
		
		Matcher Location = pLocation.matcher(input);
		if (Location.find()) {
			location = Location.group();
			location = location.replaceAll("at\\s", "");
			input = input.replaceAll("(\\A|\\s+)(a|A)t" + "\\s+[a-zA-Z]*" + "(\\z|\\s+)", "");
			input = input.trim();
		}
	}
	/*
	 * parses input into time
	 */
	public void parseTime() {
		Matcher Time = pTime.matcher(input);
		if (Time.find()) {
			time = Time.group();
			input = input.replaceAll("([01]?[0-9]|2[0-3]):[0-5][0-9](\\z|\\s+)", "");
			input = input.replaceAll("at", "");
			input = input.trim();
		}
		Matcher morningorevening = pEveningorMorning.matcher(input);
		if (morningorevening.find()) {
			String tempString = morningorevening.group();
			input = input.replaceAll("in\\sthe\\s", "");
			if (tempString.equals("morning")) {
				input = input.replaceAll("evening|morning", "");
				time = "09:00";
			}
			if (tempString.equals("evening")) {
				input = input.replaceAll("evening|morning", "");
				time = "20:00";
			}
			
		}
		Matcher pmOram = pAmorPm.matcher(input);
		if (pmOram.find()) {
			String tempString = pmOram.group();

			if (tempString.length() == 3) {
				tempString = "0" + tempString;
			}
			if (tempString.contains("am")) {
				tempString = tempString.replaceAll ("am", ":00");
				time = tempString;
			}
			if (tempString.contains("pm")) {
				
				String temp2String = tempString.replaceAll("([a-z]|[A-Z])*", "");
				int num = Integer.parseInt(temp2String);
				num = num+12;
				tempString = num + ":00";
				time = tempString;
				
			}
			
			input = input.replaceAll("at\\s[01]?[0-9]((am|Am)|(pm|Pm))", "");
		}
		
	}
	/*
	 * parses input into date
	 */
	public void parseDate() {
		DateVerifier testDate = new DateVerifier();
		Matcher wholeDate = testDate.matcherWholeDate(input);
		Matcher Date = testDate.matcherDate(input);
		Matcher day = testDate.matcherDay(input);
		Matcher nextDay = testDate.matcherNextDay(input);
		
		if (wholeDate.find()) {
			
			String tempString = wholeDate.group();
			tempString = tempString.trim();
			testDate.firstCheck(tempString);
			input = input.trim();
			input = input.replaceAll("((\\A|\\s+)"+ "(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day))"
					+ ("\\s+(\\d?\\d)")+ ("(st|nd|rd|th)") + "(\\s+)([Jj]anuary|[fF]ebruary|[mM]arch|[aA]pril|[mM]ay|[jJ]une|[jJ]uly|[aA]ugust|[sS]eptember|[nN]ovember|[dD]ecember)", " ");
		}
		
		if (Date.find()) {
			String tempString = Date.group();
			tempString = tempString.trim();
			testDate.secondCheck(tempString);
			input = input.trim();
			input = input.replaceAll("(\\s*)"+"(\\d\\d(-|/)\\d\\d(-|/)\\d\\d\\d\\d)", "");
		}
		if (day.find()) {
			String tempString = day.group();
			tempString = tempString.trim();
			testDate.thirdCheck(tempString);
			input= input.trim();
			input = input.replaceAll("[Oo]n" + "(\\s*)" + "(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day)", "");
		}
		if (nextDay.find()) {
			String tempString = nextDay.group();
			tempString = tempString.trim();
			testDate.fourthCheck(tempString);
			input = input.trim();
			
			input = input.replaceAll("(\\s*)" +"(n|N)ext\\s+(([Mm]on|[Tt]ues|[Ww]ednes|[Tt]hurs|[Ff]ri|[Ss]atur|[Ss]un)day)", "");
		}
		date = testDate.toString();
		
	}
	/*
	 * parses input into event
	 */
	public void parseEvent() {
		event = input;	
	}
	/*
	 * parses input into reminder
	 */
	public void parseReminder() {	
		reminder = input;
	}
	/*
	 * Calendar String
	 */
	public String tostring() {
		event = event.trim();
		date = date.trim();
		time = time.trim();
		location = location.trim();
		if (event.equals("")) {
			event = "-";
		}
		return "Event: " + event + " | Date: " + date + " | Time:" + time + " | Location: " + location;
	}
	/*
	 * reminder string
	 */
	public String remainderString() {
		
		reminder = reminder.trim();
		date = date.trim();
		time = time.trim();
		location = location.trim();
		
		if (reminder.equals("")) {
			reminder = "-";
		}
		
		if (date != "-" || time != "-" || location != "-" ) {
			return "Reminder: " + reminder + " | Date: " + date + " | Time:" + time + " | Location: " + location;
		}	
		return  reminder;
	}
}
