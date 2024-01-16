Stores associations between keys and data items.

Use an Ecore model for store.
Implement storage by reference - use URI's to find source data items in the resource set.
Provide implementations for:

* Vectors
* Digests - may be used for automated validation of embedding stores - digest based query shall always be a subset of a corresponding vector query.

Storage as:

* XMI - for inspection
* Compressed binary - for compact storage

Delivery vehicles:

* Local file
* Maven resource
* HTTP(S) resource
