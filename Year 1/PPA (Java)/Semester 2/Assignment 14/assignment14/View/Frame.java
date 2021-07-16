package assignment14.View;

import java.awt.BorderLayout;    
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField calendarTextField;
	private DefaultListModel<String> dlmCalendar;
	private JList<String> calendarList;
	
	private JTextField reminderTextField;
	private DefaultListModel<String> dlmReminder;
	private JList<String> reminderList;
	
	/*
	 * Creates layout and widgets of GUI
	 */
	public Frame() throws IOException {
		setSize(800, 400);
		JTabbedPane tabs = new JTabbedPane();

		// calendar tab
		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new BorderLayout());
		dlmCalendar = new DefaultListModel<String>();
		calendarList = new JList<String>(dlmCalendar);
		
		calendarList.setPreferredSize(new Dimension(800, 300));

		JPanel calendarPanel2 = new JPanel();
		calendarPanel2.setSize(800, 300);
		calendarPanel2.setLayout(new GridLayout(1, 1));
		calendarTextField = new JTextField();

		calendarPanel.add(calendarList, BorderLayout.NORTH);
		calendarPanel.add(calendarPanel2, BorderLayout.SOUTH);
		calendarPanel2.add(calendarTextField);
		ImageIcon calendarIcon = new ImageIcon("../images/calendar.png");
		tabs.addTab("Calendar", calendarIcon, calendarPanel);

		// reminder tab

		JPanel reminderPanel = new JPanel();
		reminderPanel.setLayout(new BorderLayout());
		dlmReminder = new DefaultListModel<String>();
		reminderList = new JList<String>(dlmReminder);
		reminderList.setPreferredSize(new Dimension(800, 300));

		JPanel reminderPanel2 = new JPanel();
		reminderPanel2.setSize(800, 300);
		reminderPanel2.setLayout(new GridLayout(1, 1));
		reminderTextField = new JTextField();

		reminderPanel.add(reminderList, BorderLayout.NORTH);
		reminderPanel2.add(reminderTextField, BorderLayout.SOUTH);
		reminderPanel.add(reminderPanel2, BorderLayout.SOUTH);
		ImageIcon reminderIcon = new ImageIcon("../images/reminder.png");
		tabs.addTab("Reminders", reminderIcon, reminderPanel);
		add(tabs);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	
	public String getCalendarText() {
		return calendarTextField.getText();
	}
	
	public void setCalendarText(String info) {
		calendarTextField.setText(info);
	}

	public void addTodlmCalendar(String info) {
		dlmCalendar.addElement(info);
	}
	
	public String getCalendarListValue() {
		return calendarList.getSelectedValue();
	}

	public void removeFromdlmCalendar(String line) {
		dlmCalendar.removeElement(line);
	}
	
	public String getReminderText() {
		return reminderTextField.getText();
	}
	
	public void setReminderText(String info) {
		reminderTextField.setText(info);
	}
	
	public void addTodlmReminder(String info) {
		dlmReminder.addElement(info);
	}
	
	public String getReminderListValue() {
		return reminderList.getSelectedValue();
	}
	
	public void removeFromdlmReminder(String line) {
		dlmReminder.removeElement(line);
	}
	
	/*
	 * adder for calendar listener
	 */
	public void addCalendarListener(KeyListener listener) {
		calendarTextField.addKeyListener(listener);
		if (listener instanceof MouseListener) {
			calendarList.addMouseListener((MouseListener) listener);
		}
	}
	/*
	 * adder for reminder listener
	 */
	public void addReminderListener(KeyListener listener) {
		reminderTextField.addKeyListener(listener);
		if (listener instanceof MouseListener) {
			reminderList.addMouseListener((MouseListener) listener);
		}
	}	
}
