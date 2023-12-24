package com.br.piterpg.sistemapg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class MainPG {
	public static void main(String[] args) {
		System.out.println("         _ _                        \n" +
				"       (_) |                       \n" +
				"  _ __  _| |_ ___ _ __ _ __   __ _ \n" +
				" |  _ \\| | __/ _ \\  __|  _ \\ / _  |\n" +
				" | |_) | | ||  __/ |  | |_) | (_| |\n" +
				" |  __/|_|\\__\\___|_|  |  __/ \\__  |\n" +
				" | |                  | |     __/ |\n" +
				" |_|                  |_|    |___/ ");
		System.out.println("\n\n\n\n\n");
		SpringApplication.run(MainPG.class, args);
	}

}
