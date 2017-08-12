package anotherAttempt;

import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Component;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class Windy implements ViewerListener {
	protected boolean loop = true;

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Windy window = new Windy();
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
	public Windy() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Graph graph = new MultiGraph("embedded");
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();
		View view = viewer.addDefaultView(false);

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Node a = graph.addNode("A");
		Node b = graph.addNode("B");
		Node c = graph.addNode("C");

		a.addAttribute("ui.label", a.getId());
		b.addAttribute("ui.label", b.getId());
		c.addAttribute("ui.label", c.getId());

		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.addEdge("AB", "A", "B", true);
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "A", "C", true);
		frame.add((Component) view);

	}

	@Override
	public void viewClosed(String id) {
		loop = false;
	}

	@Override
	public void buttonPushed(String id) {
		System.out.println("Button pushed on node " + id);
	}

	@Override
	public void buttonReleased(String id) {
		System.out.println("Button released on node " + id);
	}
}
