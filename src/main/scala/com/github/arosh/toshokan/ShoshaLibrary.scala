package com.github.arosh.toshokan

class ShoshaLibrary extends Library {

  def getToken(): String = {
    val uri = "http://lib.laic.u-hyogo.ac.jp/mylimedio/search/search-input.do"
    val html = common.loadHtml(uri)

    val r = html \\ "input" filter { input =>
      (input \ "@name" text) == "org.apache.struts.taglib.html.TOKEN"
    } map { input =>
      input \ "@value" text
    }
    r.head
  }
}