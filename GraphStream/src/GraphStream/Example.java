package GraphStream;

import org.osgi.service.component.annotations.*;

import java.awt.EventQueue;
import java.util.concurrent.Executors;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.spriteManager.*;

@Component
public class Example implements ViewerListener {
	protected boolean loop = true;
	protected Viewer viewer;
	protected View view;
	protected Graph graph;
	protected ViewerPipe fromViewer;

	public static void main(String args[]) {
		new Example();
	}

	public Example() {
		//System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		// TODO stylesheet for sprites
		// TODO when implementing, a factory which adds necessary attributes to
		// nodes would be a good idea
		graph = new SingleGraph("Clicks");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");

		SpriteManager sman = new SpriteManager(graph);

		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		view = viewer.addDefaultView(false);
		fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);
		viewer.enableAutoLayout();

		Sprite s = sman.addSprite("S");
		Sprite t = sman.addSprite("T");
		Sprite u = sman.addSprite("U");
		Node a = graph.addNode("A");
		s.attachToNode("A");
		Node b = graph.addNode("B");
		t.attachToNode("B");
		Node c = graph.addNode("C");
		u.attachToNode("C");
		a.addAttribute("ui.label", a.getId());
		b.addAttribute("ui.label", b.getId());
		c.addAttribute("ui.label", c.getId());

		graph.addEdge("AB", "A", "B", true);
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "A", "C", true);

		Executors.newSingleThreadExecutor().execute(new Runnable() {
		    @Override 
		    public void run() {
				try {
					while (loop) {
						view.requestFocus();
						fromViewer.pump();
					}
				} catch (Exception e) {
					System.out.print(e.toString());
				}
			}

		});
	}

	/*
	 * new Thread(new Runnable() {
	 * 
	 * @Override public void run() {
	 * 
	 * } }).start(); }
	 */
	public void viewClosed(String id) {
		loop = false;
	}

	public void buttonPushed(String id) {
		System.out.println("Button pushed on node " + id);
	}

	public void buttonReleased(String id) {
		System.out.println("Button released on node " + id);
	}

	public View getView() {
		return this.view;
	}
}
