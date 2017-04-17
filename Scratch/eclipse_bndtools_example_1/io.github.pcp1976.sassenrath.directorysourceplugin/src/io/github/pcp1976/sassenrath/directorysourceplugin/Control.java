package io.github.pcp1976.sassenrath.directorysourceplugin;

import java.awt.Window;
import java.nio.file.Path;
import org.osgi.service.component.annotations.*;
import io.github.pcp1976.sassenrath.api.Plugin;
import io.github.pcp1976.sassenrath.api.PluginState;

@Component
public class Control implements Plugin {

	private View view;
	private Model model;

	@Override
	public String getName() {
		return "Directory source";
	}

	@Override
	public void configure() {
		this.getView().setVisible(true);
	}

	private Window getView() {
		return this.view;
	}

	@Override
	public void enqueue(Path file, Plugin plugin) {
		throw new UnsupportedOperationException("This component does not accept input from other components.");
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}

	@Override
	public PluginState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerClient(Plugin plugin) {
		// TODO Auto-generated method stub

	}

	public Path getDirectory() {
		return model.getDirectory();
	}

	public void setDirectory(Path directory) {
		model.setDirectory(directory);
	}

	@Override
	public Plugin getNewPlugin() {
		Control p = new Control();
		p.setModel(new Model());
		p.setView(new View());
		return p;
	}

	private void setModel(Model model) {
		this.model = model;
		
	}

	private void setView(View view) {
		this.view = view;
	}
	
}
