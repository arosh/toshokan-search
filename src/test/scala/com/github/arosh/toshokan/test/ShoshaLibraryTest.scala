package com.github.arosh.toshokan.test

import scala.Console.err

import java.util.concurrent.TimeUnit.SECONDS.sleep

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import com.github.arosh.toshokan.ShoshaLibrary

class ShoshaLibraryTest extends Specification {
  trait context extends Scope {
    val obj = new ShoshaLibrary
    val (token, id) = obj.getTokenAndSessionId()
    sleep(2)
  }

  "ShoshaLibrary#getToken" should {
    "トークンを取得できること" in new context {
      err.println("token: " + token)
      token must not be empty
    }

    "Session-IDを取得できること" in new context {
      err.println("id: " + id)
      id must not be empty
    }
  }
}