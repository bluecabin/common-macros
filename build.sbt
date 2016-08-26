organization := "io.bluecabin"
name := "common-macros"
version := "1.1.0-SNAPSHOT"
licenses := Seq("Apache-2.0" -> url("http://www.opensource.org/licenses/apache2.0.php"))
scalaVersion := "2.11.8"
scalacOptions ++= Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")
scalacOptions in Test ++= Seq("-Yrangepos")
javacOptions in Compile ++= Seq(
  "-source", "1.7",
  "-target", "1.7"
)
libraryDependencies ++= {
  val specs2V = "3.7"
  Seq(
    "org.scala-lang" % "scala-reflect" % scalaVersion.value,
    "org.specs2" %% "specs2-core" % specs2V % Test,
    "org.specs2" %% "specs2-matcher-extra" % specs2V % Test
  )
}
bintrayReleaseOnPublish in ThisBuild := false
