package io.github.pcp1976.sassenrath.directorysourceplugin;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import io.github.pcp1976.sassenrath.api.Plugin;

public class View extends JFrame {


	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JFileChooser fileChooser;
	private Control control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					View frame = new View();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 113);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		textField = new JTextField();
		textField.setColumns(10);
		
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		JButton btnOk = new JButton("OK");

		JButton btnNewButton = new JButton("Browse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fileChooser.showSaveDialog(contentPane);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				    setFolder(fileChooser.getSelectedFile().toPath());
				}
			}
		});

		JButton btnCancel = new JButton("Cancel");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(btnCancel)
										.addPreferredGap(ComponentPlacement.RELATED, 195, Short.MAX_VALUE)
										.addComponent(btnNewButton).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnOk)))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(btnOk)
								.addComponent(btnNewButton).addComponent(btnCancel))
						.addContainerGap(192, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public Path getFolder() {
		return control.getDirectory();
	}

	public void setFolder(Path folder) {
		control.setDirectory(folder);
	}
	public void setControl(Plugin control){
		this.control = (Control) control;
	}
	
	public Plugin getControl(){
		return this.control;
	}
}
