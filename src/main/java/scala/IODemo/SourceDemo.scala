package scala.IODemo

import scala.io.Source

object SourceDemo {
  def main(args: Array[String]): Unit = {
//    fromFile得到的结果进行foreach是针对每一个字符的
    Source.fromFile("F:\\Thread.txt","utf-8").foreach(print)
    val list = Source.fromFile("F:\\Thread.txt","utf-8").toList
    println(list)
  }
}
