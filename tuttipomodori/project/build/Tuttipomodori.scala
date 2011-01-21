import java.net.URL
import sbt._

class Project(info: ProjectInfo) extends DefaultProject(info) {

  val jbossRepo = "Jboss  repo" at "http://repository.jboss.org/nexus/content/groups/public"

  // Repos
 /*
  def jbossUrl = new URL("http://repository.jboss.org/nexus/content/groups/public")
  val jbossRepo = Resolver.url("JBoss public repo", jbossUrl)

  val scalaToolsRepo = ScalaToolsReleases
  val defaultMaven = DefaultMavenRepository
   */

  val unfiltered_version = "0.3.1"
  //val unfiltered =  "net.databinder" %% "unfiltered" % unfiltered_version withSources()
  val unfiltered_jetty =  "net.databinder" %% "unfiltered-jetty" % unfiltered_version withSources()
  val unfiltered_filter = "net.databinder" %% "unfiltered-filter" % unfiltered_version withSources()
  val unfiltered_spec = "net.databinder" %% "unfiltered-spec"  % unfiltered_version % "test"

}