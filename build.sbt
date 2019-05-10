addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full) // FXML support for GUI

libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.4" // FXML support for GUI
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.5" % "test" // for unit testing
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.144-R12" // for GUI

name := "ScalaMatrixLibrary"

version := "0.1"

scalaVersion := "2.12.8"