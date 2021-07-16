package assignment14;

import java.io.IOException;

import assignment14.Controller.CalendarListener;
import assignment14.Controller.ReminderListener;
import assignment14.Model.Writer;
import assignment14.View.Frame;

public class Main {
	
	public static void main (String [] args) throws IOException {
		Frame frame = new Frame();
		Writer calendarWriter = new Writer("../Storage/Calendar.txt");
		@SuppressWarnings("unused")
		CalendarListener cl = new CalendarListener(frame, calendarWriter);
		Writer reminderWriter = new Writer("../Storage/Reminder.txt");
		@SuppressWarnings("unused")
		ReminderListener rl = new ReminderListener(frame,reminderWriter);
		
		
		
	}
	
}
