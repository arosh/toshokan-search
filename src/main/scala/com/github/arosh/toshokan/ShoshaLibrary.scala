package com.github.arosh.toshokan

class ShoshaLibrary extends Library {

  @deprecated("use getTokenAndSessionId", "0.1")
  def getToken(): String = {
    val uri = "http://lib.laic.u-hyogo.ac.jp/mylimedio/search/search-input.do"
    val html = Commons.loadHtml(uri)

    val r = html \\ "input" filter { input =>
      (input \ "@name" text) == "org.apache.struts.taglib.html.TOKEN"
    } map { input =>
      input \ "@value" text
    }

    r.head
  }

  def getTokenAndSessionId(): (String, String) = {
    val uri = "http://lib.laic.u-hyogo.ac.jp/mylimedio/search/search-input.do"
    val (html, id) = Commons.loadHtmlAndSessionId(uri)

    val token = html \\ "input" filter { input =>
      (input \ "@name" text) == "org.apache.struts.taglib.html.TOKEN"
    } map { input =>
      input \ "@value" text
    }

    (token.head, id)
  }
}