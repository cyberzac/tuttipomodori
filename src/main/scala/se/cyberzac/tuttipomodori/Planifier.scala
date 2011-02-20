package se.cyberzac.tuttipomodori

import org.joda.time.DateTime
import scala.collection.mutable.Map
import unfiltered.request._
import unfiltered.response._
class Planifier {

  case class UserId(userId: String)

  case class User(name: UserId)

  case class Pomodoro(expires: DateTime)

  val users = Map.empty[UserId, User]
  val pomodoros = Map.empty[User, Pomodoro]

  def planify = {
    unfiltered.filter.Planify({
      case PUT(Path(Seg("users" :: user :: Nil))) => registerUser(user)
      case DELETE(Path(Seg("users" :: Nil))) => clearUsers
      case DELETE(Path(Seg("users" :: user :: Nil))) => clearUser(user)
      case POST(Path(Seg("/users" :: user :: nil))) => setPomodoro(user, 25*60*1000)
      case GET(Path(Seg("users" :: user :: Nil))) => getPomodoro(user)
      case _ => NotFound
    })
  }

  def registerUser(userIdString: String): Responder[Any] = {
    val userId = UserId(userIdString)
    if (users.contains(userId)) {
      Conflict ~> ResponseString("User id " + userId + " already exists")
    } else {
      users.update(userId, User(userId))
      Ok ~> ResponseString("http://localhost/users/" + userId)
    }
  }

  def clearUser(userId: String): Responder[Any] = {
    if (users.remove(UserId(userId)).isDefined) {
      Ok
    } else {
      NotFound ~> ResponseString("no user " + userId)
    }
  }

  def clearUsers: Responder[Any] = {
    users.clear
    Ok
  }

  def setPomodoro(userIdString: String, length: Long): Responder[Any] = {
    val userId = UserId(userIdString)
    val user = users.getOrElse(userId, return NotFound ~> ResponseString("no user " + userId))
    val expires = new DateTime().plus(length)
    pomodoros.update(user, Pomodoro(expires))
    Ok
  }

  def getPomodoro(userIdString: String): Responder[Any] = {
    val userId = UserId(userIdString)
    val user = users.getOrElse(userId, return NotFound ~> ResponseString("no user " + userId))
    val now = new DateTime()
    val expires = (pomodoros.getOrElse(user, Pomodoro(now))).expires
    val left = scala.math.max(expires.getMillis() - now.getMillis(), 0).toString()
    Ok ~> ResponseString(left)
  }

}
