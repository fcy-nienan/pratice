package pratice.Day1

import java.util

object nextPermutation {
  def main(args: Array[String]): Unit = {
    nextPermutation(Array(1, 5, 1))
  }
  def nextPermutation(nums:Array[Int]):Unit={
    import java.util._
    if(nums==null||nums.length==0)return
    var index:Int=nums.length-1
    while(index-1>=0&&nums(index)<=nums(index-1)){
      index=index-1
    }
    if(index == 0){
      util.Arrays.sort(nums)
    }else{
      val swap1=nums(index-1)
      var startReverseIndex=index
      while(index<=nums.length-1&&swap1<nums(index)){
        index=index+1
      }
      index=index-1
      nums(startReverseIndex-1)=nums(index)
      nums(index)=swap1
      Arrays.sort(nums,startReverseIndex,nums.length)
    }
  }
}
