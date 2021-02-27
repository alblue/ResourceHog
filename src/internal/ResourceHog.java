package internal;

import org.eclipse.core.resources.IWorkspace;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component
public class ResourceHog {
	static class ThreadDump extends Exception {
		private static final long serialVersionUID = 1L;

		public ThreadDump() {
		}

		public static void dump() {
			new ThreadDump().printStackTrace();
		}
	}
	private IWorkspace workspace;

	@Reference
	public void setWorkspace(IWorkspace workspace) {
		System.out.println("Setting workspace");
		this.workspace = workspace;
		ThreadDump.dump();
		System.out.println("Set workspace at " + this.workspace);
	}
	
	@Activate
	public void start() throws InterruptedException {
		System.out.println("Preventing startup of Eclipse");
		ThreadDump.dump();
		Thread.sleep(5000);
		System.out.println("Continuing startup of Eclipse");
	}
}
