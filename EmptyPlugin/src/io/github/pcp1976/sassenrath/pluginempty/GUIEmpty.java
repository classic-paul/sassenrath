package io.github.pcp1976.sassenrath.pluginempty;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class GUIEmpty {

	private JFrame frame;
	private JTextField txtEmptypluginPerformsNo;

	/**
	 * Launch the application.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIEmpty window = new GUIEmpty();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIEmpty() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 335, 95);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtEmptypluginPerformsNo = new JTextField();
		txtEmptypluginPerformsNo.setEditable(false);
		txtEmptypluginPerformsNo.setText("EmptyPlugin performs no operations - it is not configurable.");
		frame.getContentPane().add(txtEmptypluginPerformsNo, BorderLayout.NORTH);
		txtEmptypluginPerformsNo.setColumns(10);
		
		JButton btnOk = new JButton("OK");
		frame.getContentPane().add(btnOk, BorderLayout.SOUTH);
	}

}
