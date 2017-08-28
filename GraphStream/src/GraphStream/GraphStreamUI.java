package GraphStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;

import org.graphstream.algorithm.Toolkit;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.layout.Eades84Layout;
import org.graphstream.ui.layout.HierarchicalLayout;
import org.graphstream.ui.layout.Layout;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;
/*
 * NB: must export the GraphStream libraries (properties->java build path->order and export)
 */
import org.osgi.service.component.annotations.Component;

@Component
public class GraphStreamUI implements ViewerListener {
	protected boolean loop = true;
	protected Viewer viewer;
	protected View view;
	protected Graph graph;
	protected ViewerPipe fromViewer;
	Map<String, double[]> positions = new HashMap<String, double[]>();

	// TODO when implementing, a factory which adds necessary attributes to nodes would be a good idea
	public GraphStreamUI() {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		graph = new SingleGraph("Clicks");
		viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		view = viewer.addDefaultView(false);
		fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);
		
		int l_type = 0;
		
		switch (l_type){
			case 0:
				Layout layout = new HierarchicalLayout();
				viewer.enableAutoLayout(layout);
			case 1:
				layout = new Eades84Layout();
				viewer.enableAutoLayout(layout);
			case 2:
				viewer.enableAutoLayout();
		}
		
		// layout must be configured before adding attributes to graph
		graph.addAttribute("ui.stylesheet", "url('GraphStream/res/css/default.css')");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");

		doTestSetup();

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

	private void doTestSetup(){
		
		Node camera = buildSource("Files on camera");
		Node copy = buildPipe("Copy from camera to disk");
		Node filter = buildPipe("Organise into directories");
		Node music = buildSource("Choose random music");
		Node local = buildSink("Local backup");
		Node cloud = buildSink("Cloud backup");
		Node resize = buildPipe("Resize images");
		Node video = buildPipe("Create video");
		Node watermark = buildPipe("Watermark images");
		Node site = buildSink("Upoad to web site");

		
		connectNodes(copy, filter);
		connectNodes(filter, local);
		connectNodes(filter, cloud);
		connectNodes(filter, resize);
		connectNodes(filter, video);
		connectNodes(resize, watermark);
		connectNodes(watermark, site);
		connectNodes(music, video);
		connectNodes(video, site);
		connectNodes(camera, copy);
	
	}
	@Override
	public void viewClosed(String id) {
		loop = false;
	}
	
	@Override
	public void buttonPushed(String id) {
		positions.put(id, Toolkit.nodePosition(graph, id));
	}

	@Override
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
	private Node buildNode(String label){
		UUID id = UUID.randomUUID();
		Node n = graph.addNode(id.toString());
		n.addAttribute("ui.label", label);
		return n;
	}
	
	private Node buildSource(String label){
		Node n = buildNode(label);
		n.addAttribute("ui.class", "source");
		//n.addAttribute("ui.icon", "GraphStream/res/img/noun_379625_cc[creative stall, non project].png");
		return n;
	}
	private Node buildPipe(String label){
		Node n = buildNode(label);
		n.addAttribute("ui.class", "pipe");
		//n.addAttribute("ui.icon", "GraphStream/res/img/pipe.png");
		return n;
	}
	private Node buildSink(String label){
		Node n = buildNode(label);
		n.addAttribute("ui.class", "sink");
		//n.addAttribute("ui.icon", "GraphStream/res/img/noun_353981_cc[sink drain by Arthur Shlain from the Noun Project].png");
		return n;
	}
	
	private void connectNodes(Node from, Node to){
		graph.addEdge(from.getId()+"->"+to.getId(),from.getId(),to.getId(), true);
	}

}
