package leetcode.Day2

object threeSumClosest {
  def main(args: Array[String]): Unit = {

  }
  /*
  下面是暴力法
  还可以先排序然后使用双指针
  * */
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    if(nums==null)return -1
    var closest=Int.MaxValue
    var t=0
    for (i<-0.until(nums.length)){
      for (j<-0.until(nums.length) if j!=i){
        for (k<-0.until(nums.length) if k!=j&&k!=i){
          val sum=Math.abs(nums(i)+nums(j)+nums(k)-target)
          if (sum<closest){
            closest=sum
           t=nums(i)+nums(j)+nums(k)
          }
        }
      }
    }
    t
  }
}
