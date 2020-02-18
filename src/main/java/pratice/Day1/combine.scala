package pratice.Day1

import scala.collection.mutable.ListBuffer

object combine {
  def main(args: Array[String]): Unit = {
    println(combine(3, 3))
//    (1 to 10).toList.combinations(3)
//    y.map(_ :+ 3)
//    println(x.combinations(2).toList)
    println(combine2(3, 3))
  }
  def combine1(n:Int,k:Int):List[List[Int]]={
    if(n == k) {
      List((1 to n).toList)
    } else if (k == 1) {
      (1 to n).toList.map(List(_))
    } else {
      (k to n).toList.flatMap(i => combine(i-1,k-1).map(_ :+ i))
    }
  }
  def combine2(n:Int,k:Int):List[List[Int]]={
    (1 to n).toList.combinations(k).toList
  }
  //返回1到n中所有可能的k个数的组合
  def combine(n: Int, k: Int): List[List[Int]] = {
    var nums=(1 to n).toArray
    var res=ListBuffer[List[Int]]()
    dfs(nums,0,k,List(),res)
    res.toList
  }
  def dfs(nums:Array[Int],index:Int,k:Int,current:List[Int],res:ListBuffer[List[Int]]):Unit={
    if (k==0){
      res+=current
    }else{
      for (i<-index until nums.length){
        dfs(nums,i+1,k-1,nums(i) :: current,res)
      }
    }
  }
}
