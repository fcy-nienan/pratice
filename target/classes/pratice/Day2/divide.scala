package pratice.Day2

object divide {
  def main(args: Array[String]): Unit = {
    println(divide(Int.MinValue,-2))
    println(divide(1,1))
  }
  def divide(dividend: Int, divisor: Int): Int = {
    if(dividend == 0)return 0
    if (dividend==Int.MinValue&&divisor== -1)return Int.MaxValue

    var negative=dividend^divisor
    var lower=Math.abs(divisor.toLong)
    var upper=Math.abs(dividend.toLong)
    var res=0
    (31 to 0 by -1).foreach(e=>{
      if(upper>>e>=lower){
        res+=1<<e
//        需要将被除数减去除数乘以e
        upper -= lower<<e
      }
    })
//    小于等于0
    if(negative>=0)res else -res
  }
}
