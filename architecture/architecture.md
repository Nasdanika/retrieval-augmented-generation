Nasdanika RAG is a retrieval augmented generation framework in Java. 
The goal of the framework is to provide Java developers means to assemble RAG designs which match their tasks at hand. 

The framework takes Capability, Architecture/Solution building blocks approach.
Architecture building blocks provide one or more capability, solution building blocks implement one or more architecture building blocks.

```drawio
${representations/drawio/diagram}
```

The diagram above depicts the architecture building blocks. 
The below diagram shows how the building blocks interact during loading and retrieval/generation phases.

```uml

hide footbox

actor User [[r0/user/index.html]]
boundary Requestor [[r0/requestor/index.html]]
participant queryEngine as "Query Engine" [[r0/query-engine/index.html]]
participant Generator [[r0/generator/index.html]]
participant Responder [[r0/responder/index.html]]
participant keyExtractor as "Key Extractor" [[r0/key-extractor/index.html]]
participant Loader [[r0/loader/index.html]]
database dataSources as "Data Sources" [[r0/data-sources/index.html]]
database Store [[r0/store/index.html]]

group Load
  Loader -> dataSources : read
  Loader -> keyExtractor : generate key
  Loader -> Store : store
end

group Retrieve & Generate
  User -> Requestor : question
  Requestor -> queryEngine : query
  queryEngine -> keyExtractor : generate query key
  queryEngine -> Store : get matches
  queryEngine -> queryEngine : sort by similarity
  queryEngine --> Requestor : sorted store entries
  Requestor -> Generator : question and query results
  Generator --> Requestor : summary
  Requestor -> Responder : question, query results, summary
  Responder --> User : answer
end
```

For each architecture building block there may be multiple solution building blocks. 
A combination of compatible solution building blocks is called a "design" or "embodiment". 
Solution building blocks for the same architecture building block may be chained and composed to produce new solution building blocks.

The following sections provide brief overviews of the architecture building blocks. 
Click on the diagram elements or use the left navigation or links to navigate to the blocks' pages with more detailed information and a list of solution building blocks.

## Data Sources

Sources of data. There might be multiple solution building blocks with two primary design dimensions:

* Data item format - PDF, HTML, JSON, diagram, Ecore model, PNG, JPEG ...
* Data storage interface - file system, zip archive, (REST) API, ...

Data items can be converted from one format to another. 
As such data sources can be chained and composed. 

## Loader

Loads data items keys and some data item identifier/locator to the store to make them discoverable by the query engine.

## Store

Contains associations between data item keys and data items. 
Data items can be stored by value or by reference. 
The store can be indexed.

A vector database is an example of an indexed store of embedding vectors.

## Key Extractor

Takes a data item of a fragment of it (say a paragraph from a text document) and computes a key. 
The key is something that identifies the data item. 
Some key types may support similarity computation.  

Examples of keys:

* Text:
    * MD5/SHA digest - exact matches
    * Vector embeddings - cosine similarity
    * Bags of words - overlap similarity
* Person
    * Postal address - distance similarity
    * DNA - shared DNA
    * Marital and social networks relationships - distance    

## Query Engine

Computes query key and retrieves matching items from the store sorted by similarity.

## Requestor

Takes a request, passes to the query engine. 
Then uses the request and the query results to formulate a request to the generator. 
For example, if the generator is an LLM, it would create prompts or chains of thought from the query results.
Then it passes the request, the query results, and the response from the generator to the responder.

## Generator

Creates a summary response from a request and query results. 

This component might not be present in some embodiments. E.g. in the case of Semantic Search the Responder will generate a response.

## Responder

Takes the request, query results, generator response and creates a response for the user.

## User

A user (client) of a RAG solution - a human or a system.