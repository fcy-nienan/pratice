package pratice.Day1

import scala.collection.mutable.ArrayBuffer

object permuteUnique {
  def main(args: Array[String]): Unit = {
    var nums=Array(1,1,1,3)
    val list = permuteUnique1(nums)
    println(list)
  }
  def permuteUnique(nums:Array[Int]):List[List[Int]]={
    nums.toList.permutations.toList
  }
  def permuteUnique1(nums:Array[Int]):List[List[Int]]={
    if(nums==null||nums.length==0)return List[List[Int]]()
    java.util.Arrays.sort(nums);
    var res=new ArrayBuffer[List[Int]]
    var flag=new Array[Boolean](nums.length)
    dfs(nums,flag,ArrayBuffer[Int](),res)
    res.toList
  }
  def dfs(nums:Array[Int],flag:Array[Boolean],current:ArrayBuffer[Int],res:ArrayBuffer[List[Int]]):Unit={
    if(current.length==nums.length){
      res+=current.toList
    }else{
      for(i<- 0 until nums.length ){
        if(!flag(i)){
          if(i>0&&nums(i)==nums(i-1)&&(!flag(i-1))){

          }else {
            flag(i) = true
            current += nums(i)
            dfs(nums, flag, current, res)
            current -= nums(i)
            flag(i) = false
          }
        }

      }
    }
  }

}
