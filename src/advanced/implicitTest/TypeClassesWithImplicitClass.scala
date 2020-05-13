package advanced.implicitTest

object TypeClassesWithImplicitClass extends App{
  
  trait Equality[T]{
    def apply(x:T, y:T):Boolean
  }
  object Equality{
    def apply[T](x:T, y:T)(implicit equality:Equality[T]):Boolean={
      equality(x, y)
    }
  }
  
  case class Person(name:String, age:String)
  
  implicit object PersonNameEquality extends Equality[Person]{
    def apply(p1:Person, p2:Person):Boolean = if(p1.name.equals(p2.name)) true else false
  }
  
  implicit class Equal[T](x:T){
    def ===(anotherValue:T)(implicit equality: Equality[T]) = equality(x,anotherValue)
    def !==(anotherValue:T)(implicit equality: Equality[T]) = !equality(x,anotherValue)
  }
  
  val p1 = Person("roshan", "29")
  val p2 = Person("roshan", "30")
  
  println(p1 === p2)
  println(p1 !== p2)
  
}