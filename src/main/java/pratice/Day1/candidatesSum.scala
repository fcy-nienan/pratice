package pratice.Day1

import scala.collection.mutable.ListBuffer

object candidatesSum {
  def main(args: Array[String]): Unit = {
    var candidates=Array(2,3,6,7)
    var target=7
    val list = candidatesSum(candidates, target)
    println(list)
  }
  def candidatesSum(candidates:Array[Int],target:Int):List[List[Int]]={
    if (candidates==null||candidates.length==0)return List[List[Int]]()
    var res=ListBuffer[List[Int]]()
    dfs(candidates,target,0,0,List[Int](),res)
    res.toList
  }
  def dfs(candidates:Array[Int],target:Int,index:Int,currentSum:Int,current:List[Int],res:ListBuffer[List[Int]]):Unit={
    if (target==currentSum){
      res+=current
    }else{
      for (i<-index until  candidates.length if currentSum+candidates(i)<=target){
        dfs(candidates,target,i,currentSum+candidates(i),candidates(i) :: current,res)
      }
    }
  }
}
