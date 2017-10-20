// todo: research overall functions of these imports.
import com.typesafe.sbt.packager.MappingsHelper._
mappings in Universal ++= directory(baseDirectory.value / "public")

name := """EpsilonSecurity"""
organization := "ca.sfu"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.2"

libraryDependencies += guice
libraryDependencies += javaJdbc
libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.8.6"
libraryDependencies += evolutions

// see https://www.playframework.com/documentation/2.6.x/ScalaHttpFilters
libraryDependencies += filters

// HTTP library for making asynchronous GET and POST requests from the app.
// see https://www.playframework.com/documentation/2.5.x/ScalaWS
libraryDependencies += ws


libraryDependencies += "io.ebean" % "querybean-generator" % "10.1.2"
libraryDependencies += "io.ebean" % "ebean-querybean" % "10.1.1"

// https://mvnrepository.com/artifact/org.javatuples/javatuples
libraryDependencies += "org.javatuples" % "javatuples" % "1.2"

// testing
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

//-----------------Development Hooks-----------------------------

PlayKeys.playRunHooks += WebpackServer(file("./front"))

//-----------------Production front-end build -------------------

lazy val cleanFrontEndBuild = taskKey[Unit]("Remove the old front-end build")

cleanFrontEndBuild := {
  val d = file("public/bundle")
  if (d.exists()) {
    d.listFiles.foreach(f => {
      if(f.isFile) f.delete
    })
  }
}

lazy val frontEndBuild = taskKey[Unit]("Execute the npm build command to build the front-end")

frontEndBuild := {
  println(Process("npm install", file("front")).!!)
  println(Process("npm run build", file("front")).!!)
}

frontEndBuild := (frontEndBuild dependsOn cleanFrontEndBuild).value

dist := (dist dependsOn frontEndBuild).value

