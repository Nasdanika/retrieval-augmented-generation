Extracts key from input. For example, generates an embedding vector.

## Flavors

TODO

## Implementations

### OpenAIEmbeddingsKeyExtractor

TODO

## Roadmap

* Caching key extractor filter for String -> double vectors. Use the same store type. Save options - on every modification, on a regular interval, on close. Storage formats - xmi, binary, compressed binary. 
* Digest - MD5 and SHA in the ``core`` module
* Open source models like Llama 2, MPT-7B, ...
* Generic REST API in the ``core`` module
* Bag of words, word2vec and other things provided by [Apache OpenNLP](https://opennlp.apache.org/) and [Smile NLP](https://haifengl.github.io/nlp.html) possibly in combination with [WordNet](https://wordnet.princeton.edu/)