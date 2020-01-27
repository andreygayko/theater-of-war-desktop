package com.gmail.andrey.gayko.theater.of.war;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gmail.andrey.gayko.theater.of.war.heroes.Hero;

public class BattleMechanics implements Runnable {

	BattleLogger battleLogger;
	Hero hero1;
	Hero hero2;
	private int damage;
	static final Logger logger = LogManager.getLogger(BattleMechanics.class);

	public BattleMechanics(BattleLogger battleLogger, Hero hero1, Hero hero2) {
		this.battleLogger = battleLogger;
		this.hero1 = hero1;
		this.hero2 = hero2;
	}

	@Override
	public void run() {
		if (new Random().nextInt(100) < 30) {
			if (hero2.isBlock()) {
				damage = (hero1.getAttack() - hero2.getDefense()) / 3;
				battleLogger.write(hero2.getName() + " blocks 60% damage");
			}
			if (hero2.isDodge()) {
				damage = 0;
				battleLogger.write(hero2.getName() + " dodged");
			}
		} else {
			if (new Random().nextInt(100) < 30) {
				damage = hero1.getAttack() + hero1.getAttack() / 2 - hero2.getDefense();
				battleLogger.write(hero1.getName() + " crits");
			} else
				damage = hero1.getAttack() - hero2.getDefense();
		}

		hero2.setHealth(hero2.getHealth() - damage);
		battleLogger.write(hero1.getName() + " makes " + damage + " damage");
		battleLogger.write("After round " + hero2.getName() + " has " + hero2.getHealth() + " hp");
	}
}