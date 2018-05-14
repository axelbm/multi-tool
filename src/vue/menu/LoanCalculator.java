package vue.menu;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class LoanCalculator extends Menu {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Dictionary dictionary;

	private JTextField tfLoan, tfInterest, tfMonthlyPayment, tfCost;
	private JComboBox<String> cbTerm;
	private JLabel lbTitle, lbLoan, lbInterest, lbTerm, lbMonthlyPayment, lbCost;
	private JButton btnCalc;

	public LoanCalculator() {
		setLayout(new BorderLayout(0, 0));

		JPanel topContainer = new JPanel();
		add(topContainer, BorderLayout.CENTER);
		GridBagLayout gbl_topContainer = new GridBagLayout();
		gbl_topContainer.columnWidths = new int[] { 0, 0 };
		gbl_topContainer.rowHeights = new int[] { 0, 201, 0 };
		gbl_topContainer.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_topContainer.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		topContainer.setLayout(gbl_topContainer);

		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.fill = GridBagConstraints.HORIZONTAL;
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
		gbl_form.columnWidths = new int[] { 71, 0, 0 };
		gbl_form.rowHeights = new int[] { 0, 0, 0, 23, 0, 0, 0, 0, 0 };
		gbl_form.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_form.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		form.setLayout(gbl_form);

		lbLoan = new JLabel();
		lbLoan.setFont(Option.font("text"));
		GridBagConstraints gbc_lbLoan = new GridBagConstraints();
		gbc_lbLoan.insets = new Insets(0, 0, 5, 5);
		gbc_lbLoan.anchor = GridBagConstraints.EAST;
		gbc_lbLoan.gridx = 0;
		gbc_lbLoan.gridy = 0;
		form.add(lbLoan, gbc_lbLoan);

		tfLoan = new JTextField();
		tfLoan.setFont(Option.font("text"));
		GridBagConstraints gbc_tfLoan = new GridBagConstraints();
		gbc_tfLoan.insets = new Insets(0, 0, 5, 0);
		gbc_tfLoan.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfLoan.gridx = 1;
		gbc_tfLoan.gridy = 0;
		form.add(tfLoan, gbc_tfLoan);
		tfLoan.setColumns(10);

		lbInterest = new JLabel();
		lbInterest.setFont(Option.font("text"));
		GridBagConstraints gbc_lbInterest = new GridBagConstraints();
		gbc_lbInterest.anchor = GridBagConstraints.EAST;
		gbc_lbInterest.insets = new Insets(0, 0, 5, 5);
		gbc_lbInterest.gridx = 0;
		gbc_lbInterest.gridy = 1;
		form.add(lbInterest, gbc_lbInterest);

		tfInterest = new JTextField();
		tfInterest.setFont(Option.font("text"));
		GridBagConstraints gbc_tfInterest = new GridBagConstraints();
		gbc_tfInterest.anchor = GridBagConstraints.SOUTH;
		gbc_tfInterest.insets = new Insets(0, 0, 5, 0);
		gbc_tfInterest.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfInterest.gridx = 1;
		gbc_tfInterest.gridy = 1;
		form.add(tfInterest, gbc_tfInterest);
		tfInterest.setColumns(10);

		lbTerm = new JLabel();
		lbTerm.setFont(Option.font("text"));
		GridBagConstraints gbc_lbTerm = new GridBagConstraints();
		gbc_lbTerm.anchor = GridBagConstraints.EAST;
		gbc_lbTerm.insets = new Insets(0, 0, 5, 5);
		gbc_lbTerm.gridx = 0;
		gbc_lbTerm.gridy = 2;
		form.add(lbTerm, gbc_lbTerm);

		cbTerm = new JComboBox<String>();
		cbTerm.setFont(Option.font("text"));

		GridBagConstraints gbc_cbTerm = new GridBagConstraints();
		gbc_cbTerm.insets = new Insets(0, 0, 5, 0);
		gbc_cbTerm.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTerm.gridx = 1;
		gbc_cbTerm.gridy = 2;
		form.add(cbTerm, gbc_cbTerm);

		lbMonthlyPayment = new JLabel();
		lbMonthlyPayment.setFont(Option.font("text"));
		lbMonthlyPayment.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lbMonthlyPayment = new GridBagConstraints();
		gbc_lbMonthlyPayment.insets = new Insets(0, 0, 5, 5);
		gbc_lbMonthlyPayment.anchor = GridBagConstraints.EAST;
		gbc_lbMonthlyPayment.gridx = 0;
		gbc_lbMonthlyPayment.gridy = 4;
		form.add(lbMonthlyPayment, gbc_lbMonthlyPayment);

		tfMonthlyPayment = new JTextField();
		tfMonthlyPayment.setFont(Option.font("text"));
		tfMonthlyPayment.setEditable(false);
		GridBagConstraints gbc_tfMonthlyPayment = new GridBagConstraints();
		gbc_tfMonthlyPayment.anchor = GridBagConstraints.SOUTH;
		gbc_tfMonthlyPayment.insets = new Insets(0, 0, 5, 0);
		gbc_tfMonthlyPayment.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMonthlyPayment.gridx = 1;
		gbc_tfMonthlyPayment.gridy = 4;
		form.add(tfMonthlyPayment, gbc_tfMonthlyPayment);
		tfMonthlyPayment.setColumns(10);

		lbCost = new JLabel();
		lbCost.setFont(Option.font("text"));
		GridBagConstraints gbc_lbCost = new GridBagConstraints();
		gbc_lbCost.insets = new Insets(0, 0, 5, 5);
		gbc_lbCost.anchor = GridBagConstraints.EAST;
		gbc_lbCost.gridx = 0;
		gbc_lbCost.gridy = 5;
		form.add(lbCost, gbc_lbCost);

		tfCost = new JTextField();
		tfCost.setFont(Option.font("text"));
		tfCost.setEditable(false);
		GridBagConstraints gbc_tfCost = new GridBagConstraints();
		gbc_tfCost.anchor = GridBagConstraints.SOUTH;
		gbc_tfCost.insets = new Insets(0, 0, 5, 0);
		gbc_tfCost.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCost.gridx = 1;
		gbc_tfCost.gridy = 5;
		form.add(tfCost, gbc_tfCost);
		tfCost.setColumns(10);

		btnCalc = new JButton();
		btnCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				calculate();
			}
		});
		btnCalc.setFont(Option.font("button"));
		add(btnCalc, BorderLayout.SOUTH);

	}

	public void setLanguage(Dictionary dict) {
		dictionary = dict;

		lbTitle.setText(dict.get("title"));
		lbLoan.setText(dict.get("loan"));
		lbInterest.setText(dict.get("interest"));
		lbTerm.setText(dict.get("term"));
		lbMonthlyPayment.setText(dict.get("payment"));
		lbCost.setText(dict.get("cost"));
		btnCalc.setText(dict.get("calculate"));

		int selectedTerm = cbTerm.getSelectedIndex();
		if (selectedTerm <= 0) 
			selectedTerm = 0;
		
		String[] termlist = new String[8];
		termlist[0] = dict.get("termSingleYear");
		for (int i = 1; i < termlist.length; i++) {
			termlist[i] = dict.format("termMultiYear", i + 1, (i + 1) * 12);
		}
		cbTerm.setModel(new DefaultComboBoxModel<String>(termlist));
		cbTerm.setSelectedIndex(selectedTerm);
	}

	private void calculate() {
		double loan = CheckField(tfLoan);
		double interest = CheckField(tfInterest) / 1200;

		if (loan <= 0) {
			JOptionPane.showMessageDialog(null, dictionary.get("err.loan"));
			return;
		}
		if (interest <= 0) {
			JOptionPane.showMessageDialog(null, dictionary.get("err.interestSup100"));
			return;
		} else if (interest > 1) {
			JOptionPane.showMessageDialog(null, dictionary.get("err.interestLower0"));
			return;
		}

		float month = (cbTerm.getSelectedIndex() + 1) * 12;

		double monthlyPayment = (interest * loan) / (1 - Math.pow(1 + interest, -month));
		tfMonthlyPayment.setText(NumberFormat.getCurrencyInstance().format(monthlyPayment));
		tfCost.setText(NumberFormat.getCurrencyInstance().format(monthlyPayment * month - loan));
	}

	private float CheckField(JTextField tf) {

		if (!tf.isEditable()) {
			return 1;
		} else {
			try {
				float value = Float.parseFloat(tf.getText());

				if (value > 0) {
					return value;
				} else {
					JOptionPane.showMessageDialog(null, dictionary.get("err.numLower0"));
					return -1;
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, dictionary.get("err.invalidNum"));

				return 0;
			}
		}
	}
}
