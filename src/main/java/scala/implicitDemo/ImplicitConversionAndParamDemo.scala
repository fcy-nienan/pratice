package scala.implicitDemo

object ImplicitConversionAndParamDemo {
  def main(args: Array[String]): Unit = {
    getIndex("asdf",'d')
  }
  def getIndex[T, CC](seq: CC, value: T)(implicit conv: CC => Seq[T]) = seq.indexOf(value)
}
