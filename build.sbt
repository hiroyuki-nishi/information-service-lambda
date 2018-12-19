name := "information-service-lambda"

lazy val commonSettings = Seq (
  version := "1.0",
  scalaVersion := "2.12.8"
)

val commonLibraryDependencies = Seq(
  "com.amazonaws" % "aws-lambda-java-events" % "1.3.0",
  "com.amazonaws" % "aws-lambda-java-core" % "1.1.0"
)

lazy val root = (project in file("."))

lazy val message = (project in file("application/message"))
  .settings(
    name := "message",
    libraryDependencies ++= commonLibraryDependencies
  )
  .settings(assemblyJarName in assembly := "message.jar")
  .dependsOn(domain)

lazy val domain = (project in file("domain"))
  .settings(
    name := "domain",
    libraryDependencies ++= Seq(
      "io.spray" %%  "spray-json" % "1.3.5"
    )
  )

lazy val infrastracture = (project in file("infrastracture"))
  .settings(
    name := "infrastracture",
    libraryDependencies ++= Seq(
      "com.amazonaws" % "aws-java-sdk-dynamodb" % "1.11.29"
    )
  ).dependsOn(domain, message)
