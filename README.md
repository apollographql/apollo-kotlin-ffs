[![Maven Central](https://img.shields.io/maven-central/v/com.apollographql.ffs/module?style=flat-square)](https://central.sonatype.com/namespace/com.apollographql.ffs)
[![OSS Snapshots](https://img.shields.io/nexus/s/com.apollographql.ffs/module?server=https%3A%2F%2Fs01.oss.sonatype.org&label=oss-snapshots&style=flat-square)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/apollographql/ffs/)

## apollo-kotlin-ffs

`apollo-kotlin-ffs` is an [Apollo compiler plugin](https://www.apollographql.com/docs/kotlin/advanced/compiler-plugins) that adds Federation Foreign Schemas (FFS) to the Apollo Compiler.

This is useful if you want to use Apollo Kotlin codegen with a schema that contains [federation subgraph directives](https://www.apollographql.com/docs/graphos/reference/federation/subgraph-spec). 

Those schemas are typically meant to be consumed by a server framework and not by a client. Client should use introspection results or the [full schema](https://github.com/graphql/graphql-wg/blob/main/rfcs/FullSchemas.md) after it has been processed by the server framework. 

Sometimes getting such a client schema is cumbersome. `apollo-kotlin-ffs` makes this process easier by adding the federation directives to the Apollo Kotlin compiler. This allows the client to validate the schema and the operations.

> [!NOTE] 
> `apollo-kotlin-ffs` does not do any composition or any query planning. It is merely a tool to help clients validate schemas that are otherwise meant for server consumption.

To use `apollo-kotlin-ffs` in your project, add it as a plugin to your Apollo Kotlin service:

```kotlin
apollo {
  service("service") {
    packageName.set("com.example")

    plugin("com.apollographql.ffs:apollo-kotlin-ffs:0.0.0")
  }
}
```

See https://github.com/apollographql/apollo-kotlin/issues/6221 for more context.

## 📚 Documentation
The Kdoc API reference can be found at: <br/>
[https://apollographql.github.io/apollo-kotlin-ffs/kdoc](https://apollographql.github.io/apollo-kotlin-ffs/kdoc)