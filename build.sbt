name := "toshokan-search"

organization := "com.github.arosh"

version := "0.0.1"

scalaVersion := "2.9.2"

scalacOptions ++= Seq("-deprecation", "-unchecked", "-explaintypes")

libraryDependencies ++= Seq(
  "net.databinder" %% "dispatch-http" % "0.8.8",
  "org.specs2" %% "specs2" % "1.12" % "test",
  "net.liftweb" % "lift-util_2.9.1" % "2.4"
)

initialCommands := """import dispatch._"""

// sbteclipse with source
// EclipseKeys.withSource := true

// sbt-onejar
seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)
