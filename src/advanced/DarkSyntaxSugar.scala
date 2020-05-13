package advanced

object DarkSyntaxSugar extends App {

  class MyStream[T] {
    def ->:(stream: T) = println(stream)
  }
  
  val myStream = new MyStream[Int]
  1 ->: myStream
  
  ///////
  
  trait <[A,B]
  val tessThan: Int < String = ???
  
  trait >[A,B,C]
  // val tessThan: Int > String > Double = ???    Not working
  
  //////
  val anArray = Array(1,2,3)
  anArray(0)=5
  anArray.update(0, 5)
  
  /////////
  class MutableClass {
    private var internalMember:Int = 0
    def member:Int = internalMember
    def member_=(value:Int) :Unit = {
      internalMember = value
    }
    
  }
  
  val mutableClass = new MutableClass
  val a = mutableClass.member
  mutableClass.member = 42
  
}

