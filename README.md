# Retrieval Augmented Generation (RAG)

RAG in Java - abstractions and concrete implementations.
The goal is provide a framework for producing multiple RAG embodiments for different tasks. 

For example, a RAG embodiment for a body of technical documentation may not need a vector database to store embeddings and maybe doesn't even need vector embeddings - some other similarity techniques may work just fine and 
be more attractive quality attributes wise - cheaper, more secure, faster, ...


* Source - anything that can be embedded - text, graph, image, ...
* Embedding - something derived from source. Vector embedding is one option of many. A bag of words is another. Can be the source itself in some baseline (NOP) implementations - such implementations may be used to compare other implementations against.
* Embedder - a BiFunction - takes source and progress monitor, returns embedding.
* Source key - something identifying the source to store in the embedding store. Also can be the source itself for baseline (NOP) implementations.
* Similarity - something indicating how one embedding is similar to another. For example, for vectors it might be a double representing cosine similarity.
* Similarity comparator - compares similarities. For cosine similarity it would reverse natural order comparison. For a bag of words it would be an an overlap of two bags possibly taking synonyms and word forms into account.
* Embedding store - stores key/embedding pairs. No querying. For example, stores to a file system. 
* Embedding Query Engine - performs search by a query embedding and returns an iterator/stream/generator of keys and similarities. Backed by a store (some form of key/embedding source - iterable, stream, ...).
* Source store - stores sources. Performs search by a query source and returns an iterator/stream/generator of sources and similarities. 
* Aggregator - takes a query and search results and produces a response. For example, search results are used to generate prompts for an LLM.


## Implementations

Initial focus on wrappping Azure Open AI for embedding and aggregation.

### Source

* Plain text
* Text graph node
* HTML - break into paragraphs and create a graph of text nodes using Jsoup
* Markdown - convert to HTML and then to text graph nodes. May take into account diagram fenced blocks and treat them differently
* Drawio diagrams - diagram elements may have label text node, tooltip text node/graph, documentation text nodes/graph
* OCR Json
* Ecore models (which includes drawio diagrams)
* Directories of the above

### Embedding/Embedder

* Vector 
    * Azure OpenAI Java API - [Azure OpenAI client library for Java | Microsoft Learn](https://learn.microsoft.com/en-us/java/api/overview/azure/ai-openai-readme?view=azure-java-preview), [Maven Central](https://mvnrepository.com/artifact/com.azure/azure-ai-openai)
    * Open source models like Llama 2, MPT-7B, ...
* NOP
* Bag of words, ... - Apache OpenNLP, maybe Smile

### Source key

* NOP - source itself
* GUID
* URI

### Similarity/Similarity comparator

* For vectors - cosine similarity, reverse natural order comparator
* For bags of words - overlap with stemming, synonyms etc. Apache OpenNLP, Smile, WordNet

### Embedding store/query engine

* NOP - stores sources as-is in an in-memory collection and uses similarity function for querying - (parallel) stream
* Vector
    * Native - data in memory with optional storage somewhere
        * Brute force - (parallel) stream, compute similarity, sort
        * Indexed - use https://github.com/jelmerk/hnswlib / https://mvnrepository.com/artifact/com.github.jelmerk/hnswlib-core-jdk17, take a look at embeddings4j - adapt if possible
    * Vector DB adapters. E.g. Milvus (https://milvus.io/, https://milvus.io/docs/install-java.md)
* https://opensearch.org/

### Aggregator

* NOP - just reports results like a search engine would. May be used as is and for validation of other aggregators
* LLM - Azure OpenAI Java API. May report query results - used in response generation and a few additional.







