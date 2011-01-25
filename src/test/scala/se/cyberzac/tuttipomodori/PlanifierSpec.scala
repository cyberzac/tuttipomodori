package se.cyberzac.tuttipomodori

import dispatch.{Http, StatusCode}


class PlanifierSpec extends Served {

  def setup = {
    val dut = new Planifier
    _.filter(dut.planify)
  }

  "Planifier" should {

    "throw NotFound for an unknown url" in {
      Http(host / "invalidPath" >|) must throwA[StatusCode]
    }

    "register a new user with put:/users/{userid}" in {
      Http(host / "users/kalle" <<< "password" as_str) must_== "http://localhost/users/kalle"
    }

    "throw a Conflict when trying to register a new user with a existing users id " in {
      Http(host / "users/nisse" <<< "password" >|)
      Http(host / "users/nisse" <<< "password" as_str) must throwAn(new StatusCode(409, "User id nisse already exists"))
    }
  }
}