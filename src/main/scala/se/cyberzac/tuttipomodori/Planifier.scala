package se.cyberzac.tuttipomodori

import scala.collection.mutable.Set
import unfiltered.request._
import unfiltered.response._

class Planifier {

  val users = Set.empty[String]

  def planify = {
    unfiltered.filter.Planify({
      case PUT(Path(Seg("users" :: user :: Nil))) => registerUser(user)
      case DELETE(Path(Seg("users" :: Nil))) => clearUsers
      case DELETE(Path(Seg("users" :: user :: Nil))) => clearUser(user)
      //     case POST(Path(Seg("/" :: user :: "/start"))) => Ok ~> ResponseString("Pomodoro started")
      //     case POST(Path(Seg("/" :: user :: "/stop"))) => Ok ~> ResponseString("Pomodoro started")
       case GET(Path(Seg("users" :: user :: Nil ))) => getPomodoro(user)
      case _ => NotFound
    })
  }

  def registerUser(user: String) = {
    if (users.contains(user)) {
      Conflict ~> ResponseString("User id " + user + " already exists")
    } else {
      users += user
      Ok ~> ResponseString("http://localhost/users/" + user)
    }
  }

    def clearUser(user: String) = {
    if (users.remove(user)) {
      Ok
    } else {
      NotFound~> ResponseString("no user " + user)    }
  }

  def clearUsers = {
    users.clear
    Ok
  }

  def getPomodoro(user:String) = {
      NotFound~> ResponseString("no user " + user)
  }


}