package practice.part2

object MethodAnnotation extends App{
  
  class Person(val name:String, val favMovie:String, val age:Int){
    
    def +(nickName:String):Person={
      new Person(s"$name(nickName)", favMovie, age)
    }
    
    def unary_+ : Person={
      new Person(name, favMovie, age+1)
    }
    
    def learns={
      println(learnsScala)
    }
    
    def learnsScala:String={
      s"$name learns Scala"
    }
    
    def apply()={
      println(s"$name, $favMovie, $age")
    }
    
    def apply(n:Int)={
      println(s"$n times")
    }
  }
  
  val person = new Person("Roshan", "bala", 0)
  (person + "roshi")()
  
  (+person)()
  
  person learns
  
  person(2)
  
  
  
}