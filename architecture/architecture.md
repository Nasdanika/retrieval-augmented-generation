Nasdanika RAG is a retrieval augmented generation framework in Java. 
The goal of the framework is to provide Java developers means to assemble RAG designs which match their tasks at hand. 

The framework takes Capability, Architecture/Solution building blocks approach.
Architecture building blocks provide one or more capability, solution building blocks implement one or more architecture building blocks.
The diagram below depicts architecture building blocks. 
For each architecture building block there may be multiple solution building blocks. 
A combination of compatible solution building blocks is called a "design" or "embodiment". 
Solution building blocks for the same architecture building block may be chained and composed to produce new solution building blocks.

```drawio
${representations/drawio/diagram}
```

ddd


```uml

hide footbox

actor User
boundary Requestor
participant queryEngine as "Query Engine"
participant Generator
participant Responder
participant keyExtractor as "Key Extractor"
participant Loader
database dataSources as "Data Sources"
database Store

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

