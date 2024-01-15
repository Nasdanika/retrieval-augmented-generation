Iterates over the data source, uses key extractor to compute a key, stores to the store.
The loader may perform a transformation of the data store format to an internal URI -> S format where S is the argument type for the key extractors. 
For example, String for text embeddings. 

Implement as a generic (functional) interface with an inheritance hierarchy progressively binding generic parameters. 
Concrete implementations may be provided as static utility methods.