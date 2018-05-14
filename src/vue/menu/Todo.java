package vue.menu;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import model.Language.Dictionary;
import model.Option;
import vue.Menu;

import java.awt.Color;

//import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Todo extends Menu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String name = "Todo";
	
	/**
	 * Create the panel.
	 */
	
	private JPanel mainList;
	private JTextField txtTodo;
	private JButton btnAdd;
	
	public Todo() {
		setLayout(new BorderLayout());

        mainList = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);

        add(new JScrollPane(mainList));
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        
        txtTodo = new JTextField();
        txtTodo.setText("Todo");
        txtTodo.setFont(Option.font("text"));
        panel.add(txtTodo, BorderLayout.CENTER);
        txtTodo.setColumns(10);

        btnAdd = new JButton("Add");
        btnAdd.setFont(Option.font("button"));
        panel.add(btnAdd, BorderLayout.EAST);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addLine(txtTodo.getText());
            }
        });
        add(panel, BorderLayout.SOUTH);

	}
	
	public TodoRow addLine(String text) {
		TodoRow row = new TodoRow(text);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainList.add(row, gbc, 0);

        validate();
        repaint();
        
        return row;
	}
	
	public class TodoRow extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JCheckBox checkBox;
		
		public TodoRow(String text) {
			setBackground(Color.WHITE);
			setLayout(new BorderLayout(0, 0));
			setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			
			
			JLabel lblLine = new JLabel(text);
			add(lblLine, BorderLayout.CENTER);
			lblLine.setFont(Option.font("text"));
			lblLine.setBorder(new EmptyBorder(0, 5, 0, 0));
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			add(panel, BorderLayout.EAST);

			
//			JButton btnNewButton = new JButton("");
//			btnNewButton.setVisible(false);
//			btnNewButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					getParent().removeAll();;
//				}
//			});
//			btnNewButton.setIcon(new ImageIcon(TodoRow.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));

			checkBox = new JCheckBox("");
			checkBox.setBackground(Color.WHITE);
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

				}
			});
			panel.add(checkBox);

//			panel.add(btnNewButton);
		}
		
		public void setChecked(boolean check) {
			checkBox.setSelected(check);
		}
	}


	public void setLanguage(Dictionary dict) {
		btnAdd.setText(dict.get("add"));
	}
}
