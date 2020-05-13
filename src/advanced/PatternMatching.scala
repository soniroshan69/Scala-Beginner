package advanced

object PatternMatching extends App{
  
  class Person(val name:String, val age:Int)
  
  object PersonPattern{
    def unapply(person:Person):Option[(String, Int)]={
      Some((person.name, person.age))
    }
    
    def unapply(age:Int):Option[String]={
      if(age<21) Some("minor")
      else Some("major")
    }
  }
  
  val person = new Person("roshan", 29)
  val personMatch = person match {
    case PersonPattern((n,a)) => s"my name is $n and my age is $a"
    case _ => s"not defined"
  }
 
  println(personMatch)
  
  val personStatus = person.age match {
    case PersonPattern(s) => s"person status $s"
  }
  println(personStatus)
  
  ///////////////////////////////////////////////////
  object Even{
    def unapply(num:Int):Option[Boolean]={
      if(num%2==0) Some(true) 
      else None
    }
  }
  
  object SingleDig{
    def unapply(num:Int):Option[Boolean]={
      if(num<10) Some(true)
      else None
    }
  }
  
    object Even1{
    def unapply(num:Int):Boolean= num%2==0
  }
  
  object SingleDig1{
    def unapply(num:Int):Boolean= num<10
  }
  
  val num = 5
  val numProp = num match {
    case Even(_) => s"even"
    case SingleDig(_)=> s"single digit"
    case _ => "nop prop"
  }
  
  val numProp1 = num match {
    case Even1() => s"even"
    case SingleDig1()=> s"single digit"
    case _ => "nop prop"
  }
  
  println(numProp)
  println(numProp1)
  
  
  
  
}