Demo of slow loading components
===============================

On a multi-threaded system, it should be the case that we can stand these
examples up in 1s or less.

However, publication of a service triggers DS to activate other components,
setting both their dependency on that service and then subsequently their
activate method.

The components simulate delays with a `Thread.sleep()` which of course is
unrealistic in practice but can be seen when calling either of these methods
involves the loading of many classes.
