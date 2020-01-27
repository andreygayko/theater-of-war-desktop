package com.gmail.andrey.gayko.theater.of.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.andrey.gayko.theater.of.war.heroes.Hero;

public class Action implements Runnable {

	BattleLogger battleLogger;
	Hero hero1;
	Hero hero2;
	static final Logger logger = LogManager.getLogger(Action.class);
	private int round = 1;

	public Action(BattleLogger battleLogger, Hero hero1, Hero hero2) {
		this.battleLogger = battleLogger;
		this.hero1 = hero1;
		this.hero2 = hero2;
	}

	@Override
	public void run() {

		battleLogger.write("Start");
		while ((hero1.getHealth() > 0) && (hero2.getHealth() > 0)) {

			battleLogger.write("Round " + round);
			BattleMechanics battleMechanics1 = new BattleMechanics(battleLogger, hero1, hero2);
			Thread bm1 = new Thread(battleMechanics1);
			bm1.start();
			try {
				bm1.join();
			} catch (InterruptedException e) {
			}
			BattleMechanics battleMechanics2 = new BattleMechanics(battleLogger, hero2, hero1);
			Thread bm2 = new Thread(battleMechanics2);
			bm2.start();
			try {
				bm2.join();
			} catch (InterruptedException e) {
			}
			round++;
		}
		if (hero1.getHealth() <= 0 && hero2.getHealth() <= 0) {
			battleLogger.write("We have two bodies!");
			System.out.println("We have two bodies!");
		} else if (hero1.getHealth() <= 0) {
			battleLogger.write(hero2.getName() + " wins!");
			System.out.println(hero2.getName() + " wins!");
		} else if (hero2.getHealth() <= 0) {
			battleLogger.write(hero1.getName() + " wins!");
			System.out.println(hero1.getName() + " wins!");
		}
	}
}
