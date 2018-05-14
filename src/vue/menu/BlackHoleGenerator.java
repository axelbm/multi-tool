package vue.menu;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;


public class BlackHoleGenerator extends Menu {
	private static final long serialVersionUID = 1L;


	private JLabel lbTitle, lbVanity;

	private Image image;

	/**
	 * Create the panel.
	 */
	public BlackHoleGenerator() {
		image = Toolkit.getDefaultToolkit().createImage("resources/black hole.gif");

//		Paramettrage du panel
		setBackground(Color.WHITE);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{198, 0};
		gridBagLayout.rowHeights = new int[]{0, 36, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

//		le Titre
		lbTitle = new JLabel();
		lbTitle.setFont(Option.font("title"));
		GridBagConstraints gbc_lbTitle = new GridBagConstraints();
		gbc_lbTitle.anchor = GridBagConstraints.WEST;
		gbc_lbTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lbTitle.gridx = 0;
		gbc_lbTitle.gridy = 0;
		add(lbTitle, gbc_lbTitle);

//		Et le message de vanit�
		lbVanity = new JLabel();
		lbVanity.setFont(Option.font("vanity"));
		GridBagConstraints gbc_lbVanity = new GridBagConstraints();
		gbc_lbVanity.anchor = GridBagConstraints.NORTH;
		gbc_lbVanity.gridx = 0;
		gbc_lbVanity.gridy = 1;
		add(lbVanity, gbc_lbVanity);

	}

//	Pour modifier l'affichage pour mettre mon image
    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = (getWidth()-image.getWidth(null))/2;
        int height = (getHeight()-image.getHeight(null))/2;

        g.drawImage(image, width, height, this);
    }



	public void setLanguage(Dictionary dict) {
		lbTitle.setText(dict.get("title"));	//"Générateur de trou noir"
		lbVanity.setText(dict.get("wip"));	//"WIP"
	}
}
