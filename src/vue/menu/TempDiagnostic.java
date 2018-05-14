package vue.menu;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
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
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TempDiagnostic extends Menu {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Dictionary dictionary;

	private JTextField tfTemp, tfResult;
	private JLabel lbTitle, lbTemp, lbTempTip, lbResult;
	private JButton btCalculate;

	/**
	 * Create the panel.
	 */
	public TempDiagnostic() {
		setLayout(new BorderLayout(0, 0));

		JPanel topContainer = new JPanel();
		add(topContainer, BorderLayout.NORTH);
		GridBagLayout gbl_topContainer = new GridBagLayout();
		gbl_topContainer.columnWidths = new int[]{0, 0};
		gbl_topContainer.rowHeights = new int[]{0, 0, 0};
		gbl_topContainer.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_topContainer.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		topContainer.setLayout(gbl_topContainer);

		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.anchor = GridBagConstraints.WEST;
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.gridx = 0;
		gbc_lbTitle.gridy = 0;
		topContainer.add(lbTitle, gbc_lbTitle);

		JPanel form = new JPanel();
		GridBagConstraints gbc_form = new GridBagConstraints();
		gbc_form.fill = GridBagConstraints.BOTH;
		gbc_form.gridx = 0;
		gbc_form.gridy = 1;
		topContainer.add(form, gbc_form);
		GridBagLayout gbl_form = new GridBagLayout();
		gbl_form.columnWidths = new int[]{112, 0, 0};
		gbl_form.rowHeights = new int[]{0, 31, 0, 0};
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

		tfTemp = new JTextField();
		tfTemp.setFont(Option.font("text"));
		GridBagConstraints gbc_tfTemp = new GridBagConstraints();
		gbc_tfTemp.insets = new Insets(0, 0, 5, 0);
		gbc_tfTemp.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfTemp.gridx = 1;
		gbc_tfTemp.gridy = 0;
		form.add(tfTemp, gbc_tfTemp);
		tfTemp.setColumns(10);

		lbTempTip = new JLabel();
		lbTempTip.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTempTip = new GridBagConstraints();
		gbc_lbTempTip.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbTempTip.insets = new Insets(0, 0, 5, 0);
		gbc_lbTempTip.gridx = 1;
		gbc_lbTempTip.gridy = 1;
		form.add(lbTempTip, gbc_lbTempTip);

		lbResult = new JLabel();
		lbResult.setFont(Option.font("text"));
		GridBagConstraints gbc_lbResult = new GridBagConstraints();
		gbc_lbResult.anchor = GridBagConstraints.EAST;
		gbc_lbResult.insets = new Insets(0, 0, 0, 5);
		gbc_lbResult.gridx = 0;
		gbc_lbResult.gridy = 2;
		form.add(lbResult, gbc_lbResult);

		tfResult = new JTextField();
		tfResult.setEditable(false);
		tfResult.setFont(Option.font("text"));
		GridBagConstraints gbc_tfResult = new GridBagConstraints();
		gbc_tfResult.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfResult.gridx = 1;
		gbc_tfResult.gridy = 2;
		form.add(tfResult, gbc_tfResult);
		tfResult.setColumns(10);

		btCalculate = new JButton();
		btCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculate();
			}
		});
		btCalculate.setFont(Option.font("button"));
		add(btCalculate, BorderLayout.SOUTH);

	}

	public void calculate() {
		try {
			float temp = Float.parseFloat(tfTemp.getText());
			String text = "";

			if (temp <= 37) {
				text = dictionary.get("result.1");	//"Prendre l'air."
			} else if (temp <= 38) {
				text = dictionary.get("result.2");	//"Se reposer."
			} else if (temp <= 39) {
				text = dictionary.get("result.3");	//"Prendre de l'aspirine et de l'eau."
			} else {
				text = dictionary.get("result.4");	//"Aller à l'hopital."
				alerte();
			}

			tfResult.setText(text);
		} catch (NumberFormatException e) {

		}
	}

	public void alerte() {
		try {
			@SuppressWarnings("deprecation")
			Player p = Manager.createPlayer(new File("resources/alarme.wav").toURL());
			p.start();
		} catch (NoPlayerException | IOException e) {
			e.printStackTrace();
		}

		JOptionPane.showMessageDialog(this, dictionary.get("alert"));	//"ALERTE! VOUS DEVEZ ALLER A L'HOPITAL!!"
	}


	public void setLanguage(Dictionary dict) {
		dictionary = dict;
		
		lbTitle.setText(dict.get("title"));	//"Diagnostique de température"
		lbTemp.setText(dict.get("temperature"));	//"Température :"
		lbTempTip.setText(dict.get("tip"));	//"Entrez votre température interne."
		lbResult.setText(dict.get("result"));	//"Résultat :"

		tfTemp.setText(dict.get("example"));	//"ex: 37"

		btCalculate.setText(dict.get("calculate"));
	}
}
