package advanced.monad

object LazyMonad extends App{
  
  
  val lazyTest = Lazy{
      println("called lazy apply")
      3
    }
    
  val lazyFlatMap = lazyTest.flatMap(x => Lazy(s"$x is good")) //still lazy
  val lazyFlatMap2 = lazyTest.flatMap(x => Lazy(s"$x is good")) //still lazy
  println(lazyFlatMap.get) // now called
  println(lazyFlatMap.get) //now called
    //lazyTest.get
    
    
    
  
  class Lazy[A](value: =>A){
    lazy val internalLazyVal = value //so that value is calculated only onse when used multiple times
    def get = internalLazyVal
    def flatMap[B](f: (=>A)=> Lazy[B]):Lazy[B] = f(internalLazyVal) //flat map with lazy input param
  }
  
  object Lazy{
    def apply[A](value: =>A):Lazy[A] = new Lazy(value)
  }
  
}