package scala.implicitDemo

/*
*
* Speaking very briefly about the latter type,
* if one calls a method m on an object o of a class C,
* and that class does not support method m,
* then Scala will look for an implicit conversion from C to something that does support m.
* */
object ImplicitConversionDemo {
  class C{

  }
  class D{
    def display(x:String): Unit ={
      println(x)
    }
  }
  implicit def C2D(c:C): D ={
    new D
  }
  def main(args: Array[String]): Unit = {
    val x=new C()
    x.display("asdf")
  }
}
