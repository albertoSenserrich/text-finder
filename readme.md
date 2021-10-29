# Project Title

The exercise is to write a command line driven text search engine, usage being:
java mainClassFile pathToDirectoryContainingTextFiles
This should read all the text files in the given directory, building an in memory representation of the
files and their contents, and then give a command prompt at which interactive searches can be
performed.

## Getting Started

Text files to load must have txt extension


### Execute program


An example session might look like:

$ java ­jar SimpleSearch.jar /foo/bar

14 files read in directory /foo/bar

search>

search> to be or not to be

filename1 : 100%

filename2 : 95%

search>

search> cats

no matches found

search> :quit

$


## Running the tests

In order to run project text use the wolowing comand

mvn clean test


## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

In order to build this project you need to execute the following comand

mvn clean package 

## Development considerations

## Road Map for future improvements
- Delete invalid characters witch separate words (example: ',' , '.' , ':' , ';') if they are not part of any word (example: 'S.L.' , '100.0')
- Detect names on text, if there are two consecutive words witch the first character with chapital letter it must be a name. exm: 'Maria Garcia', 'Maria Gomez') find part of a name cannot be considered a match.
- Sanitice words in order to avoid problems on matchings due to spelling errors. 
- Create interfaces for TextFileLoader and ScreenManager in order to use different kind of input/output sources

## Authors

* **Alberto Senserrich** - albertosenserrich@gmail.com


