ThisBuild / scalaVersion := "3.3.0"

val V = new {
  val llm4s = "0.11.0"
}

val Deps = new {
  val llm4s = "com.donderom" %% "llm4s" % V.llm4s
}

lazy val `llm` = (project in file("."))
  .settings(
    resolvers ++= Seq(
    ),
    libraryDependencies ++= Seq(
      Deps.llm4s,
    )
  )
