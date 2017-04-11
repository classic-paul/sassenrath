package paulpeacock.sassenrath.plugina;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import paulpeacock.sassenrath.interfaces.Plugin;

public class Activator implements BundleActivator {
    private ServiceRegistration registration;

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        registration = bundleContext.registerService(
                Plugin.class.getName(),
                new PluginA(),
                null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}