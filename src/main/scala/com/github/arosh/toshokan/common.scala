package com.github.arosh.toshokan

import scala.xml.NodeSeq
import dispatch._
import net.liftweb.util.Html5

/**
 * 共通で使う、ユーティリティのようなもの
 */
object common {

  /**
   * uriを指定してHTMLを取得し、パースしてNodeSeqで返す
   */
  def loadHtml(uri: String, charset: String = "UTF-8") = {
    val u = url(uri) >\ charset

    val parsed = Http(u >> { is =>
      Html5.parse(is) openOr NodeSeq.Empty
    })

    parsed
  }

}