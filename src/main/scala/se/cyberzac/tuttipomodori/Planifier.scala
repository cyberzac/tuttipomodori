package se.cyberzac.tuttipomodori

import scala.collection.mutable.Set
import unfiltered.request._
import unfiltered.response._

class Planifier {

  val users = Set.empty[String]

  def registerUser(user: String) = {
    if (users.contains(user)) {
      Conflict ~> ResponseString("User id " + user + " already exists")
    } else {
      users += user
      Ok ~> ResponseString("http://localhost/users/" + user)
    }
  }

  def planify = {
    unfiltered.filter.Planify({
      case PUT(Path(Seg("users" :: user :: Nil))) => registerUser(user)
      //     case POST(Path(Seg("/" :: user :: "/start"))) => Ok ~> ResponseString("Pomodoro started")
      //     case POST(Path(Seg("/" :: user :: "/stop"))) => Ok ~> ResponseString("Pomodoro started")
      //     case GET(Path(Seg(user))) => Ok ~> ResponseString((25 * 60 * 1000).toString)
      case _ => NotFound
    })
  }
}