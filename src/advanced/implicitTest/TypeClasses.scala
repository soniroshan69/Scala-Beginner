package advanced.implicitTest

object TypeClasses extends App{
  
  trait Equality[T]{
    def apply(x:T, y:T):Boolean
  }
  
  case class Person(name:String, age:String)
  
  object PersonNameEquality extends Equality[Person]{
    def apply(p1:Person, p2:Person):Boolean = if(p1.name.equals(p2.name)) true else false
  }
  
  object PersonAgeEquality extends Equality[Person]{
    def apply(p1:Person, p2:Person):Boolean = if(p1.age==p2.age) true else false
  }
  
  val p1 = Person("roshan", "29")
  val p2 = Person("roshan", "30")
  
  println(PersonNameEquality(p1, p2))
  println(PersonAgeEquality(p1, p2))
}