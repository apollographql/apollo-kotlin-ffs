package com.apollographql.ffs

import com.apollographql.apollo.annotations.ApolloExperimental
import com.apollographql.apollo.ast.ForeignSchema
import com.apollographql.apollo.ast.toGQLDocument
import com.apollographql.apollo.compiler.ApolloCompilerPlugin
import com.apollographql.apollo.compiler.ApolloCompilerPluginEnvironment
import com.apollographql.apollo.compiler.ApolloCompilerRegistry

@ApolloExperimental
class FfsCompilerPlugin: ApolloCompilerPlugin {

  override fun beforeCompilationStep(environment: ApolloCompilerPluginEnvironment, registry: ApolloCompilerRegistry) {
    registry.registerForeignSchemas(ffsSchemas())
  }

  private fun ffsSchemas(): List<ForeignSchema> {
    val definitions = federationDefinitions_2_9.toGQLDocument().definitions

    return listOf(
      /**
       * See https://github.com/apollographql/apollo-kotlin/issues/6221.
       * Apollo Kotlin doesn't use the federation directives but adding them allows validating the schema.
       */
      ForeignSchema("federation", "v2.0", definitions, emptyList()),
      ForeignSchema("federation", "v2.1", definitions, emptyList()),
      ForeignSchema("federation", "v2.2", definitions, emptyList()),
      ForeignSchema("federation", "v2.3", definitions, emptyList()),
      ForeignSchema("federation", "v2.4", definitions, emptyList()),
      ForeignSchema("federation", "v2.5", definitions, emptyList()),
      ForeignSchema("federation", "v2.6", definitions, emptyList()),
      ForeignSchema("federation", "v2.7", definitions, emptyList()),
      ForeignSchema("federation", "v2.8", definitions, emptyList()),
      ForeignSchema("federation", "v2.9", definitions, emptyList()),
    )
  }
}

/**
 * Taken from https://www.apollographql.com/docs/graphos/reference/federation/subgraph-spec
 * with _Entity, _Any, _Service, Query._service and Query._entities removed.
 */
internal val federationDefinitions_2_9 = """
  scalar FieldSet
  scalar link__Import
  scalar federation__ContextFieldValue
  scalar federation__Scope
  scalar federation__Policy

  enum link__Purpose {
    ""${'"'}
    `SECURITY` features provide metadata necessary to securely resolve fields.
    ""${'"'}
    SECURITY

    ""${'"'}
    `EXECUTION` features provide metadata necessary for operation execution.
    ""${'"'}
    EXECUTION
  }

  directive @external on FIELD_DEFINITION | OBJECT
  directive @requires(fields: FieldSet!) on FIELD_DEFINITION
  directive @provides(fields: FieldSet!) on FIELD_DEFINITION
  directive @key(fields: FieldSet!, resolvable: Boolean = true) repeatable on OBJECT | INTERFACE
  directive @link(url: String!, as: String, for: link__Purpose, import: [link__Import]) repeatable on SCHEMA
  directive @shareable repeatable on OBJECT | FIELD_DEFINITION
  directive @inaccessible on FIELD_DEFINITION | OBJECT | INTERFACE | UNION | ARGUMENT_DEFINITION | SCALAR | ENUM | ENUM_VALUE | INPUT_OBJECT | INPUT_FIELD_DEFINITION
  directive @tag(name: String!) repeatable on FIELD_DEFINITION | INTERFACE | OBJECT | UNION | ARGUMENT_DEFINITION | SCALAR | ENUM | ENUM_VALUE | INPUT_OBJECT | INPUT_FIELD_DEFINITION
  directive @override(from: String!) on FIELD_DEFINITION
  directive @composeDirective(name: String!) repeatable on SCHEMA
  directive @interfaceObject on OBJECT
  directive @authenticated on FIELD_DEFINITION | OBJECT | INTERFACE | SCALAR | ENUM
  directive @requiresScopes(scopes: [[federation__Scope!]!]!) on FIELD_DEFINITION | OBJECT | INTERFACE | SCALAR | ENUM
  directive @policy(policies: [[federation__Policy!]!]!) on FIELD_DEFINITION | OBJECT | INTERFACE | SCALAR | ENUM
  directive @context(name: String!) repeatable on INTERFACE | OBJECT | UNION
  directive @fromContext(field: ContextFieldValue) on ARGUMENT_DEFINITION

  # This definition is required only for libraries that don't support
  # GraphQL's built-in `extend` keyword
  directive @extends on OBJECT | INTERFACE
""".trimIndent()