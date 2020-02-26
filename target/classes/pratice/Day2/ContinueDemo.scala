package pratice.Day2
import scala.util.control.Breaks._
object ContinueDemo {
  def main(args: Array[String]): Unit = {
    continueDemo()
  }
  def continueDemo():Unit={
    for(i<-0 to 10){
      breakable(
        if(i%3==0){
          break
        }else{
          println("kk"+i)
        }
      )
      println(i)
    }
  }
}
