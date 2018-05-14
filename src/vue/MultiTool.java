package vue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.DefaultListModel;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;

import model.Language;
import model.Option;
import model.Language.Dictionary;

import javax.swing.event.ListSelectionEvent;
import java.awt.Toolkit;

public class MultiTool extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private static MultiTool instance = new MultiTool();

	public static MultiTool getInstance() {
		return instance;
	}


	private ArrayList<Menu> panels = new ArrayList<Menu>();
	private int currentPanel = -1;

	private DefaultListModel<String> listModel = new DefaultListModel<String>();

	private MenuBar menuBar;
	private JPanel contentPane;
	private JList<String> list;

	private final int frameWidth = 715;
	private final int frameHeight = 475;

	private MultiTool() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MultiTool.class.getResource("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, frameWidth, frameHeight);

		menuBar = new MenuBar();
		setJMenuBar(menuBar);



		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setMinimumSize(new Dimension(frameWidth, frameHeight));



		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{146, 0, 0};
		gbl_contentPane.rowHeights = new int[]{438, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		list = new JList<>(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if (list.getSelectedIndex() >= 0) {
						selectMenu(list.getSelectedIndex());
					}
				}
			}
		});
		list.setFont(Option.font("text"));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		contentPane.add(list, gbc_list);
	}

	public void selectMenu(int menuId) {
		if (currentPanel >= 0) {
			panels.get(currentPanel).setVisible(false);
		}

		Menu panel = panels.get(menuId);
		panel.setVisible(true);


		currentPanel = menuId;
	}

	public void addToolMenu(Menu panel) {
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);
		panel.setVisible(false);

		panels.add(panel);
	}

	public void changeLanguage(Language lang) {
		setLanguage(lang.getDictionary("Main"));

		menuBar.setLanguage(lang.getDictionary("MenuBar"));

		panels.forEach(pnl->{
			String dictName = pnl.getClass().getSimpleName();

			if (lang.dictionaryExists(dictName)) {
				pnl.setLanguage(lang.getDictionary(dictName));
			}
		});
	}

	private void setLanguage(Dictionary dict) {
		setTitle(dict.get("title"));
		
		
		int selectedIndex = list.getSelectedIndex();
		listModel.clear();
		
		panels.forEach(pnl -> {
			Dictionary pnldict = dict.getLanguage().getDictionary(pnl.getClass().getSimpleName());
			
			listModel.addElement(pnldict.get("name"));
		});
		
		if (currentPanel == -1) {
			list.setSelectedIndex(0);
		} else {
			list.setSelectedIndex(selectedIndex);
		}
	}
}
