/*
 * Copyright (C) 2017 Paul Peacock
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package paulpeacock.sassenrath.interfaces;

import java.io.File;
import java.net.URISyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete implementation of a plugin, provided as a parent class for actual
 * plugins.
 *
 * @author Paul Peacock
 */
public class Plugin extends PluginAbstract {

    public final Logger logger;

    /*
    * Default constructer
    * @param name represents the human-readable name of the plugin
    * @param logLevel one of ERROR > WARN > INFO > DEBUG > TRACE
     */
    public Plugin(String name, String logLevel, File logFile) {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", logLevel);
        System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
        System.setProperty("org.slf4j.simpleLogger.logFile", logFile.toString());
        setName(name);
        logger = LoggerFactory.getLogger(getName());
        logger.info("Logger created.");
        logger.info("Log level set to {}, log path set to {}", logLevel, logFile.toString());
        logger.trace("Trace logging enabled.");
    }

    public Plugin(String name, String logLevel) throws URISyntaxException {
        this(name, logLevel, new File("sassenrath.log"));
    }
    
    public Plugin(String name, File logFile){
        this(name, "INFO", logFile);
    }

    public Plugin(String name) throws URISyntaxException {
        this(name, "INFO");
    }
}
