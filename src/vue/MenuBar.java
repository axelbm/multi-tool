package vue;

import model.Language;
import model.Language.Dictionary;
import model.Option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class MenuBar extends JMenuBar {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private JMenu mnLanguage, mnFichier, mnditer, mnUnit, mnDistance, mnWeight, mnTemperature;
	private ButtonGroup languageOption;
	private JMenuItem mntmUndo;
	private JRadioButton rdbtnMeter, rdbtnFoot, rdbtnKilogram, rdbtnPound, rdbtnCelsius, rdbtnFahrenheit, rdbtnKelvin;


	public MenuBar() {
		build();

		buildLanguageMenu();
	}

	private void build() {
		mnFichier = new JMenu();
		add(mnFichier);

		mnditer = new JMenu();
		add(mnditer);

		mntmUndo = new JMenuItem();
		mnditer.add(mntmUndo);
		mntmUndo.setIcon(new ImageIcon(MultiTool.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));

		mnUnit = new JMenu();
		add(mnUnit);

		mnDistance = new JMenu();
		mnUnit.add(mnDistance);
		ButtonGroup distOption = new ButtonGroup();

		rdbtnMeter = new JRadioButton();
		mnDistance.add(rdbtnMeter);

		distOption.add(rdbtnMeter);

		rdbtnFoot = new JRadioButton();
		mnDistance.add(rdbtnFoot);
		distOption.add(rdbtnFoot);

		mnWeight = new JMenu();
		mnUnit.add(mnWeight);
		ButtonGroup weightOption = new ButtonGroup();

		rdbtnKilogram = new JRadioButton();
		mnWeight.add(rdbtnKilogram);
		weightOption.add(rdbtnKilogram);

		rdbtnPound = new JRadioButton();
		mnWeight.add(rdbtnPound);
		weightOption.add(rdbtnPound);

		mnTemperature = new JMenu();
		mnUnit.add(mnTemperature);
		ButtonGroup tempOption = new ButtonGroup();

		rdbtnCelsius = new JRadioButton();
		mnTemperature.add(rdbtnCelsius);
		tempOption.add(rdbtnCelsius);

		rdbtnFahrenheit = new JRadioButton();
		mnTemperature.add(rdbtnFahrenheit);
		tempOption.add(rdbtnFahrenheit);

		rdbtnKelvin = new JRadioButton();
		mnTemperature.add(rdbtnKelvin);
		tempOption.add(rdbtnKelvin);

		mnLanguage = new JMenu();
		add(mnLanguage);
		languageOption = new ButtonGroup();
	}

	public void setLanguage(Dictionary dict) {

		mnFichier.setText(dict.get("file"));
		mnditer.setText(dict.get("edit"));
		mntmUndo.setText(dict.get("undo"));
		mnUnit.setText(dict.get("unit"));
		mnDistance.setText(dict.get("distance"));
		rdbtnMeter.setText(dict.get("meter"));
		rdbtnFoot.setText(dict.get("foot"));
		mnWeight.setText(dict.get("weight"));
		rdbtnKilogram.setText(dict.get("kilogram"));
		rdbtnPound.setText(dict.get("pound"));
		mnTemperature.setText(dict.get("temperature"));
		rdbtnCelsius.setText(dict.get("celsius"));
		rdbtnFahrenheit.setText(dict.get("fahrenheit"));
		rdbtnKelvin.setText(dict.get("kelvin"));
		mnLanguage.setText(dict.get("language"));
		
		
	}

	private void buildLanguageMenu() {
		Map<String, Language> languages = Language.getLanguages();

		languages.forEach((String key, Language lang) -> {
			JRadioButton rdbtnLang = new JRadioButton(lang.getName());
			languageOption.add(rdbtnLang);
			mnLanguage.add(rdbtnLang);

			rdbtnLang.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Option.changeLanguage(lang);
				}
			});
			
			if (lang.getID().equals(Option.getLanguage())) {
				rdbtnLang.setSelected(true);
			}
		});
	}
}
