# Does Java Support Super-Delegation?

In class, a student asked if we could use the ``super`` keyword to reference a member of the superclass of our superclass (the grand-superclass) if the member wasn't part of the superclass.

This code demonstrates the answer by delegating so super(80), where the superclass has no constructor with a single integer parameter but its superclass (the grand-superclass) does have a matching parameter.

Run ``ant`` to see the answer. Did you get an error, or does the code work?
