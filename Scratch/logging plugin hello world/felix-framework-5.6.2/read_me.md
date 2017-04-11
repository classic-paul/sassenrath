# simple plugin example

In felix-framework-5.6.2 run java -jar \bin\felix.jar

The felix jar will load the jar classes in 'bundle' and attempt to activate them. Interfaces and PluginA will be loaded into the OsGI container. PluginA's activator will be executed. In the process, a logging framework will be used to write lines to a log file.

Once complete, type 'stop 0' into the command window to allow felix to deactivate and close.