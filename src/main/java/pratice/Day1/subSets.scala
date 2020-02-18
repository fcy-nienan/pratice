package pratice.Day1

object subSets {
  def main(args: Array[String]): Unit = {
    var nums=Array(1,2,3)
    println(subSets(nums))
    //[1,2,3]==>{[1],[2],[3],[1,2],[1,3],[1,2,3],[2,3]}
  }
  def subSets(nums:Array[Int]):List[List[Int]]={
    if (nums==null||nums.length==0)return List[List[Int]]()
    var count:Int=java.lang.Math.pow(2,nums.length).asInstanceOf[Int]
    var res=List[List[Int]]()
    for (i <- 1 until count){
      var current=List[Int]()
      for (j<-0 until nums.length){
        var x=1<<j
        if((i&x)>0){
          current=nums(j) :: current
        }
      }
      res=current :: res
    }
    res
  }
}
