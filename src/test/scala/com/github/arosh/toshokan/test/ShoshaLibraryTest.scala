package com.github.arosh.toshokan.test

import java.util.concurrent.TimeUnit.SECONDS.sleep

import org.specs2.mutable.Specification
import org.specs2.specification.Scope

import com.github.arosh.toshokan.ShoshaLibrary

class ShoshaLibraryTest extends Specification {
  "ShoshaLibrary#getToken" should {
    "トークンを取ることができること" in new context {
      val token = obj.getToken()
      sleep(2)
      token must not be empty
    }
  }

  trait context extends Scope {
    val obj = new ShoshaLibrary
  }
}