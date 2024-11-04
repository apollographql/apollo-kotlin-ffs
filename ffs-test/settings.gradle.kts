pluginManagement {
  listOf(repositories, dependencyResolutionManagement.repositories).forEach {
    it.mavenCentral()
  }
}

dependencyResolutionManagement {
  versionCatalogs {
    this.create("libs") {
      this.from(files("../gradle/libs.versions.toml"))
    }
  }
}

includeBuild("../")