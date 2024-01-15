Data sources contain data items.

A data item is anything from which one or more keys can be extracted: 

* Text
* Image
* Graph
* PDF, HTML, Markdown - a graph of text nodes (sections/paragraphs/sentences)
* Formatted text - a tree of text nodes (paragraphs/sentences)
* Source files such as Java - parse tree
* Drawio diagrams - diagram elements may have label text node, tooltip text node/graph, documentation text nodes/graph
* OCR Json
* Ecore models (which includes drawio diagrams)

A data source may be implemented as:

* File system
* Zip archive
* Source repository (Git, GitHub, GitLab). [gitlab4j-api](https://github.com/gitlab4j/gitlab4j-api) and [GitLab model](https://gitlab.models.nasdanika.org/) built on top of it may be used to retrieve items from GitLab.
* (REST) API
* Maven repositories
* Relational databases
* ...

Ecore resources and resource factories may be used as an abstraction layer to represent all types of data sources and data items as Ecore resources in which all elements are identified by URI's:

* URI converters and handlers allow to load from different storage formats. For example, a custom URI handler for ``gitlab`` URI scheme may load from a GitLab server using REST API without cloning repositories, ``maven`` URI handler may load from Maven repositories/archives. 
* Resource factories may be used to load resources differently based on extensions. For example:
    * ``md`` extension would be treated as a Markdown file - convert to HTML and then use HTML loader to convert to some internal implementation. Nasdanika provides resource factories for Drawio diagrams and Excel files.
    * ``html`` and ``htm`` extensions would be handled by an HTML factory which may parse HTML using Jsoup and then structure HTML contents into sections using ``H`` tag hierarchy, then to paragraphs and sentences. It may compute cross-references between files (resources) and parts of the document. These cross-references may be taken into account when computing similarity.
    
Ecore resource set do not provide functionality for iterating over different storage systems, they load resources from URI's using resource factories.

As such, the data source ecosystem would include the following:

* Storage navigators implementing ``Iterable<URI>``[^emf_uri]
* [URI handlers](https://javadoc.io/static/org.eclipse.emf/org.eclipse.emf.ecore/2.33.0/org/eclipse/emf/ecore/resource/URIHandler.html) for URI's which can be loaded as streams (files).
* [Resources](https://javadoc.io/static/org.eclipse.emf/org.eclipse.emf.ecore/2.33.0/org/eclipse/emf/ecore/resource/Resource.html) and resource factories. Depending on a resource type, resources can be loaded from streams delegating to URI handlers to open them. Or they may use custom logic to load a resource. For example, JDBC to load from a relational database.

[^emf_uri]: EMF [URI](https://javadoc.io/static/org.eclipse.emf/org.eclipse.emf.common/2.28.0/org/eclipse/emf/common/util/URI.html)     
    
The data sources ecosystem doesn't have to be RAG-specific - it can be used for other purposes as well.
For example, for [reasoning](https://github.com/Nasdanika-Models/reasoning).
Reasoning and RAG might be combined with RAG/AI rules in which conclusions may be used as prompts/chains of thought. 

## Roadmap

* Create a ``common`` module for common functionality.
* Implement storage navigators for:
    * File system
    * Zip archives
    * Generic REST API - GET to list directories and download resources. Token based authentication.  
* Implement resources and resource factories for:
    * HTML using [Jsoup](https://jsoup.org/ and [HTML model](https://html.models.nasdanika.org/)
    * PDF using [Apache PDFBox](https://pdfbox.apache.org/)
    * Markdown using [MarkdownHelper](https://javadoc.io/doc/org.nasdanika.core/common/latest/org.nasdanika.common/org/nasdanika/common/MarkdownHelper.html)
    * Plain text.
  

Use [String](https://ncore.models.nasdanika.org/references/eClassifiers/String/index.html) for sentences. 
For plain text use [List](https://ncore.models.nasdanika.org/references/eClassifiers/List/index.html) of strings for paragraphs.

Resources shall implement lookup by URI fragment. For example, line/column number for plain text and markdown, CSS path for HTML, TBD for PDF.
