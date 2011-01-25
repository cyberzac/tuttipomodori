package se.cyberzac.tuttipomodori

import unfiltered.response._


object WebServer {

  val defaultPort = 8080

  def apply = {
    new WebServer(defaultPort)
  }

  def main(args: Array[String]) {
    new WebServer(defaultPort) start
  }

}

class WebServer(val port: Int) {

  def start = {
    unfiltered.jetty.Http(port).filter(new Planifier().planify).run
  }


}