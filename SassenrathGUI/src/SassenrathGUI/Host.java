package SassenrathGUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import org.osgi.service.component.annotations.*;
import GraphStream.GraphStreamUI;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import java.awt.ComponentOrientation;
import java.awt.CardLayout;
import java.awt.Dimension;

@Component
public class Host {

	private JFrame frmSassenrath;

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
			System.setProperty("sassenrath.UIDesignMode", "false");
		} catch (UnsupportedLookAndFeelException e) {
			// TODO add logging; handle exceptions
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		frmSassenrath = new JFrame();
		frmSassenrath.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		frmSassenrath.setIconImage(Toolkit.getDefaultToolkit().getImage(Host.class.getResource("/res/logo.png")));
		frmSassenrath.setTitle("Sassenrath");
		frmSassenrath.setBounds(100, 100, 720, 480);
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
		
		JMenu mnWindow = new JMenu("Window");
		menuBar.add(mnWindow);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JToolBar toolBar = new JToolBar();
		menuBar.add(toolBar);
		frmSassenrath.getContentPane().setLayout(new CardLayout(0, 0));

		JSplitPane splitPane = new JSplitPane();
		frmSassenrath.getContentPane().add(splitPane, "name_7178479776586");

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setMinimumSize(new Dimension(50, 5));

		if (System.getProperty("sassenrath.UIDesignMode").equals("true"))
		{
			JPanel p = new JPanel();
			splitPane.setRightComponent(p);
		} 
		else if (System.getProperty("sassenrath.UIDesignMode").equals("false"))
		{
			GraphStreamUI p = new GraphStreamUI();
			splitPane.setRightComponent((java.awt.Component) p.getView());
		}
		else
		{
			// TODO error handling!
		}
		
		splitPane.setLeftComponent(tabbedPane);

		tabbedPane.addTab("Tab 1", null, new JPanel(), null);
		tabbedPane.addTab("Tab 2", null, new JPanel(), null);

	}

}
