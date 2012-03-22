


import sbt._
import Keys._
import Process._
import java.io.File



object ScalapoolBuild extends Build {
  
  /* tasks and settings */
  
  val javaCommand = TaskKey[String](
    "java-command",
    "Creates a java vm command for launching a process."
  )
  val javaCommandSetting = javaCommand <<= (
    dependencyClasspath in Compile,
    artifactPath in (Compile, packageBin),
    artifactPath in (Test, packageBin),
    packageBin in Compile,
    packageBin in Test
  ) map {
    (dp, jar, testjar, pbc, pbt) => // -XX:+UseConcMarkSweepGC  -XX:-DoEscapeAnalysis -XX:MaxTenuringThreshold=12 -XX:+UseCondCardMark 
    val javacommand = "java -Xmx512m -Xms512m -verbose:gc -XX:+PrintGCDetails -server -cp %s:%s:%s".format(
      dp.map(_.data).mkString(":"),
      jar,
      testjar
    )
    javacommand
  }
  
  val benchTask = InputKey[Unit](
    "bench",
    "Runs a specified benchmark."
  ) <<= inputTask {
    (argTask: TaskKey[Seq[String]]) =>
    (argTask, javaCommand) map {
      (args, jc) =>
      val javacommand = jc
      val comm = javacommand + " " + args.mkString(" ")
      println("Executing: " + comm)
      comm!
    }
  }
  
  /* projects */
  
  lazy val jvmbench = Project(
    "scala-jvm-benchmarks",
    file("."),
    settings = Defaults.defaultSettings ++ Seq(benchTask, javaCommandSetting)
  ) dependsOn (
  )
  
}
