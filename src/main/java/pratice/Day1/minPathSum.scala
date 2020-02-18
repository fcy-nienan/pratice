package pratice.Day1

object minPathSum {
  def minPathSum1(grid:Array[Array[Int]]):Int={
    if (grid==null||grid.length==0)return -1
    val dp=new Array[Array[Int]](grid.length)
    grid.indices.foreach(e=>{
      dp(e)=grid(e)
    })
    (1 until grid(0).length).foreach(e=>dp(0)(e-1)+dp(0)(e))
    (1 until grid.length).foreach(e=>dp(e-1)(0)+dp(e)(0))
    for{
      i<- 0 until grid.length
      j<- 0 until grid(0).length
    }{
      dp(i)(j)=Math.min(dp(i-1)(j),dp(i)(j-1))+dp(i)(j)
    }
    dp.last.last
  }
  def minPathSum(grid:Array[Array[Int]]):Int={
    if (grid==null||grid.length==0)return -1
    var dp=grid.clone();
    for(i<-1 until grid(0).length){
      dp(0)(i)=dp(0)(i-1)+dp(0)(i)
    }
    for (i<-1 until grid.length){
      dp(i)(0)=dp(i-1)(0)+dp(i)(0)
    }
    for (i<-1 until grid.length){
      for (j<-1.until(grid(0).length)){
        dp(i)(j)=java.lang.Math.min(dp(i-1)(j),dp(i)(j-1))+dp(i)(j)
      }
    }
    dp(grid.length-1)(grid(0).length-1)
  }
}
