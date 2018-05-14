package main;


import java.awt.EventQueue;

import model.Language;
import vue.MultiTool;
import vue.menu.*;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Language.loadLanguages();

					// 
					MultiTool frame = MultiTool.getInstance();
					frame.setVisible(true);

					frame.addToolMenu(new LoanCalculator());
					frame.addToolMenu(new BMI());

					Todo todo = new Todo();
					todo.addLine("Me vanter à tout le monde que je suis le meilleur.").setChecked(true);
					todo.addLine("Terminer mon générateur de trou noir.");
					todo.addLine("Dominer le monde.");
					todo.addLine("Présenter mon projet au prof.").setChecked(true);;

					frame.addToolMenu(todo);
					frame.addToolMenu(new TempDiagnostic());
					frame.addToolMenu(new CommissionCalculator());
					frame.addToolMenu(new WhatToWear());
					frame.addToolMenu(new BlackHoleGenerator());
					frame.addToolMenu(new LuaEditor());
					
					frame.changeLanguage(Language.getLanguage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
