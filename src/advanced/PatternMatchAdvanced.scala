package advanced

object PatternMatchAdvanced extends App{
  
  val list = List(1,2,3,4)
  println(list match {
    //case ListPattern1(1,2,_*)=> "list params"
    //or case ListPattern(1,2,_*)=> "hi"
    //case ListPattern() => "list type"
    case h split tail => s"$h, $tail"
  })
  
  /////////
  object ListPattern {
    def unapplySeq[T](list:List[T]):Option[List[T]]={
      if(list.isEmpty) Some(List.empty)
      else unapplySeq(list.tail).map(list.head+:_)
    }
    def unapply[T](list:List[T]):Boolean= !list.isEmpty
  }
  
   object ListPattern1 {
    def unapplySeq[T](list:List[T]):Option[List[T]]={
      if(list.isEmpty) None
      else Some(list)
    }
  }
   
   object split {
    def unapply[T](list:List[T]):Option[(T,List[T])]={
      if(list.isEmpty) None
      else Some((list.head, list.tail))
    }
  }
  
   /////////////////////////////////
   
   case class Or[A,B](a:A,b:B)
   val either = Or(2, "two")
   
   println(either match{
     case num Or string => s"$num, $string"
   })
   
   //////////////////////
   
   trait Wrapper[T]{
     def isEmpty:Boolean
     def get:T
   }
   
  class Person(val name:String, val age:Int)
  
  object PersonPattern{
    def unapply(person:Person):Wrapper[(String, Int)]={
      new Wrapper[(String, Int)]{ 
        def isEmpty = person==null
        def get = (person.name, person.age)
        }
      }
    }
   
  val person = new Person("roshan", 29)
  val personMatch = person match {
    case PersonPattern((n,a)) => s"my name is $n and my age is $a"
    case _ => s"not defined"
  }
  println(personMatch)
   
}