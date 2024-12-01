var searchDocuments = {"glossary.html":{"link-uuid":"4dbabba3-0383-46ec-9de9-7a81b4e06b42","title":"Glossary","content":"Clear Identifier(s) Hide UUID {{data.value.name}} {{data.value[0].value}} {{item.value}}"},"model/glossary.html":{"link-uuid":"29f61fb2-2a6b-40fa-ac48-4f37319b1a56","title":"Glossary","content":"Clear Identifier(s) Hide UUID {{data.value.name}} {{data.value[0].value}} {{item.value}}"},"r0/responder/index.html":{"link-uuid":"85d43cf3-c038-4d9f-aeab-fde1375ab7a6","title":"Responder","content":"Generates a response from: Request Query results Generator&rsquo;s response Similar to the requestor and loader - a generic (functional) interface, inheritance hierarchy, concrete implementations as classes and utility methods: Plain text JSON or some other structure, say response model Markdown/HTML - summary, footnotes, sources."},"model/references/eClassifiers/FloatVectorStringItem/inheritance.html":{"path":"FloatVectorStringItem/Inheritance","link-uuid":"8677d48e-7f81-4049-ab2d-cc4d073cd926","title":"Inheritance","content":"Supertypes "},"r0/loader/index.html":{"link-uuid":"f1f486d5-a264-4a9a-a000-1e1c4bfc4bc3","title":"Loader","content":"Iterates over the data sources and passes inputs to the item builder. Loader can perform reading/addition in multiple threads using an executor. It may also break down large objects (e.g. PDF documents) into smaller objects - sections, paragraphs, sentences."},"model/references/eClassifiers/DoubleVectorStringItem/references/eStructuralFeatures/vector/index.html":{"path":"DoubleVectorStringItem/Attributes/vector","link-uuid":"d7b6b50d-3258-4476-b577-cb47f1a924bb","title":"vector","content":"Vector elements"},"model/references/eClassifiers/DoubleVectorStringItem/index.html":{"link-uuid":"009f14ab-c892-4a51-8872-0253ac899229","title":"DoubleVectorStringItem","content":"DoubleVectorStringStore item - a mapping of String id to a double vector."},"r0/repository/rh/item-builder/index.html":{"path":"Repository/Item Builder","link-uuid":"d0daca9f-1985-4f9c-ba73-f9a498f66807","title":"Item Builder","content":"Builds store items from input. May build multiple items from a single input. E.g. multiple paragraph items for a document. May perform operations in parallel using an executor."},"r0/generator/index.html":{"link-uuid":"30116363-acb1-4547-94bd-8699511485e3","title":"Generator","content":"Implementations: OpenAI summarization with prompts and chains of thought using the open-ai module using Azure OpenAI Java API - Azure OpenAI client library for Java | Microsoft Learn, Maven Central Generic REST API in the core module Open source models like Llama 2, MPT-7B, &hellip;"},"model/references/eClassifiers/FloatVectorStringItem/index.html":{"link-uuid":"c4b59603-98cd-4240-8cf7-4e9b4b6956d6","title":"FloatVectorStringItem","content":"FloatVectorStringStore item - a mapping of String id to a float vector."},"r0/data-sources/index.html":{"link-uuid":"5d7d225c-3892-4c26-ba3e-077e436aa774","title":"Data Sources","content":"Data sources contain data items. A data item is anything from which one or more keys can be extracted: Text Image Graph PDF, HTML, Markdown - a graph of text nodes (sections/paragraphs/sentences) Formatted text - a tree of text nodes (paragraphs/sentences) Source files such as Java - parse tree Drawio diagrams - diagram elements may have label text node, tooltip text node/graph, documentation text nodes/graph OCR Json Ecore models (which includes drawio diagrams) A data source may be implemented as: File system Zip archive Source repository (Git, GitHub, GitLab). gitlab4j-api and GitLab model built on top of it may be used to retrieve items from GitLab. (REST) API Maven repositories Relational databases &hellip; Ecore resources and resource factories may be used as an abstraction layer to represent all types of data sources and data items as Ecore resources in which all elements are identified by URI&rsquo;s: URI converters and handlers allow to load from different storage formats. For example, a custom URI handler for gitlab URI scheme may load from a GitLab server using REST API without cloning repositories, maven URI handler may load from Maven repositories/archives. Resource factories may be used to load resources differently based on extensions. For example: md extension would be treated as a Markdown file - convert to HTML and then use HTML loader to convert to some internal implementation. Nasdanika provides resource factories for Drawio diagrams and Excel files. html and htm extensions would be handled by an HTML factory which may parse HTML using Jsoup and then structure HTML contents into sections using H tag hierarchy, then to paragraphs and sentences. It may compute cross-references between files (resources) and parts of the document. These cross-references may be taken into account when computing similarity. Ecore resource set do not provide functionality for iterating over different storage systems, they load resources from URI&rsquo;s using resource factories. As such, the data source ecosystem would include the following: Storage navigators implementing Iterable&lt;URI&gt;1 URI handlers for URI&rsquo;s which can be loaded as streams (files). Resources and resource factories. Depending on a resource type, resources can be loaded from streams delegating to URI handlers to open them. Or they may use custom logic to load a resource. For example, JDBC to load from a relational database. The data sources ecosystem doesn&rsquo;t have to be RAG-specific - it can be used for other purposes as well. For example, for reasoning. Reasoning and RAG might be combined with RAG/AI rules in which conclusions may be used as prompts/chains of thought. Roadmap Create a common module for common functionality. Implement storage navigators for: File system Zip archives Generic REST API - GET to list directories and download resources. Token based authentication. Implement resources and resource factories for: HTML using [Jsoup](https://jsoup.org/ and HTML model PDF using Apache PDFBox Markdown using MarkdownHelper Plain text. Use String for sentences. For plain text use List of strings for paragraphs. Resources shall implement lookup by URI fragment. For example, line/column number for plain text and markdown, CSS path for HTML, TBD for PDF. EMF URI ↩"},"r0/repository/rh/retriever/index.html":{"path":"Repository/Retriever","link-uuid":"024bb3fe-5e2d-4824-b838-33fca383a5f3","title":"Retriever","content":"Retriever is essentially Function&lt;K,Iterable&lt;R&gt;&gt; or BiFunction&lt;K,ProgressMonitor,Iterable&lt;R&gt;&gt; where R provides access to the source identifier (URI) and possibly similarity and some metadata. Similarity can be anything including null. There might be a comparator for similarities. For example, cosine similarity comparator. Similarity is a double between 0 and 1 with 1 meaning that two items are identical and 0 that there are not similar at all. Another metric is distance, which is a non-negative number. Similarity may be defined as 1 / (1 + distance) - if distance is 0 then similarity is 1. If distance is large, similarity is small. For vectors take a look at https://github.com/jelmerk/hnswlib / https://mvnrepository.com/artifact/com.github.jelmerk/hnswlib-core-jdk17, take a look at embeddings4j - adapt if possible. Hnswlib provides several distance metrics with cosine distance being one of them. Hnswlib can also be used for indexing. The index may be serialized. The index can be used as a store - so provide an index store implementation which combines both an index and query engine. Implementations (solution building blocks to provide): Vector Iteration - takes a similarity computer, iterates over the store, sorts. Index - use Hnswlib, there are two index implementations Vector database, e.g. Milvus Digest - exact match, singleton list as a result Bag of words - explore what Apache OpenNLP, Smile NLP and WordNet have to offer Default/static methods: from() methods to create query engines from functions and interfaces. E.g. from iterable and similarity function. Composition - Binary operator. default and static methods. Predicate to limit. Similarity caching predicate. Flat map - expansion. E.g. neighbors of exact match. TODO: Javadoc links to modules once published Excecutor to query engine? Or parallel flag? Iterable -&gt; stream -&gt; parallel Perhaps implement a caching retriever filter"},"r0/requestor/index.html":{"link-uuid":"3d3a4686-9342-4105-b47b-b1df3b12877c","title":"Requestor","content":"Takes a request and orchestrates other components. Implement similar to Loader - a generic (functional) interface with an inheritance hierarchy which progressively binds generic parameters. Concrete implementations as classes or utility methods."},"model/references/eClassifiers/DoubleVectorStringStore/references/eStructuralFeatures/items/index.html":{"path":"DoubleVectorStringStore/References/items","link-uuid":"fae93e46-aa05-4761-b29e-6bd6e8f06186","title":"items","content":"Store items"},"r0/repository/rh/key-extractor/index.html":{"path":"Repository/Key Extractor","link-uuid":"bbc4f5d7-5e7c-4a3f-ad54-6c43c4190912","title":"Key Extractor","content":"Extracts key from input. For example, generates an embedding vector. Flavors TODO Implementations OpenAIEmbeddingsKeyExtractor TODO Roadmap Caching key extractor filter for String -&gt; double vectors. Use the same store type. Save options - on every modification, on a regular interval, on close. Storage formats - xmi, binary, compressed binary. Digest - MD5 and SHA in the core module Open source models like Llama 2, MPT-7B, &hellip; Generic REST API in the core module Bag of words, word2vec and other things provided by Apache OpenNLP and Smile NLP possibly in combination with WordNet"},"model/references/eClassifiers/DoubleVectorStringItem/inheritance.html":{"path":"DoubleVectorStringItem/Inheritance","link-uuid":"e4afa2c2-c667-4d8a-9a2f-193b4df649c9","title":"Inheritance","content":"Supertypes "},"index.html":{"link-uuid":"0c4ed3d8-96a8-44c4-b047-12dcfa5916a1","title":"Retrieval Augmented Generation","content":"Nasdanika RAG is a retrieval augmented generation framework in Java. The goal of the framework is to provide Java developers means to assemble RAG designs which match their tasks at hand. The framework takes Capability, Architecture/Solution building blocks approach. Architecture building blocks provide one or more capability, solution building blocks implement one or more architecture building blocks. One of definitions of software development is &ldquo;the process of incremental binding of decisions to make them executable&rdquo;. This framework follows this paradigm - it defines high-level generic abstractions then progressively binds generic parameters and then concrete implementations. The diagram above depicts the architecture building blocks. The below diagram shows how the building blocks interact during loading and retrieval/generation phases. hide footbox database dataSources as &quot;Data Sources&quot; [[r0/data-sources/index.html]] participant Loader [[r0/loader/index.html]] actor User [[r0/user/index.html]] boundary Requestor [[r0/requestor/index.html]] box &quot;Repository&quot; #LightBlue participant itemBuilder as &quot;Item Builder&quot; [[r0/repository/rh/item-builder/index.html]] participant keyExtractor as &quot;Key Extractor&quot; [[r0/repository/rh/key-extractor/index.html]] participant Retriever [[r0/repository/rh/retriever/index.html]] database Store [[r0/repository/rh/store/index.html]] end box participant Generator [[r0/generator/index.html]] participant Responder [[r0/responder/index.html]] group Load Loader -&gt; dataSources : read Loader -&gt; itemBuilder : add itemBuilder -&gt; keyExtractor : generate key itemBuilder -&gt; Store : store end group Retrieve &amp; Generate User -&gt; Requestor : question Requestor -&gt; Retriever : query Retriever -&gt; keyExtractor : generate query key Retriever -&gt; Store : get matches Retriever -&gt; Retriever : sort by similarity Retriever --&gt; Requestor : sorted store entries Requestor -&gt; Generator : question and query results Generator --&gt; Requestor : summary Requestor -&gt; Responder : question, query results, summary Responder --&gt; User : answer end For each architecture building block there may be multiple solution building blocks. A combination of compatible solution building blocks is called a &ldquo;design&rdquo; or &ldquo;embodiment&rdquo;. Solution building blocks for the same architecture building block may be chained and composed to produce new solution building blocks. The following sections provide brief overviews of the architecture building blocks. Click on the diagram elements or use the left navigation or links to navigate to the blocks&rsquo; pages with more detailed information and a list of solution building blocks. Data Sources Sources of data. There might be multiple solution building blocks with two primary design dimensions: Data item format - PDF, HTML, JSON, diagram, Ecore model, PNG, JPEG &hellip; Data storage interface - file system, zip archive, (REST) API, &hellip; Data items can be converted from one format to another. As such data sources can be chained and composed. Loader Reads data items from data sources and adds them to the repository Repository Item Builder Creates store items from data items. For example, breaks down a document into sections and paragraphs. Computes embeddings (keys) and some item identifier/locator and adds items the store to make them discoverable by the retriever. Key Extractor Takes a data item of a fragment of it (say a paragraph from a text document) and computes a key. The key is something that identifies the data item. Some key types may support similarity computation. Examples of keys: Text: MD5/SHA digest - exact matches Vector embeddings - cosine similarity Bags of words - overlap similarity Person Postal address - distance similarity DNA - shared DNA Marital and social networks relationships - distance Store Contains associations between item keys (e.g. vector embeddings) and item identifiers. Data items can be stored by value or by reference. The store can be indexed. Retriever Computes query key and retrieves matching items from the store sorted by similarity. Requestor Takes a request, passes to the query engine. Then uses the request and the query results to formulate a request to the generator. For example, if the generator is an LLM, it would create prompts or chains of thought from the query results. Then it passes the request, the query results, and the response from the generator to the responder. Generator Creates a summary response from a request and query results. This component might not be present in some embodiments. E.g. in the case of Semantic Search the Responder will generate a response. Responder Takes the request, query results, generator response and creates a response for the user. User A user (client) of a RAG solution - a human or a system. Resources Model documentation Roadmap Java Core module Ecore model Processors - ecore, doc, factories Drawio mapping Eclipse tree editor Eclipse Sirius designer AI models - Claude antroipic, Orca-2, &hellip; Caching implementations for query engine, key extractor, and generator: Memory-sensitive cache based on Apache Commons Pool Hazelcast based cache. Can be used in conjunction with Hazecast Docker Image Other implementations as needed, e.g. RDBMS -&gt; H2, &hellip; Async/reactive implementation User feedback collection - ranking of answers, providing the right answer (and then use similarity to choose the best of available) Benchmarking User A user (client) of a RAG solution - a human or a system Data Sources Sources of data items - HTML pages, PDF files, databases, OCR JSON's, diagrams, ... Requestor Takes a request, passes to the query engine. Then uses the request and the query results to formulate a request to the generator. For example, if the generator is an LLM, it would create prompts or chains of thought from the query results. Then it passes the request, the query results, and the response from the generator to the responder. Responder Takes the request, query results, generator response and creates a response for the user. Generator Creates a summary response from a request and query results. This component might not be present in some embodiments. E.g. in the case of Semantic Search the Responder will generate a response. Loader Loads data items from data sources to a store for subsequent retrieval. May store by value or by references. May create multiple store entries per data item. For example, it may break down a PDF/HTML documents into sections and then into paragraphs and create entries per paragraph. May also index entries by the key. Key Extractor Computes store/retrieval key. For example, a vector embedding. Key may have a measure of similarity. For example, cosine similarity for vectors Retriever Takes a request and uses the key extractor to compute a request key. Then uses the key to retrieve matching items from the store. If the key type supports similarity computation, then orders results by similarity. May index store entries for faster retrieval. Store Item Builder Builds a store item from input data and extracted key. Repository Storage and retrieval of information for augmented generation Retrieval Augmented Generation"},"model/references/eClassifiers/FloatVectorStringStore/references/eStructuralFeatures/items/index.html":{"path":"FloatVectorStringStore/References/items","link-uuid":"2f23ab4c-706b-405a-9520-5d7bb840503a","title":"items","content":"Store items"},"model/references/eClassifiers/FloatVectorStringStore/index.html":{"link-uuid":"158a9adc-784c-4eb3-b351-85801c63d39d","title":"FloatVectorStringStore","content":"Stores float vectors identified by strrings (URI&rsquo;s)"},"model/search.html":{"link-uuid":"eb887d57-263c-4234-bdb1-fa62647607b7","title":"Search","content":"Clear"},"r0/repository/rh/store/index.html":{"path":"Repository/Store","link-uuid":"489d6e40-fa92-4319-bef2-d641c74af4f1","title":"Store","content":"Stores associations between keys and data items. Use an Ecore model for store. Implement storage by reference - use URI&rsquo;s to find source data items in the resource set. Provide implementations for: Vectors Digests - may be used for automated validation of embedding stores - digest based query shall always be a subset of a corresponding vector query. Storage as: XMI - for inspection Compressed binary - for compact storage Delivery vehicles: Local file Maven resource HTTP(S) resource"},"model/references/eClassifiers/DoubleVectorStringStore/index.html":{"link-uuid":"e9f26848-8ad9-468b-b469-f5db670b6d0a","title":"DoubleVectorStringStore","content":"Stores double vectors identified by strrings (URI&rsquo;s)"},"model/references/eClassifiers/FloatVectorStringItem/references/eStructuralFeatures/vector/index.html":{"path":"FloatVectorStringItem/Attributes/vector","link-uuid":"83b574b5-0eaa-452f-977a-e1a7418b5d7a","title":"vector","content":"Vector elements"},"r0/repository/index.html":{"link-uuid":"02c74d45-f150-4895-adb6-36979a7233b0","title":"Repository","content":"A grouping of components responsible for storage and retrieval: Item Builder Key Extractor Store Retriever Provide an implementation assembled from components and an implementations for a few vector databases: Milvus - it has vendor-provided Java SDK and a docker image provided by VMware"}}