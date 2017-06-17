package io.github.pcp1976.sassenrath.util;

import io.github.pcp1976.sassenrath.api.extend.Plugin;
import io.github.pcp1976.sassenrath.api.extend.Behaviour;

public class ConcretePluginFactory<B extends Behaviour> {
	final Class<B> behaviourClass;
	final String name;

	public ConcretePluginFactory(Class<B> behaviousClass, String name) {
		this.name = name;
		this.behaviourClass = behaviousClass;
	}

	public Plugin create() throws InstantiationException, IllegalAccessException {
		Plugin p = new PipeHost(name, behaviourClass.newInstance());
		p.setName(name);
		return p;
	}

	public static <B extends Behaviour> ConcretePluginFactory<B> createFactory(final Class<B> behaviourClass, String name) {
		return new ConcretePluginFactory<B>(behaviourClass, name);
	}
	
	/*
	static public Plugin temp(){
		Plugin p = null;
		try{
			createFactory(Empty.class, "Null").create();
		}catch(Exception e){}
		return p;
	}
	*/
}

/*
 * from http://stackoverflow.com/questions/6093363/generic-factory-with-unknown-
 * implementation-classes
 * public class FruitHandlerFactory<H extends
 * FruitHandler<F>, F extends Fruit> { final Class<H> handlerClass; final
 * Class<F> fruitClass;
 * 
 * public FruitHandlerFactory(final Class<H> handlerClass, final Class<F>
 * fruitClass) { this.handlerClass = handlerClass; this.fruitClass = fruitClass;
 * }
 * 
 * public H create() throws InstantiationException, IllegalAccessException { H
 * handler = handlerClass.newInstance();
 * handler.setFruit(fruitClass.newInstance()); return handler; } }
 * 
 * static <H extends FruitHandler<F>, F extends Fruit> FruitHandlerFactory<H, F>
 * createFactory( final Class<H> handlerClass, final Class<F> fruitClass) {
 * return new FruitHandlerFactory<H, F>(handlerClass, fruitClass); }
 * 
 * usage:
 * FruitHandlerFactory fhf = FruitHandlerFactory.createFactory(OrangeHandler.class, Orange.class);
 */