package vue.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

public class WhatToWear extends Menu {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Dictionary dictionary;
	
	private JTextField tfTemp, tfWear;
	private JSlider sdTemp;
	private JLabel lbTitle, lbTemp;

	/**
	 * Create the panel.
	 */
	public WhatToWear() {
		setLayout(new BorderLayout(0, 0));

		JPanel container = new JPanel();
		add(container, BorderLayout.CENTER);
		GridBagLayout gbl_container = new GridBagLayout();
		gbl_container.columnWidths = new int[]{0, 0};
		gbl_container.rowHeights = new int[]{0, 0, 0};
		gbl_container.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_container.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		container.setLayout(gbl_container);

		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.anchor = GridBagConstraints.WEST;
		gbc_lbTitle.gridx = 0;
		gbc_lbTitle.gridy = 0;
		container.add(lbTitle, gbc_lbTitle);

		JPanel form = new JPanel();
		GridBagConstraints gbc_form = new GridBagConstraints();
		gbc_form.fill = GridBagConstraints.BOTH;
		gbc_form.gridx = 0;
		gbc_form.gridy = 1;
		container.add(form, gbc_form);
		GridBagLayout gbl_form = new GridBagLayout();
		gbl_form.columnWidths = new int[]{137, 0, 0};
		gbl_form.rowHeights = new int[]{0, 0, 0, 0};
		gbl_form.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_form.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		form.setLayout(gbl_form);

		lbTemp = new JLabel();
		lbTemp.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTemp = new GridBagConstraints();
		gbc_lbTemp.insets = new Insets(0, 0, 5, 5);
		gbc_lbTemp.anchor = GridBagConstraints.EAST;
		gbc_lbTemp.gridx = 0;
		gbc_lbTemp.gridy = 0;
		form.add(lbTemp, gbc_lbTemp);

		JPanel divTemp = new JPanel();
		GridBagConstraints gbc_divTemp = new GridBagConstraints();
		gbc_divTemp.insets = new Insets(0, 0, 5, 0);
		gbc_divTemp.fill = GridBagConstraints.BOTH;
		gbc_divTemp.gridx = 1;
		gbc_divTemp.gridy = 0;
		form.add(divTemp, gbc_divTemp);
		GridBagLayout gbl_divTemp = new GridBagLayout();
		gbl_divTemp.columnWidths = new int[]{0, 0, 0};
		gbl_divTemp.rowHeights = new int[]{0, 0};
		gbl_divTemp.columnWeights = new double[]{1.0, 0.25, Double.MIN_VALUE};
		gbl_divTemp.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		divTemp.setLayout(gbl_divTemp);

		sdTemp = new JSlider();
		sdTemp.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				calculate();
			}
		});
		GridBagConstraints gbc_sdTemp = new GridBagConstraints();
		gbc_sdTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_sdTemp.insets = new Insets(0, 0, 0, 5);
		gbc_sdTemp.gridx = 0;
		gbc_sdTemp.gridy = 0;
		divTemp.add(sdTemp, gbc_sdTemp);

		tfTemp = new JTextField();
		tfTemp.setText("0.0 °C");
		tfTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		tfTemp.setEditable(false);
		tfTemp.setFont(Option.font("text"));
		GridBagConstraints gbc_tfTemp = new GridBagConstraints();
		gbc_tfTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTemp.gridx = 1;
		gbc_tfTemp.gridy = 0;
		divTemp.add(tfTemp, gbc_tfTemp);
		tfTemp.setColumns(10);

		tfWear = new JTextField();
		tfWear.setEditable(false);
		tfWear.setFont(Option.font("text"));
		GridBagConstraints gbc_tfWear = new GridBagConstraints();
		gbc_tfWear.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfWear.gridx = 1;
		gbc_tfWear.gridy = 2;
		form.add(tfWear, gbc_tfWear);
		tfWear.setColumns(10);
	}

	protected void calculate() {
		float temp = (float) sdTemp.getValue()/100*60 - 20;
		String text;

		tfTemp.setText(String.format("%.1f °C", temp));

		if (temp < -10)
			text = dictionary.get("result.1");	//"Attache ta tuque!";
		else if (temp < 5)
			text = dictionary.get("result.2");	//"Manteau d'hiver";
		else if (temp < 15)
			text = dictionary.get("result.3");	//"Manteau de printemps";
		else if (temp < 20)
			text = dictionary.get("result.4");	//"V�tement longs";
		else if (temp < 30)
			text = dictionary.get("result.5");	//"V�tement l�gers";
		else
			text = dictionary.get("result.6");	//"Maillot de bain";

		tfWear.setText(text);
	}


	public void setLanguage(Dictionary dict) {
		dictionary = dict;
		
		lbTitle.setText(dict.get("title"));	//"Quoi porter aujourd'hui?"
		lbTemp.setText(dict.get("temperature"));	//"Temperature"

		calculate();
	}

}
