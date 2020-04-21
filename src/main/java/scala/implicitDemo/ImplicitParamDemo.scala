package scala.implicitDemo

object ImplicitParamDemo {
  def main(args: Array[String]): Unit = {
    implicit val x=100
    display
//    display方法接收一个隐式参数
//    而在上下文中我们定义了一个隐式参数x
//    所以可以直接调用方法而不传递参数
  }
  def display(implicit x:Int): Unit ={
    println(x)
  }
}
