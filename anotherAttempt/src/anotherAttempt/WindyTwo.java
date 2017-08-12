package anotherAttempt;

import java.awt.Frame;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.*;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerListener;
import org.graphstream.ui.view.ViewerPipe;

public class WindyTwo implements ViewerListener {
	protected boolean loop = true;
	protected Graph graph;
	protected Viewer viewer;
	protected ViewerPipe fromViewer;
	private View view;

	public WindyTwo() {
		graph = new SingleGraph("Clicks");
		viewer = graph.display();

		// The default action when closing the view is to quit
		// the program.
		viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);

		// We connect back the viewer to the graph,
		// the graph becomes a sink for the viewer.
		// We also install us as a viewer listener to
		// intercept the graphic events.
		fromViewer = viewer.newViewerPipe();
		fromViewer.addViewerListener(this);
		fromViewer.addSink(graph);

		// Then we need a loop to do our work and to wait for events.
		// In this loop we will need to call the
		// pump() method before each use of the graph to copy back events
		// that have already occurred in the viewer thread inside
		// our thread.
		setView(viewer.addDefaultView(false));
		while (loop) {
			try {
				fromViewer.blockingPump();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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

	public Frame getView() {
		return (Frame) view;
	}

	protected void setView(View view) {
		this.view = view;
	}
}
