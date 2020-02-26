package pratice.Day1

object spiralOrder {
  def spiralOrder(nums:Array[Array[Int]]):List[Int]={
    if (nums==null||nums.length==0)return List[Int]()
    var left=0
    var right=nums(0).length
    var top=0
    var bottom=nums.length
    var res=List[Int]()
    var i=left
    var j=right
    while (true){
      j=left
      while (j<right){
        res=nums(i)(j) :: res
        j=j+1
      }
      j=j-1

      i=top+1
      while(i<bottom){
        res=nums(i)(j) :: res
        i=i+1
      }
      i=i-1

      j=right-2
      while(j>=left){
        res=nums(i)(j) :: res
        j=j-1
      }
      j=j+1
      if(left>=right-1||top>=bottom-1)return res
      i=bottom-2
      while (i>top){
        res=nums(i)(j) :: res
        i=i-1
      }
      i=i+1

      left=left+1
      right=right-1
      top=top+1
      bottom=bottom-1
    }
    res
  }
}
