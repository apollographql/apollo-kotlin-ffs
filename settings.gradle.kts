rootProject.name = "apollo-kotlin-ffs-root"

pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.apply {
      mavenCentral()
      google()
      maven("https://storage.googleapis.com/gradleup/m2")
    }
  }
}

include(":apollo-kotlin-ffs")