import java.net.URL
import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {

  val jbossRepo = "Jboss  repo" at "http://repository.jboss.org/nexus/content/groups/public"


  val unfiltered_version = "0.3.1"
  val unfiltered_jetty =  "net.databinder" %% "unfiltered-jetty" % unfiltered_version withSources()
  val unfiltered_filter = "net.databinder" %% "unfiltered-filter" % unfiltered_version withSources()

  val dispatch_version = "0.7.8"
  val dispatch =  "net.databinder" %% "dispatch-http" % dispatch_version withSources()

  val yodatime = "joda-time" % "joda-time" % "1.6.2"

  /*
  val slf4j_version = "1.6.1"
  val slf4j = "org.slf4j" % "slf4j-api" % slf4j_version
  val slf4j_log4j14 = "org.slf4j" % "slf4j-log4j12" % slf4j_version
      */
  val specs = "org.scala-tools.testing" % "specs_2.8.1" % "1.6.7" % "test"
  val scalatest = "org.scalatest" % "scalatest" % "1.2" %  "test"
  val unfiltered_spec = "net.databinder" %% "unfiltered-spec"  % unfiltered_version % "test"

}
