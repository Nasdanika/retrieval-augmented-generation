Query engine is essentially ``Function<K,Iterable<R>>`` or ``BiFunction<K,ProgressMonitor,Iterable<R>>``
where R provides access to the source URI and possibly similarity and some metadata.

Similarity can be anything including null. There might be a comparator for similarities. For example, cosine similarity comparator.
For vectors take a look at https://github.com/jelmerk/hnswlib / https://mvnrepository.com/artifact/com.github.jelmerk/hnswlib-core-jdk17, take a look at embeddings4j - adapt if possible. 

Hnswlib provides several distance metrics with cosine distance being one of them. 
Hnswlib can also be used for indexing. The index may be serialized. The index can be used as a store - so provide an index store implementation which combines both an index and query engine.

Implementations (solution building blocks to provide):

* Vector
    * Iteration - takes a similarity computer, iterates over the store, sorts. 
    * Index - use Hnswlib, there are two index implementations
    * Vector database, e.g. Milvus
* Digest - exact match, singleton list as a result
* Bag of words - explore what Apache OpenNLP, Smile NLP and WordNet have to offer

    
Default/static methods:

* ``from()`` methods to create query engines from functions and interfaces. E.g. from iterable and similarity function.
* Composition - Binary operator. default and static methods. 
* Predicate to limit. Similarity caching predicate.
* Flat map - expansion. E.g. neighbors of exact match.


TODO: Javadoc links to modules once published

Excecutor to query engine? Or parallel flag? Iterable -> stream -> parallel



