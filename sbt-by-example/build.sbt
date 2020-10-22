ThisBuild / scalaVersion := "2.13.3"
ThisBuild / organization := "com.example"

val scalaTest = "org.scalatest" %% "scalatest" % "3.2.0"
val gigahorse = "com.eed3si9n" %% "gigahorse-okhttp" % "0.5.0"
val playJson = "com.typesafe.play" %% "play-json" % "2.9.1"
val scalaTic = "org.scalactic" %% "scalactic" % "3.2.0"

lazy val hello = (project in file("."))
  .aggregate(helloCore)
  .dependsOn(helloCore)
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "Hello",
    libraryDependencies += scalaTest % Test,
  )

lazy val helloCore = (project in file("core"))
  .settings(
    name := "Hello Core",
    libraryDependencies ++= Seq(gigahorse, playJson, scalaTic),
    libraryDependencies += scalaTest % Test,
  )
