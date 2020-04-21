package scala.ExtractorDemo

object ExtractorDemo {
  class User(val username:String,val password:String){

  }
  object User{
    def apply(username:String,password:String): User ={
      new User(username,password)
    }
    def unapply(x:User):Option[String]={
      Option(x.username)
    }
  }
  def main(args: Array[String]) {
    val value=User("fcy","nienan")
    println(value)
    val User(name)=value
    println(name)
  }
}
