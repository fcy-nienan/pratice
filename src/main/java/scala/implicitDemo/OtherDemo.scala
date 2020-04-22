package scala.implicitDemo

object OtherDemo {
  def main(args: Array[String]): Unit = {
    implicit val str="asb";
    implicit val int=2
    println(implicitly[String] * implicitly[Int])

  }
  def x=new Object{

  }
  def y=new Object()
  def age=30
  def getChinese(x:Int):String = x match{
    case 1=>"一"
    case 2=>"二"
    case 3=>"三"
    case 4=>"四"
    case 5=>"五"
    case 6=>"六"
    case _=>"其他"
  }
}
