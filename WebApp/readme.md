Simple spring MVC project, derived from the intellij tutorial found here:

http://confluence.jetbrains.com/display/IntelliJIDEA/Getting+Started+with+Spring+MVC,+Hibernate+and+JSON

Its slowly evolving to a nice playground for experimenting with jquery, spring security, hibernate, etc.  I've migrated
the db stuff from H2 to postgres, added jquery 1.something, etc.

The two factor auth is done via implementing a spring magical interface, allowing
the use of the spring java config for security authentication/authorization.
Another alternative is to have a /login controller that handles authentication
and upon succesful autheintaction determine if 2factor auth is needed, and if so
hthen redirect to the 2 factor auth page.  You can also use a filter in conjunction
with this to


