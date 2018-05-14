package vue.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BMI extends Menu {
	/**
	 *
	 */

	private static final long serialVersionUID = 1L;

	private Dictionary dictionary;
	
	private JTextField tfPoids, tfTaille, tfIMC;
	private JLabel lbTip, lbTitle, lbPoids, lbTaille, lbIMC;
	private JRadioButton rdIMC, rdTaille, rdPoids;
	private JButton btCalculer;

	private final ButtonGroup calcModGroup = new ButtonGroup();
	private int calcMod = 1;

	/**
	 * Create the panel.
	 */
	public BMI() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.gridx = 0;
		gbc_lbTitle.gridy = 0;
		panel.add(lbTitle, gbc_lbTitle);

		JPanel tfForm = new JPanel();
		GridBagConstraints gbc_tfForm = new GridBagConstraints();
		gbc_tfForm.insets = new Insets(0, 0, 5, 0);
		gbc_tfForm.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfForm.gridx = 0;
		gbc_tfForm.gridy = 1;
		panel.add(tfForm, gbc_tfForm);
		GridBagLayout gbl_tfForm = new GridBagLayout();
		gbl_tfForm.columnWidths = new int[]{83, 0, 0};
		gbl_tfForm.rowHeights = new int[]{0, 0, 23, 0, 0};
		gbl_tfForm.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_tfForm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		tfForm.setLayout(gbl_tfForm);

		lbPoids = new JLabel();
		GridBagConstraints gbc_lbPoids = new GridBagConstraints();
		gbc_lbPoids.anchor = GridBagConstraints.EAST;
		gbc_lbPoids.insets = new Insets(0, 0, 5, 5);
		gbc_lbPoids.gridx = 0;
		gbc_lbPoids.gridy = 0;
		tfForm.add(lbPoids, gbc_lbPoids);
		lbPoids.setFont(Option.font("text"));

		tfPoids = new JTextField();
		tfPoids.setFont(Option.font("text"));
		GridBagConstraints gbc_tfPoids = new GridBagConstraints();
		gbc_tfPoids.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPoids.insets = new Insets(0, 0, 5, 0);
		gbc_tfPoids.gridx = 1;
		gbc_tfPoids.gridy = 0;
		tfForm.add(tfPoids, gbc_tfPoids);
		tfPoids.setColumns(10);

		lbTaille = new JLabel();
		GridBagConstraints gbc_lbTaille = new GridBagConstraints();
		gbc_lbTaille.anchor = GridBagConstraints.EAST;
		gbc_lbTaille.insets = new Insets(0, 0, 5, 5);
		gbc_lbTaille.gridx = 0;
		gbc_lbTaille.gridy = 1;
		tfForm.add(lbTaille, gbc_lbTaille);
		lbTaille.setFont(Option.font("text"));

		tfTaille = new JTextField();
		tfTaille.setFont(Option.font("text"));
		GridBagConstraints gbc_tfTaille = new GridBagConstraints();
		gbc_tfTaille.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTaille.insets = new Insets(0, 0, 5, 0);
		gbc_tfTaille.gridx = 1;
		gbc_tfTaille.gridy = 1;
		tfForm.add(tfTaille, gbc_tfTaille);
		tfTaille.setColumns(10);

		lbIMC = new JLabel();
		GridBagConstraints gbc_lbIMC = new GridBagConstraints();
		gbc_lbIMC.anchor = GridBagConstraints.EAST;
		gbc_lbIMC.insets = new Insets(0, 0, 0, 5);
		gbc_lbIMC.gridx = 0;
		gbc_lbIMC.gridy = 3;
		tfForm.add(lbIMC, gbc_lbIMC);
		lbIMC.setFont(Option.font("text"));

		tfIMC = new JTextField();
		tfIMC.setFont(Option.font("text"));
		GridBagConstraints gbc_tfIMC = new GridBagConstraints();
		gbc_tfIMC.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfIMC.gridx = 1;
		gbc_tfIMC.gridy = 3;
		tfForm.add(tfIMC, gbc_tfIMC);
		tfIMC.setColumns(10);

		JPanel rdForm = new JPanel();
		GridBagConstraints gbc_rdForm = new GridBagConstraints();
		gbc_rdForm.insets = new Insets(0, 0, 5, 0);
		gbc_rdForm.fill = GridBagConstraints.HORIZONTAL;
		gbc_rdForm.gridx = 0;
		gbc_rdForm.gridy = 2;
		panel.add(rdForm, gbc_rdForm);
		GridBagLayout gbl_rdForm = new GridBagLayout();
		gbl_rdForm.columnWidths = new int[]{0, 0};
		gbl_rdForm.rowHeights = new int[]{22, 0, 0, 0, 0};
		gbl_rdForm.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_rdForm.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		rdForm.setLayout(gbl_rdForm);

		rdIMC = new JRadioButton();
		rdIMC.setSelected(true);
		calcModGroup.add(rdIMC);
		rdIMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMod(1);
			}
		});
		GridBagConstraints gbc_rdIMC = new GridBagConstraints();
		gbc_rdIMC.anchor = GridBagConstraints.WEST;
		gbc_rdIMC.insets = new Insets(0, 0, 5, 0);
		gbc_rdIMC.gridx = 0;
		gbc_rdIMC.gridy = 1;
		rdForm.add(rdIMC, gbc_rdIMC);
		rdIMC.setFont(Option.font("text"));

		rdTaille = new JRadioButton();
		rdTaille.setFont(Option.font("text"));
		calcModGroup.add(rdTaille);
		rdTaille.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMod(3);
			}
		});
		GridBagConstraints gbc_rdTaille = new GridBagConstraints();
		gbc_rdTaille.anchor = GridBagConstraints.WEST;
		gbc_rdTaille.insets = new Insets(0, 0, 5, 0);
		gbc_rdTaille.gridx = 0;
		gbc_rdTaille.gridy = 2;
		rdForm.add(rdTaille, gbc_rdTaille);

		rdPoids = new JRadioButton();
		rdPoids.setFont(Option.font("text"));
		calcModGroup.add(rdPoids);
		rdPoids.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectMod(2);
			}
		});
		GridBagConstraints gbc_rdPoids = new GridBagConstraints();
		gbc_rdPoids.anchor = GridBagConstraints.WEST;
		gbc_rdPoids.gridx = 0;
		gbc_rdPoids.gridy = 3;
		rdForm.add(rdPoids, gbc_rdPoids);

		lbTip = new JLabel();
		lbTip.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTip = new GridBagConstraints();
		gbc_lbTip.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbTip.gridx = 0;
		gbc_lbTip.gridy = 3;
		panel.add(lbTip, gbc_lbTip);
		


		btCalculer = new JButton();
		btCalculer.setFont(Option.font("button"));
		btCalculer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculate();
			}
		});
		add(btCalculer, BorderLayout.SOUTH);
	}

	private void selectMod(int mod) {
		switch (mod) {
			case 1:
				tfPoids.setEditable(true);
				tfTaille.setEditable(true);
				tfIMC.setEditable(false);

				lbTip.setText(dictionary.get("tip.bmi"));	//"Calculez le IMC avec le poids et la taille."

				calcMod = 1;

				break;
			case 2:
				tfPoids.setEditable(false);
				tfTaille.setEditable(true);
				tfIMC.setEditable(true);

				lbTip.setText(dictionary.get("tip.weight"));	//"Calculez le poids avec le IMC et la taille."

				calcMod = 2;

				break;
			case 3:
				tfPoids.setEditable(true);
				tfTaille.setEditable(false);
				tfIMC.setEditable(true);

				lbTip.setText(dictionary.get("tip.height"));	//"Calculez la taille avec le poids et le IMC."

				calcMod = 3;

				break;
			default:

				break;
		}
	}

	private void calculate() {
		float poids = checkField(tfPoids);
		float taille = checkField(tfTaille);
		float imc = checkField(tfIMC);

		if (poids <= 0) return;
		if (taille <= 0) return;
		if (imc <= 0) return;

		switch (calcMod) {
			case 1:
				tfIMC.setText(Integer.toString((int) Math.round(poids / Math.pow(taille/100, 2))));

				break;
			case 2:
				tfPoids.setText(Integer.toString((int) Math.round(imc*Math.pow(taille/100, 2))));

				break;
			case 3:
				tfTaille.setText(Integer.toString((int) Math.round(Math.sqrt(1/imc*poids)*100)));

				break;
		}
	}

	private float checkField(JTextField tf) {

		if (!tf.isEditable()) {
			return 1;
		} else {
			try {
				float value = Float.parseFloat(tf.getText());

				if (value > 0) {
					return value;
				} else {
					JOptionPane.showMessageDialog(null, dictionary.get("error.numbInf0"));	//Erreur: Vous devez entrer un nombre superieur a 0.
					return -1;
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, dictionary.get("error.invalidNumber"));	//Erreur: Vous devez entrer un nombre.

				return 0;
			}
		}
	}

	public void setLanguage(Dictionary dict) {
		dictionary = dict;
		
		lbTitle.setText(dict.get("title")); 	//"Outil de mesure du IMC"
		lbPoids.setText(dict.get("weight"));	//"Poids (Kg) :"
		lbTaille.setText(dict.get("height"));	//"Taille (cm) :"
		lbIMC.setText(dict.get("bmi"));			//"IMC :"

		rdIMC.setText(dict.get("calc.bmi"));		//"Calculer le IMC"
		rdTaille.setText(dict.get("calc.height"));	//"Calculer la taille"
		rdPoids.setText(dict.get("calc.weight"));	//"Calculer le poids"
		
		btCalculer.setText(dict.get("calculate"));

		selectMod(calcMod);
	}
}
