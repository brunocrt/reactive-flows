lazy val reactiveflows =
  project.in(file(".")).enablePlugins(AutomateHeaderPlugin, GitVersioning)

libraryDependencies ++= Vector(
  Library.scalaTest % "test"
)

initialCommands := """|import de.heikoseeberger.reactiveflows._
                      |""".stripMargin
