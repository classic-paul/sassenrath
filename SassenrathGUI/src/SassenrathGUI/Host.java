package SassenrathGUI;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import org.osgi.service.component.annotations.*;

import GraphStream.Example;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

@Component
public class Host {

	private JFrame frmSassenrath;
	private JTextField textField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Host window = new Host();
					window.frmSassenrath.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Host() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		
		
		frmSassenrath = new JFrame();
		frmSassenrath.setTitle("Sassenrath");
		frmSassenrath.setBounds(100, 100, 450, 300);
		frmSassenrath.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSassenrath.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as");
		mnFile.add(mntmSaveAs);
		
		JSeparator separator_2 = new JSeparator();
		mnFile.add(separator_2);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		Example w = new Example();
		frmSassenrath.getContentPane().add((java.awt.Component) w.getView(), BorderLayout.CENTER);

		/*
		JFrame splitPane = new JFrame();
		frmSassenrath.getContentPane().add(splitPane, BorderLayout.CENTER);
		Example w = new Example();
		
		splitPane.getContentPane().add((java.awt.Component) w.getView());
		
		//textField = new JTextField();
		//splitPane.setLeftComponent(textField);
		//textField.setColumns(10);
		 * 
		 */
	}

}
