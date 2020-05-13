package advanced.implicitTest

object Basics extends App{
  
  //implicit param
  def implicitParamMethod(x:Int)(implicit y:Int):Int = x+y
  
  implicit val value1:Int = 2 // or implicit def value:Int = 2 // methods without parenthesis(accessor methods) are acceptable
  println(implicitParamMethod(3))
  
  //implicit are not supported by anonymous functions as later does not support currying
  //and implicits are supported in currying
  
  
  //implicit methods
  class Person(name:String){
    def getNameUpper = name.toUpperCase()
  }
  implicit def implicitMethod(name:String):Person= new Person(name)
  
  println("roshan".getNameUpper)
  
  
  //implicit classes
  implicit class PersonImplicit(name:String){
    def getNameLower = name.toLowerCase()
  }
  
  println("ROSHAN".getNameLower)
  
  
  //implicitly
  case class Employee(name:String) extends Person(name)
  implicit val emp = Employee("roshan")
  val emp1 = implicitly[Employee]
  
  println(emp)
  println(emp)
  
  //context bound
  trait Print[T]{
    def print(x:T):Unit
  }
  implicit object PrintString extends Print[String]{
    def print(s:String) = println(s)
  }
  def print[T : Print](name:T)={
    val implicitPrintObj = implicitly[Print[T]]
    implicitPrintObj.print(name)
  }
  
  print("roshan")
  
  
  
  
  
  
  
  
  
  
}