package org.eclipse.bug571568;

import java.util.Map;

import org.eclipse.bug571568.service.IExampleService;

public class SlowComponent implements IComponent {

	private IExampleService service;

	public void setExampleService(IExampleService service) throws InterruptedException {
		Thread.sleep(500);
		this.service = service;
	}
	public void activate(Map<String, ?>  properties) throws InterruptedException {
		long start = System.currentTimeMillis();
		int delay = (int) properties.get("delay");
		System.out.println("Starting with delay of " + delay + "ms using " + service);
		Thread.sleep(delay);
		long end = System.currentTimeMillis();
		System.out.println("Component started in: " + (end - start));
	}
}
