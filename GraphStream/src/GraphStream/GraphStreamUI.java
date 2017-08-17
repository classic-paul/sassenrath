package GraphStream;

import org.osgi.service.component.annotations.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

@Component
public class GraphStreamUI implements ViewerListener {
	protected boolean loop = true;
	protected Viewer viewer;
	protected View view;
	protected Graph graph;
	protected ViewerPipe fromViewer;
	Map<String, double[]> positions = new HashMap<String, double[]>();

	// FIXME style sheet for nodes
	// TODO when implementing, a factory which adds necessary attributes to
	// nodes would be a good idea
	public GraphStreamUI() {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		graph = new SingleGraph("Clicks");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet",
				"node {size-mode: fit; shape: box; fill-color: white; padding: 3; stroke-color: black; stroke-mode: plain;} node:selected {fill-color: red;}");

		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		view = viewer.addDefaultView(false);
		fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);
		viewer.enableAutoLayout();

		Node a = graph.addNode("A");
		Node b = graph.addNode("B");
		Node c = graph.addNode("C");
		Node d = graph.addNode("D");
		Node e = graph.addNode("E");
		Node f = graph.addNode("F");

		a.addAttribute("ui.label", a.getId());
		b.addAttribute("ui.label", b.getId());
		c.addAttribute("ui.label", c.getId());
		d.addAttribute("ui.label", d.getId());
		e.addAttribute("ui.label", e.getId());
		f.addAttribute("ui.label", f.getId());

		graph.addEdge("AB", "A", "B", true);
		graph.addEdge("CA", "A", "C", true);
		graph.addEdge("BD", "B", "D", true);
		graph.addEdge("BE", "B", "E", true);
		graph.addEdge("EF", "E", "F", true);

		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				try {
					while (loop) {
						view.requestFocus();
						fromViewer.blockingPump();
					}
				} catch (Exception e) {
					System.out.print(e.toString());
				}
			}

		});
	}

	public void viewClosed(String id) {
		loop = false;
	}

	public void buttonPushed(String id) {
		positions.put(id, Toolkit.nodePosition(graph, id));
	}

	public void buttonReleased(String id) {
		if (hasNotMoved(id)) {
			System.out.println("Node was clicked " + id);
		} else {
			System.out.println("Node was moved " + id);
		}
	}

	private boolean hasNotMoved(String id) {
		if(graph.getNode(id).hasAttribute("ui.selected")){
			System.out.println("Node selected " + id);
		}
		return Arrays.equals(positions.get(id), Toolkit.nodePosition(graph, id));
	}

	public View getView() {
		return this.view;
	}
}
