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

// Play-Mailer Plugin
// https://github.com/playframework/play-mailer
libraryDependencies += "com.typesafe.play" %% "play-mailer" % "6.0.1"
libraryDependencies += "com.typesafe.play" %% "play-mailer-guice" % "6.0.1"

// see https://www.playframework.com/documentation/2.6.x/ScalaHttpFilters
libraryDependencies += filters

// HTTP library for making asynchronous GET and POST requests from the app.
// see https://www.playframework.com/documentation/2.5.x/ScalaWS
libraryDependencies += ws

// https://mvnrepository.com/artifact/org.javatuples/javatuples
libraryDependencies += "org.javatuples" % "javatuples" % "1.2"

// CAS Auth
libraryDependencies += "org.pac4j" % "play-pac4j" % "4.0.0"
libraryDependencies += "org.pac4j" % "pac4j-cas" % "2.1.0"
// TODO: check if we keep this
libraryDependencies ++= Seq(
  ehcache
)
libraryDependencies ++= Seq(
  "be.objectify" %% "deadbolt-java" % "2.6.1"
)

// testing
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

//testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")
//javaOptions in Test += "-Dconfig.file=conf/application.test.conf"

// [in dev-mode], Akka Http Server listens on this port.
// https://www.playframework.com/documentation/2.5.x/ConfigFile#Using-with-the-run-command
PlayKeys.devSettings := Seq("play.server.http.port" -> "9000") // default = 9000


// [in dev-mode], spin up the webpack dev-server after starting Play
PlayKeys.playRunHooks += WebpackServer(file("./front"))


// [in prod-mode], build the frontend before packaging
lazy val frontEndBuild = taskKey[Unit]("Execute the npm build command to build the front-end")

frontEndBuild := {
  println(Process("npm install", file("front")).!!)
  println(Process("npm run prod", file("front")).!!)
}

dist := (dist dependsOn frontEndBuild).value
stage := (stage dependsOn frontEndBuild).value
