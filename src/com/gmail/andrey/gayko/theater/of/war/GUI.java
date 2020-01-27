package com.gmail.andrey.gayko.theater.of.war;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.andrey.gayko.theater.of.war.heroes.Dwarf;
import com.gmail.andrey.gayko.theater.of.war.heroes.Elf;
import com.gmail.andrey.gayko.theater.of.war.heroes.Hero;
import com.gmail.andrey.gayko.theater.of.war.heroes.OldMage;

import javafx.scene.image.Image;

public class GUI extends JFrame implements ActionListener, Runnable {

	Logger logger = LogManager.getLogger(GUI.class);
	BattleLogger battleLogger;
	private JButton startButton;
	private JButton elfButton;
	private JButton dwarfButton;
	private JButton oldMageButton;
	private JButton comingSoon;
	private JLabel label;
	private JLabel heroPic1;
	private JLabel heroPic2;
	private JTextArea logs;
	private JScrollPane scroll;
	private int numOfHeroes;
	Hero hero1;
	Hero hero2;

	public GUI(BattleLogger battleLogger) {
		this.battleLogger = battleLogger;

		startButton = new JButton("Start");
		label = new JLabel("Welcome to Theater of War");
		elfButton = new JButton("Elf");
		dwarfButton = new JButton("Dwarf");
		oldMageButton = new JButton("Old mage");
		comingSoon = new JButton("Soon...");
		heroPic1 = new JLabel();
		heroPic2 = new JLabel();
		logs = new JTextArea();
		scroll = new JScrollPane(logs, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scroll.setBounds(240, 150, 200, 350);
		startButton.setBounds(300, 110, 70, 20);
		label.setBounds(250, 20, 200, 20);
		heroPic1.setBounds(20, 100, 270, 370);
		heroPic2.setBounds(460, 100, 270, 370);
		Dimension buttonSize = new Dimension(90, 20);
		elfButton.setBounds(140, 60, buttonSize.width, buttonSize.height);
		dwarfButton.setBounds(240, 60, buttonSize.width, buttonSize.height);
		oldMageButton.setBounds(340, 60, buttonSize.width, buttonSize.height);
		comingSoon.setBounds(440, 60, buttonSize.width, buttonSize.height);

		startButton.addActionListener(this);
		elfButton.addActionListener(this);
		dwarfButton.addActionListener(this);
		oldMageButton.addActionListener(this);
		scroll.setViewportView(logs);

		add(startButton);
		add(label);
		add(elfButton);
		add(dwarfButton);
		add(oldMageButton);
		add(comingSoon);
		add(heroPic1);
		add(heroPic2);
		add(scroll);

		setLayout(null);
		setSize(700, 540);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Start") && numOfHeroes == 2) {
			Action action = new Action(battleLogger, hero1, hero2);
			new Thread(action).start();
			battleLogger.write(hero1.getName() + " vs " + hero2.getName());
			numOfHeroes = 0;
		}
		if (e.getActionCommand().equals("Elf")) {
			if (numOfHeroes == 1) {
				hero2 = new Elf();
				heroPic2.setIcon(new ImageIcon("resources/images/little elf.jpg"));
				numOfHeroes++;
			}
			if (numOfHeroes == 0) {
				hero1 = new Elf();
				heroPic1.setIcon(new ImageIcon("resources/images/little elf.jpg"));
				numOfHeroes++;
			}
		}
		if (e.getActionCommand().equals("Dwarf")) {
			if (numOfHeroes == 1) {
				hero2 = new Dwarf();
				heroPic2.setIcon(new ImageIcon("resources/images/little dwarf.jpg"));
				numOfHeroes++;
			}
			if (numOfHeroes == 0) {
				hero1 = new Dwarf();
				heroPic1.setIcon(new ImageIcon("resources/images/little dwarf.jpg"));
				numOfHeroes++;
			}
		}
		if (e.getActionCommand().equals("Old mage")) {
			if (numOfHeroes == 1) {
				hero2 = new OldMage();
				heroPic2.setIcon(new ImageIcon("resources/images/little old mage.jpg"));
				numOfHeroes++;
			}
			if (numOfHeroes == 0) {
				hero1 = new OldMage();
				heroPic1.setIcon(new ImageIcon("resources/images/little old mage.jpg"));
				numOfHeroes++;
			}
		}
	}

	@Override
	public synchronized void run() {
		System.out.println("Start run");
		while (!battleLogger.getLastWord().equals("End")) {
			logs.append(battleLogger.read() + "\r\n");
		}
	}
}
