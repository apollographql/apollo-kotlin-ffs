plugins {
  alias(libs.plugins.kgp)
  alias(libs.plugins.apollo)
}

apollo {
  service("service") {
    packageName.set("com.example")

    plugin("com.apollographql.ffs:apollo-kotlin-ffs")
  }
}

dependencies {
  implementation("com.apollographql.apollo:apollo-runtime")
}