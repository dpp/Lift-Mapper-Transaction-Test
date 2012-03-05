package com.transacttest.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.common.Loggable
import com.transacttest.model.{Contact, User}
import net.liftweb.http.{PlainTextResponse, OkResponse}

/**
 */


object TestRest2 extends RestHelper with Loggable {

  serve {
    case Get("test2" :: "transaction" :: Nil, _) =>
      User.create.name("user").save()
      interruptExecution()
      Contact.create.name("contact").save()
      OkResponse()
  }

  def interruptExecution() {
    throw new IllegalStateException()
  }

}

object TestRest extends RestHelper with Loggable {

  serve {
    case Get("test" :: "transaction" :: Nil, _) =>
      User.create.name("user").save()
      interruptExecution()
      Contact.create.name("contact").save()
      OkResponse()
    case Get("test" :: "get" :: Nil, _) =>
      PlainTextResponse("Created. Users: %s, Contacts: %s".format(User.count, Contact.count))
  }

  def interruptExecution() {
    throw new IllegalStateException()
  }

}



