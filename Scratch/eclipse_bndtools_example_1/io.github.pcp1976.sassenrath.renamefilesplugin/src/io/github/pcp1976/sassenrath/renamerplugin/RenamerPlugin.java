package io.github.pcp1976.sassenrath.renamerplugin;

import java.nio.file.Path;

import org.osgi.service.component.annotations.*;

import io.github.pcp1976.sassenrath.api.Plugin;
import io.github.pcp1976.sassenrath.api.PluginState;

@Component
public class RenamerPlugin implements Plugin {

	@Override
	public String getName() {
		return "File renamer";
	}

	@Override
	public void configure() {
		// TODO write configure
		throw new UnsupportedOperationException();

	}

	@Override
	public void enqueue(Path file, Plugin plugin) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public PluginState getState() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
		//return null;
	}

	@Override
	public void registerClient(Plugin plugin) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Plugin getNewPlugin() {
		// TODO Auto-generated method stub
		return null;
	}

}
