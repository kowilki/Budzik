package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.EventManager;

public class NewAction extends JFrame {
	JLabel lbInterval = new JLabel("Interval (in seconds):");
	JTextField txtInterval = new JTextField(10);

	JComboBox cbxLogType = new JComboBox(new String[] { "memory usage",
			"processor usage" });
	JLabel lbFilePath = new JLabel("Midi file path:");
	JTextField txtFile = new JTextField(100);
	JButton btnFile = new JButton("Choose midi file to play");
	JButton btnAdd = new JButton("Add new action");
	JFileChooser fc;

	public NewAction() {
		BoxLayout bl = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		getContentPane().setLayout(bl);

		add(lbInterval);
		add(txtInterval);

		/*
		 * add(lbFilePath); add(txtFile); add(btnFile);
		 */

		add(cbxLogType);
		add(btnAdd);

		/*
		 * btnFile.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) {
		 * System.out.println("new window"); fc = new JFileChooser();
		 * fc.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent arg0) { File f =
		 * fc.getSelectedFile(); txtFile.setText(f.getAbsolutePath()); } });
		 * fc.showOpenDialog(null);
		 * 
		 * } });
		 */

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventManager manager = EventManager.getInstance();
				/*
				 * manager.addMidi(Integer.parseInt(txtInterval.getText()),
				 * txtFile.getText());
				 */
				Integer interval = Integer.parseInt(txtInterval.getText());
				int log_type = cbxLogType.getSelectedIndex();
				if (log_type == 0) {
					manager.logMemoryUsage(interval);
				} else if (log_type == 1) {
					manager.logProcessorUsage(interval);
				}
				setVisible(false);
				System.out.println("test");
			}

		});

		setVisible(true);
		setSize(300, 300);
	}

}
