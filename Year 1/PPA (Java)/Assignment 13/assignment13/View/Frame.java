package assignment13.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.nio.file.Paths;

import assignment13.Controller.Controller;
import assignment13.Model.Model;

public class Frame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L; // Creating fields
	private Model model;
	private Controller controller;

	private ImagePanel fox;
	private ImagePanel goose;
	private ImagePanel beans;
	private ImagePanel farmer;
	private ImagePanel boat;

	private JButton leftBoat;
	private JButton rightBoat;
	private JButton leftFarmer;
	private JButton rightFarmer;
	private JButton leftBeans;
	private JButton rightBeans;
	private JButton leftGoose;
	private JButton rightGoose;
	private JButton leftFox;
	private JButton rightFox;

	private ImagePanel ipRightGrass;
	private ImagePanel ipLeftGrass;
	private ImagePanel ipWater;

	/*
	 * names frame, sets border layout, creates panels, sets size
	 */
	public Frame(Model model, Controller controller) {

		super("Fox, Goose and Bag of Beans.");
		this.controller = controller;
		this.model = model;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		southPanel();
		eastPanel();
		westPanel();
		centerPanel();
		pack();
		setSize(1100, 800);
		setVisible(true);

	}

	public void centerPanel() {
		ipWater = new ImagePanel("./../images/water.png");
		add(ipWater, BorderLayout.CENTER);

		ipWater.setLayout(new BorderLayout());
		boat = new ImagePanel("./../images/boat.png");
		boat.setBoatLayout();
		boat.setPreferredSize(new Dimension(300, 200));
		boat.setOpaque(false);
		ipWater.add(boat, BorderLayout.EAST);

	}

	public void southPanel() {
		JPanel southPanel = new JPanel();
		add(southPanel, BorderLayout.SOUTH);
		southPanel.setLayout(new FlowLayout());

		leftBoat = new JButton("<");
		leftBoat.setName("leftBoat");
		rightBoat = new JButton(">");
		rightBoat.setName("rightBoat");
		leftFox = new JButton("<");
		leftFox.setName("leftFox");
		rightFox = new JButton(">");
		rightFox.setName("rightFox");
		leftGoose = new JButton("<");
		leftGoose.setName("leftGoose");
		rightGoose = new JButton(">");
		rightGoose.setName("rightGoose");
		leftBeans = new JButton("<");
		leftBeans.setName("leftBeans");
		rightBeans = new JButton(">");
		rightBeans.setName("rightBeans");
		leftFarmer = new JButton("<");
		leftFarmer.setName("leftFarmer");
		rightFarmer = new JButton(">");
		rightFarmer.setName("rightFarmer");

		leftFarmer.addActionListener(controller);
		rightFarmer.addActionListener(controller);
		leftFox.addActionListener(controller);
		rightFox.addActionListener(controller);
		leftGoose.addActionListener(controller);
		rightGoose.addActionListener(controller);
		leftBeans.addActionListener(controller);
		rightBeans.addActionListener(controller);
		leftBoat.addActionListener(controller);
		rightBoat.addActionListener(controller);

		southPanel.add(new JLabel("Boat:"));
		southPanel.add(leftBoat);
		southPanel.add(rightBoat);
		southPanel.add(new JLabel("Fox:"));
		southPanel.add(leftFox);
		southPanel.add(rightFox);
		southPanel.add(new JLabel("Goose:"));
		southPanel.add(leftGoose);
		southPanel.add(rightGoose);
		southPanel.add(new JLabel("Beans:"));
		southPanel.add(leftBeans);
		southPanel.add(rightBeans);
		southPanel.add(new JLabel("Farmer:"));
		southPanel.add(leftFarmer);
		southPanel.add(rightFarmer);
	}

	/*
	 * Creates fox, goose, beans and farmer
	 */
	public void eastPanel() {
		ipRightGrass = new ImagePanel("./../images/grass.png");
		ipRightGrass.setPreferredSize(new Dimension(200, 800));
		add(ipRightGrass, BorderLayout.EAST);
		ipRightGrass.setLayout(new GridLayout(4, 1));

		fox = new ImagePanel("./../images/fox.png");
		fox.setPreferredSize(new Dimension(50, 200));
		fox.setOpaque(false);
		ipRightGrass.add(fox);

		goose = new ImagePanel("../images/goose.png");
		fox.setPreferredSize(new Dimension(50, 200));
		goose.setOpaque(false);
		ipRightGrass.add(goose);

		beans = new ImagePanel("../images/beans.png");
		beans.setPreferredSize(new Dimension(50, 200));
		beans.setOpaque(false);
		ipRightGrass.add(beans);

		farmer = new ImagePanel("../images/farmer.png");
		farmer.setPreferredSize(new Dimension(50, 250));
		farmer.setOpaque(false);
		ipRightGrass.add(farmer);

	}

	public void westPanel() {
		ipLeftGrass = new ImagePanel("../images/grass.png");
		ipLeftGrass.setPreferredSize(new Dimension(200, 800));
		ipLeftGrass.setOpaque(false);
		add(ipLeftGrass, BorderLayout.WEST);
		ipLeftGrass.setLayout(new GridLayout(4, 1));
	}

	/*
	 * updates the GUI using the image positions from the model
	 */
	public void update(Observable o, Object arg) {
		int score = model.getScore();
		setTitle("score:" + score);

		if (model.getFoxPosition().equals("left")) {
			ipLeftGrass.add(fox);
			revalidate();
			repaint();
		}
		if (model.getFoxPosition().equals("middle")) {
			boat.add(fox);
			revalidate();
			repaint();
		}
		if (model.getFoxPosition().equals("right")) {
			ipRightGrass.add(fox);
			revalidate();
			repaint();
		}

		if (model.getGoosePosition().equals("left")) {
			ipLeftGrass.add(goose);
			revalidate();
			repaint();
		}
		if (model.getGoosePosition().equals("middle")) {
			boat.add(goose);
			revalidate();
			repaint();
		}
		if (model.getGoosePosition().equals("right")) {
			ipRightGrass.add(goose);
			revalidate();
			repaint();
		}

		if (model.getBeansPosition().equals("left")) {
			ipLeftGrass.add(beans);
			revalidate();
			repaint();
		}
		if (model.getBeansPosition().equals("middle")) {
			boat.add(beans);
			revalidate();
			repaint();
		}
		if (model.getBeansPosition().equals("right")) {
			ipRightGrass.add(beans);
			revalidate();
			repaint();
		}

		if (model.getFarmerPosition().equals("left")) {
			ipLeftGrass.add(farmer);
			revalidate();
			repaint();
		}
		if (model.getFarmerPosition().equals("middle")) {
			boat.add(farmer);
			revalidate();
			repaint();
		}
		if (model.getFarmerPosition().equals("right")) {
			ipRightGrass.add(farmer);
			revalidate();
			repaint();
		}

		if (model.getBoatPosition().equals("west")) {
			ipWater.add(boat, BorderLayout.WEST);
			revalidate();
			repaint();
		}
		if (model.getBoatPosition().equals("east")) {
			ipWater.add(boat, BorderLayout.EAST);
			revalidate();
			repaint();
		}
		if (model.checkFoxAndGoose()) {
			setTitle("Fox has eaten the goose!");
		}
		if (model.checkGooseAndBeans()) {
			setTitle("Goose has eaten the beans!");
		}
		if (model.checkWin()) {
			setTitle("You have won! Score: " + model.getScore());
		}

	}

}
