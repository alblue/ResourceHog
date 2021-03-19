package org.eclipse.bug571568.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class StartupActivator implements BundleActivator {

	static class ExampleService implements IExampleService {
		
	}
	@Override
	public void start(BundleContext context) throws Exception {
		long start = System.currentTimeMillis();
		context.registerService(IExampleService.class, new ExampleService(), null);
		long end = System.currentTimeMillis();
		System.out.println("Activator started in: " + (end-start));
	}

	@Override
	public void stop(BundleContext context) throws Exception {
	}

}
