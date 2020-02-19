package pratice.Day1

object subSets {
  def main(args: Array[String]): Unit = {
    var x=for(i<-0 to 100) yield scala.util.Random.nextInt(100)
    println(quickSort1(x.toList))
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
  def quickSort(x:List[Int]):List[Int]={
    if (x.isEmpty) x
    else
      quickSort(x.filter(e=>e<x.head)):::x.head::quickSort(x.filter(e=>e>x.head))
  }
  def quickSort1(x:List[Int]):List[Int]={
    x match {
      case Nil=>Nil
      case x::xs=>{
        val (before,after)=xs.partition(_<x)
        quickSort1(before) ::: x::quickSort1(after)
      }
    }
  }

}
