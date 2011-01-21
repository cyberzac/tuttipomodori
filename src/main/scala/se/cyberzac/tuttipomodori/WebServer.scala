package se.cyberzac.tuttipomodori

import unfiltered.response._


object WebServer {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).filter(unfiltered.filter.Planify {
      case _ => Ok ~> ResponseString("Hello there2")
    }).run
  }
}