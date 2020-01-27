package com.gmail.andrey.gayko.theater.of.war;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BattleLogger {

	BufferedWriter writer;
	BufferedReader reader;
	int newLine = 0;
	String lastWord = "Start";

	public BattleLogger() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Battle Logs.txt", true));
		reader = new BufferedReader(new FileReader("Battle Logs.txt"));
	}

	public synchronized void write(String str) {
		while (newLine != 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("Battle Logs.txt", true))){
			writer.write(str + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		newLine++;
		notify();
	}

	public synchronized String read() {
		while (newLine == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		try {
			lastWord = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		newLine--;
		notify();
		return lastWord;
	}

	public String getLastWord() {
		return lastWord;
	}
}
