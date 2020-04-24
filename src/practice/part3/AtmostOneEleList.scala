package practice.part3

object AtmostOneEleList extends App{
  
  val list = Just(1)
  println(list.filter(_%2==0))
  println(list.map[Int](_*2))
  println(list.flatMap[Boolean]((x)=>Just(x%2==0)))
  
  
  
  trait MayBe[+T]{
    def map[B](f:T=>B):MayBe[B]
    def flatMap[B](f:T=>MayBe[B]):MayBe[B]
    def filter(f:T=>Boolean):MayBe[T]
  }
  
  case object MayBeNot extends MayBe[Nothing]{
   def map[B](f:Nothing=>B):MayBe[B]= MayBeNot
   def flatMap[B](f:Nothing=>MayBe[B]):MayBe[B]=MayBeNot
   def filter(f:Nothing=>Boolean):MayBe[Nothing]=MayBeNot
  }
  
  case class Just[+T](value:T) extends MayBe[T]{
    def map[B](f:T=>B):MayBe[B] = Just(f(value))
    def flatMap[B](f:T=>MayBe[B]):MayBe[B]= f(value)
    def filter(f:T=>Boolean):MayBe[T] = {
      if(f(value)) Just(value)
      else MayBeNot
    }
  }
}