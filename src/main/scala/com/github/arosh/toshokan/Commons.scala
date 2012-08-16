package com.github.arosh.toshokan

import java.io.InputStream

import scala.xml.NodeSeq

import dispatch._
import net.liftweb.util.Html5

/**
 * 共通で使う、ユーティリティのようなもの
 */
object Commons {

  /**
   * LiftのHtml5 parserのラッパー
   * Http(u >> parse) のようなカッコイイ書き方ができるように val で宣言した
   */
  val parse = (is: InputStream) => Html5.parse(is) openOr NodeSeq.Empty

  /**
   * 指定したuriのHTMLを取得し、パースしたものをNodeSeqにして返す
   */
  def loadHtml(uri: String, charset: String = "UTF-8"): NodeSeq = {
    Http(url(uri) >\ charset >> parse)
  }

  /**
   * 指定したuriのHTMLとcookieのsession-idを取得する
   */
  def loadHtmlAndSessionId(uri: String, charset: String = "UTF-8"): (NodeSeq, String) = {

    val u = url(uri) >\ charset

    // unsafeなextractSessionId
    def extract(header: Map[String, Set[String]]) = {
      extractSessionId(header).get
    }

    val r = Http(u >+ { req =>
      (req >> parse, req >:> extract)
    })

    r
  }

  /**
   * headerからSessionIDを抽出する
   */
  def extractSessionId(header: Map[String, Set[String]]): Option[String] = {
    val regex = """^JSESSIONID=(\w+);.+$""".r

    val r = header.get("Set-Cookie") flatMap { cookie =>
      cookie collectFirst {
        case regex(id) => id
      }
    }

    r
  }

  def mkUrl(id: String): Request = {
    url("http://lib.laic.u-hyogo.ac.jp/mylimedio/search/search-input.do;jsessionid=" + id) POST
  }

  def mkQuery(): Map[String, String] = Map("mode" -> "simp", "nqid" -> "1", "queryid" -> "0")

  def mkForm(keyword: String, token: String): Map[String, String] = {
    import java.net.URLEncoder.encode

    Map("annex" -> "1",
      "annex" -> "3",
      "databasetarget" -> "local",
      "fulltext" -> "off",
      "keyword" -> encode(keyword, "UTF-8"),
      "keywordandor" -> "and",
      "listLimit" -> "50",
      "listOrder" -> "PDA",
      "org.apache.struts.taglib.html.TOKEN" -> token,
      "search" -> encode("検索", "UTF-8"),
      "searchtarget" -> "BK")
  }
}