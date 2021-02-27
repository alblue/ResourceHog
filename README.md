ResourceHog
===========

This Eclipse plug-in demonstrates how the serial nature of creating DS
components from resources registered in an Activator, and how it can block the
start-up of Eclipse by doing a lot of dependent work in the component's `start`
method.

If DS were able to provide the setup call asynchronously, then there would be
no delay.

Alternate fixes
---------------

One alternative fix would be for the Resources plug-in to use a separate thread
to register the startup of the IWorkspace, so that clients using a DS service
would not impact the main thread. This would be a less than ideal fix:

* It would have to use a Thread, not a Job, because that may introduce circular
  dependencies
* Any potential service registered in an Activator would be subject to the same
  concerns when creating a DS component that is activating on their loading

Another approach is to use a Thread/Job in the dependent plug-in for consumption
of the service asynchronously. This is less than ideal:

* The classes referred to by the Thread/Job would still need to be loaded,
  which would mean the loaded classes trigger other activators
* It pushes the users of any DS components to have to implement the threading 

Usage
-----

Clone and build this plug-in, then run an Eclipse application which includes
this plug-in with the content.

There will be a delay at start-up; as soon as the Resources plug-in loads and
its activator runs, it will register the `IWorkspace` service:

https://github.com/eclipse/eclipse.platform.resources/blob/434556bcae52a8bc1d9476d2574cc65821f22841/bundles/org.eclipse.core.resources/src/org/eclipse/core/resources/ResourcesPlugin.java#L492

As soon as the Resources plug-in registers the workspace, it will immediately
activate the `ResourceHog` plug-in, which will simulate work by sleeping for
5s.

Although this is a contrived example, it shows that a DS component, that
depends on the `IWorkspace`, can seriously delay the start-up of Eclipse.
