package pratice.Day2

import pratice.Day2.combinationSum2.dfs

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks.{break, breakable}

object combinationSum2 {
  def main(args: Array[String]): Unit = {
    println(combinationSum3(Array(1, 2, 2, 3, 4, 5), 8))
  }
  def combinationSum3(candidates: Array[Int], target: Int): List[List[Int]] = {
    val sortedCandi = candidates.groupBy(num => num).mapValues(_.length).toArray.sortBy(_._1)
    val ansSet = sortedCandi.map({
      case (num, cnt) =>
        (num, (0 to cnt).map(_ * num))
    })
    ansSet.foldLeft(List((target, List.empty[Int])))((sol, candi) => {
      sol.filter(s => {
        s._1 == 0 || s._1 >= candi._1
      }).flatMap(s => {
        candi._2.filter(_ <= s._1).map(sum => {
          if (sum == 0){
            s
          }else{
            (s._1 - sum, s._2 ++ (1 to (sum / candi._1)).map(_ => candi._1).toList)
          }
        })
      })
    }).filter(_._1 == 0).map(_._2)
  }
  def combinationSum2(candidates: Array[Int], target: Int): List[List[Int]] = {
    java.util.Arrays.sort(candidates)
    var res=new ArrayBuffer[List[Int]]()
    dfs(candidates,0,target,List[Int](),res)
    res.toList
  }
  def dfs(candidates:Array[Int],index:Int,target:Int,current:List[Int],res:ArrayBuffer[List[Int]]):Unit={
    if(target==0){
      println(current)
      res += current
    }else{
      (index until candidates.length) foreach {
        e=>
          breakable(
//            关键在于e>index
            if(e>index&&candidates(e)==candidates(e-1)){
              break
            }else{
              if(target-candidates(e)>=0){
                dfs(candidates,e+1,target-candidates(e),current :+ candidates(e),res)
              }
            }
          )
      }
    }
  }
}
