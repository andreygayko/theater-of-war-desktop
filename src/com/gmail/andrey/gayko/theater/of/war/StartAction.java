package com.gmail.andrey.gayko.theater.of.war;

import java.io.File;
import java.io.IOException;
import com.gmail.andrey.gayko.theater.of.war.BattleLogger;
import com.gmail.andrey.gayko.theater.of.war.GUI;

public class StartAction {

	public static void main(String[] args) throws IOException {

		new File("Battle Logs.txt").delete();
		BattleLogger battleLogger = new BattleLogger();
		GUI gui = new GUI(battleLogger);
		new Thread(gui).start();
		
	}
}
