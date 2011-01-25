package se.cyberzac.tuttipomodori

import org.specs.Specification
import org.specs.runner.ScalaTest
import dispatch._

trait Served extends Specification with ScalaTest {
  // shareVariables()

  import unfiltered.jetty._
  def setup: (Server => Server)
  val port = 9090
  lazy val server = setup(new Http(port))
  val host = dispatch :/("localhost", port)

  doBeforeSpec { server.start() }
  doAfterSpec { server.stop() }
}
