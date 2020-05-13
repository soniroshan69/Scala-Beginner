package advanced.monad

object MonadConceptWithList extends App {
  
  //left identity
  //Unit(x).flatMap(f(x)) = f(x)
  val f = (x:Int) => List(x, x+1)
  val lhs = List(1).flatMap(f)
  println(lhs)
  val rhs = f(1)
  println(rhs)
  //so lhs==rhs
  
  
  //right identity
  //monad.flatMap(x => Unit(x)) = Unit(x)
  val list = List(1,2)
  val lhs1 = list.flatMap(x=> List(x))
  println(lhs1)
  val rhs1 = List(1,2)
  println(rhs1)
  //lhs=rhs
  
  //associative
  //monad.flatMap(f).flatMap(g) = monad.flatMap(x => f(x).flatMap(g))
  val list1 = List(2,3)
  val f1 = (x:Int) => List(x, x+1)
  val g1 = (x:Int) => List(x, x*x)
  val lhs2 = list1.flatMap(f1).flatMap(g1)
  println(lhs2)
  val rhs2 = list1.flatMap(x => f1(x).flatMap(g1))
  println(rhs2)
  //lhs=rhs
  
  
  
}