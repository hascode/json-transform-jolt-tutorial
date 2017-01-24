# Jolt JSON Transformation Examples

Examples for JSON manipulation using [Jolt] and in different scenarios like programmatic manipulation, transforming the result from a RESTful webservice (JAX-RS) using a servlet filter and finally using [Apache Camel].

## Running the Examples

## JAX-RS RESTful Webservices with embedded Jetty


```
$ mvn clean compile exec:java -Dexec.mainClass=com.hascode.tutorial.RestServer
```

## Programmatic transformation from a JSON file


```
$ mvn clean compile exec:java -Dexec.mainClass=com.hascode.tutorial.CustomJsonTransformer
```


## More
For more of my experiments please feel free to visit my blog at [www.hascode.com] or to have a look at my [project repositories].

----

**2017 Micha Kops / hasCode.com**

  [www.hascode.com]:http://www.hascode.com/
  [project repositories]:https://bitbucket.org/hascode/
  [Apache Camel]:http://camel.apache.org/
  [Jolt]:https://github.com/bazaarvoice/jolt