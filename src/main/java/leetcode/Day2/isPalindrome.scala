package leetcode.Day2
import scala.util.control.Breaks._
object isPalindrome {
  def main(args: Array[String]): Unit = {
    println(isPalindrome(10))
    println(10%10==0&&10!=0)
  }
  def isPalindrome(x: Int): Boolean = {
    if (x<0)return false
//    0结尾并且不是0的数字
    if (x%10==0&&x!=0)return false
    var reserved=0
    var src=x
//    当原始数字大于反转后的数字(基于位数判断)
    while(src>reserved){
      reserved=reserved*10+src%10
      src=src/10
    }
//    如果有123454321这种数字,执行上面的代码后reserved=12345,src=1234,所以判断时需要除以10
    reserved==src||reserved/10==src
  }
}
