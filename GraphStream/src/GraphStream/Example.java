package GraphStream;

import org.osgi.service.component.annotations.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.spriteManager.*;

@Component
public class Example implements ViewerListener {
	protected boolean loop = true;

	public static void main(String args[]) {
		new Example();
	}

	public Example() {
		Graph graph = new SingleGraph("Clicks");
		SpriteManager sman = new SpriteManager(graph);

		// TODO stylesheet for sprites
		// TODO when implementing, a factory which adds necessary attributes to
		// nodes would be a good idea

		Sprite s = sman.addSprite("S");
		Sprite t = sman.addSprite("T");
		Sprite u = sman.addSprite("U");

		Viewer viewer = graph.display();

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
		ViewerPipe fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);

		while (loop) {
			try {
				fromViewer.blockingPump(); // or fromViewer.blockingPump(); in
											// the nightly builds
			} catch (Exception e) {
				System.out.print(e.toString());
			}

		}
	}

	public void viewClosed(String id) {
		loop = false;
	}

	public void buttonPushed(String id) {
		System.out.println("Button pushed on node " + id);
	}

	public void buttonReleased(String id) {
		System.out.println("Button released on node " + id);
	}
}
