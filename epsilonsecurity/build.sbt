name := """EpsilonSecurity"""
organization := "ca.sfu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.6"
libraryDependencies += evolutions

// https://mvnrepository.com/artifact/org.javatuples/javatuples
libraryDependencies += "org.javatuples" % "javatuples" % "1.2"

// testing
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")