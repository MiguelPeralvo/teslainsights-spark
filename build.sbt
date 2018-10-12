name := "teslainsights-spark"

version := "0.1"

scalaVersion := "2.11.12"

// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.2"

libraryDependencies += "org.apache.spark" %% "spark-streaming" % "2.3.2" // % "provided"

libraryDependencies += "org.apache.spark" %% "spark-mllib" % "2.3.2" //% "provided"

libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.10"

libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "commons-io" % "commons-io" % "2.5"