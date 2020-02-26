package pratice.Day1

import scala.collection.mutable.ArrayBuffer

object uniquePaths {
  def main(args: Array[String]): Unit = {
    uniquePaths(3,3)
  }
  def uniquePaths(m:Int,n:Int):Int={
    var dp=Array.ofDim[Int](m,n)
    dp(0)(0)=0
    for (i<-0 until dp.length){
      dp(0)(i)=1
    }
    for(j<-0 until dp(0).length){
      dp(j)(0)=1
    }
    for (i<-1 until dp.length){
      for(j<-1 until dp(0).length){
        dp(i)(j)=dp(i-1)(j)+dp(i)(j-1)
      }
    }
    dp(m-1)(n-1)
  }
}
