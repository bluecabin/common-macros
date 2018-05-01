organization := "io.bluecabin.common"
name := "common-macros"
description := "Common Scala macros"
version := "1.0.2-SNAPSHOT"
licenses := Seq("Apache-2.0" -> url("http://www.opensource.org/licenses/apache2.0.php"))
homepage := Some(url("https://github.com/bluecabin/common-macros"))
scmInfo := Some(ScmInfo(
  url("https://github.com/bluecabin/common-macros"),
  "scm:git:https://github.com/bluecabin/common-macros.git",
  Some("scm:git:ssh://git@github.com:bluecabin/common-macros.git"))
)
scalaVersion := "2.11.12"
crossScalaVersions := Seq("2.11.12", "2.12.6")
releaseCrossBuild := true
releaseUseGlobalVersion := false
scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")
scalacOptions in Test ++= Seq("-Yrangepos")
libraryDependencies ++= {
  val specs2V = "4.1.0"
  Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.specs2" %% "specs2-core" % specs2V % Test,
    "org.specs2" %% "specs2-matcher-extra" % specs2V % Test
  )
}
bintrayReleaseOnPublish in ThisBuild := false
pomExtra :=
  <developers>
    <developer>
      <id>bluecabin</id>
      <name>Blue Cabin</name>
      <email>bluecabindev@gmail.com</email>
    </developer>
  </developers>
