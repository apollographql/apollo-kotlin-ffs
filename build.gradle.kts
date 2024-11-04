import com.gradleup.librarian.gradle.librarianRoot

plugins {
  alias(libs.plugins.kgp).apply(false)
  alias(libs.plugins.librarian).apply(false)
}

librarianRoot()