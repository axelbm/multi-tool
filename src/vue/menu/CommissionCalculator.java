package vue.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CommissionCalculator extends Menu {
	private static final long serialVersionUID = 1L;

	private Dictionary dictionary;

	private JTextField tfVentes, tfCommisson, tfTaux;
	private JLabel lbTitle, lbVentes, lbTaux, lbCommisson, lbTip;
	private JButton btnCalculate;

	/**
	 * Create the panel.
	 */
	public CommissionCalculator() {
		setLayout(new BorderLayout(0, 0));

		JPanel topContainer = new JPanel();
		add(topContainer, BorderLayout.CENTER);
		GridBagLayout gbl_topContainer = new GridBagLayout();
		gbl_topContainer.columnWidths = new int[]{0, 0};
		gbl_topContainer.rowHeights = new int[]{0, 0, 0, 0};
		gbl_topContainer.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_topContainer.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		topContainer.setLayout(gbl_topContainer);

		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.anchor = GridBagConstraints.WEST;
		gbc_lbTitle.gridx = 0;
		gbc_lbTitle.gridy = 0;
		topContainer.add(lbTitle, gbc_lbTitle);

		JPanel form = new JPanel();
		GridBagConstraints gbc_form = new GridBagConstraints();
		gbc_form.insets = new Insets(0, 0, 5, 0);
		gbc_form.fill = GridBagConstraints.BOTH;
		gbc_form.gridx = 0;
		gbc_form.gridy = 1;
		topContainer.add(form, gbc_form);
		GridBagLayout gbl_form = new GridBagLayout();
		gbl_form.columnWidths = new int[]{113, 0, 0};
		gbl_form.rowHeights = new int[]{0, 5, 0, 0, 0};
		gbl_form.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_form.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		form.setLayout(gbl_form);

		lbVentes = new JLabel();
		lbVentes.setFont(Option.font("text"));
		GridBagConstraints gbc_lbVentes = new GridBagConstraints();
		gbc_lbVentes.anchor = GridBagConstraints.EAST;
		gbc_lbVentes.insets = new Insets(0, 0, 5, 5);
		gbc_lbVentes.gridx = 0;
		gbc_lbVentes.gridy = 0;
		form.add(lbVentes, gbc_lbVentes);

		tfVentes = new JTextField();
		tfVentes.setFont(Option.font("text"));
		GridBagConstraints gbc_tfVentes = new GridBagConstraints();
		gbc_tfVentes.insets = new Insets(0, 0, 5, 0);
		gbc_tfVentes.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfVentes.gridx = 1;
		gbc_tfVentes.gridy = 0;
		form.add(tfVentes, gbc_tfVentes);
		tfVentes.setColumns(10);

		lbTaux = new JLabel();
		lbTaux.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTaux = new GridBagConstraints();
		gbc_lbTaux.anchor = GridBagConstraints.EAST;
		gbc_lbTaux.insets = new Insets(0, 0, 5, 5);
		gbc_lbTaux.gridx = 0;
		gbc_lbTaux.gridy = 2;
		form.add(lbTaux, gbc_lbTaux);

		tfTaux = new JTextField();
		tfTaux.setEditable(false);
		tfTaux.setFont(Option.font("text"));
		GridBagConstraints gbc_tfTaux = new GridBagConstraints();
		gbc_tfTaux.insets = new Insets(0, 0, 5, 0);
		gbc_tfTaux.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTaux.gridx = 1;
		gbc_tfTaux.gridy = 2;
		form.add(tfTaux, gbc_tfTaux);
		tfTaux.setColumns(10);

		lbCommisson = new JLabel();
		lbCommisson.setFont(Option.font("text"));
		GridBagConstraints gbc_lbCommisson = new GridBagConstraints();
		gbc_lbCommisson.insets = new Insets(0, 0, 0, 5);
		gbc_lbCommisson.anchor = GridBagConstraints.EAST;
		gbc_lbCommisson.gridx = 0;
		gbc_lbCommisson.gridy = 3;
		form.add(lbCommisson, gbc_lbCommisson);

		tfCommisson = new JTextField();
		tfCommisson.setFont(Option.font("text"));
		tfCommisson.setEditable(false);
		GridBagConstraints gbc_tfCommisson = new GridBagConstraints();
		gbc_tfCommisson.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCommisson.gridx = 1;
		gbc_tfCommisson.gridy = 3;
		form.add(tfCommisson, gbc_tfCommisson);
		tfCommisson.setColumns(10);

		lbTip = new JLabel();
		lbTip.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTip = new GridBagConstraints();
		gbc_lbTip.anchor = GridBagConstraints.WEST;
		gbc_lbTip.gridx = 0;
		gbc_lbTip.gridy = 2;
		topContainer.add(lbTip, gbc_lbTip);

		btnCalculate = new JButton();
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculate();
			}
		});
		btnCalculate.setFont(Option.font("button"));
		add(btnCalculate, BorderLayout.SOUTH);

	}

	public void calculate () {
		try {
			float ventes = Float.parseFloat(tfVentes.getText());
			double taux = 0;

			if (ventes <= 0)
				taux = 0;
			else if (ventes < 100)
				taux = 0.1;
			else if (ventes < 500)
				taux = 0.2;
			else
				taux = 0.3;

			tfCommisson.setText(String.format("%.2f $", ventes*taux));
			tfTaux.setText(String.format("%.2f %%", taux));

			if (taux == 0)
				JOptionPane.showMessageDialog(this, dictionary.get("warning"));	//"Attention! Vous étes renvoyez!"
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, dictionary.get("error.invalidNumber"));	//"Erreur : Vous devez entrer un nombre."
		}
	}

	public void setLanguage(Dictionary dict) {
		dictionary = dict;

		lbTitle.setText(dictionary.get("title"));	//"Calcule des commission")
		lbVentes.setText(dictionary.get("sales"));	//"Total des ventes :")
		lbTaux.setText(dictionary.get("rate"));	//"Taux :")
		lbCommisson.setText(dictionary.get("commission"));	//"Commission :")
		lbTip.setText(dictionary.get("tip"));	//"Calculer la commission selon le total des ventes")

		btnCalculate.setText(dictionary.get("calculate"));
	}
}
