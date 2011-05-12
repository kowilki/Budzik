package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame {
	NewAction new_action = null;

	JPanel createNewActionGUI() {
		JPanel panel = new JPanel();
		JButton btnAdd = new JButton("Add new action");
		panel.add(btnAdd, BorderLayout.CENTER);
		panel.setVisible(true);

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*if (new_action == null) {
					new_action = new NewAction();
				}*/
				new_action = new NewAction();
				new_action.setVisible(true);
			}
		});
		return panel;
	}

	public MainWindow() {
		// TODO Auto-generated constructor stub

		add(createNewActionGUI());
		this.setSize(300, 300);
		// pack();
		setVisible(true);
	}

}
