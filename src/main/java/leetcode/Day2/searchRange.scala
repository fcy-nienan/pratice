package leetcode.Day2

import scala.util.control.Breaks
import scala.util.control.Breaks._

object searchRange {
  def main(args: Array[String]): Unit = {
    for (elem <- searchRange(Array(5, 7, 7, 8, 8, 10), 8)) {
      println(elem)
    }
  }

  def searchRange(nums: Array[Int], target: Int): Array[Int] = {
    if (nums == null) return Array(-1, -1)
    var start = 0
    var end = nums.length - 1
    while (start <= end) {
      var mid = (start + end) / 2
      if (target > nums(mid)) {
        start = mid + 1
      } else if (target < nums(mid)) {
        end = mid - 1
      } else {
        var startIndex=mid
        var endIndex=mid
        var leftStart=0
        var leftEnd=mid
        while (leftStart<leftEnd-1){
          var leftMid=(leftStart+leftEnd)/2
          if (nums(leftMid)==target){
            leftEnd = leftMid
          }else{
            leftStart=leftMid+1
          }
        }
        if (nums(leftStart)==nums(leftEnd)){
          startIndex=leftStart
        }else{
          startIndex=leftEnd
        }
        var rightStart=mid
        var rightEnd=nums.length-1
        while (rightStart<rightEnd-1){
          var rightMid=(rightStart+rightEnd)/2
          if (nums(rightMid)==target){
            rightStart = rightMid
          }else{//88  89       78  88
            rightEnd=rightMid-1
          }
        }
        if (nums(rightStart)==nums(rightEnd)){
          endIndex=rightEnd
        }else{
          endIndex=rightStart
        }
        return Array(startIndex,endIndex)
      }
    }
    Array(-1, -1)
  }
}
