package practice.part3

object HOFandCurrying extends App{
  
  //apply function f , n times to a value x
  def nTimes(f:(Int=>Int), n:Int, x:Int):Int={
    if(n==0) x
    else nTimes(f, n-1, f(x))
  }
  
  val nTimesAnoFun:(Int => Int, Int, Int) => Int = (f:(Int=>Int), n:Int, x:Int) => {
    if(n==0) x
    else nTimesAnoFun(f, n-1, f(x))
  }
  
  val plusOne = (x:Int) => x+1
  println(  nTimes(plusOne, 10, 1)  )
  println(  nTimesAnoFun(plusOne, 10, 1)  )
  
  
  
  def nTimesBetter(f:Int=>Int, n:Int): (Int =>Int) = {
    if(n==0) (x:Int)=>x
    else (x:Int) => nTimesBetter(f,n-1)(f(x))
  }
  
  println(nTimesBetter(plusOne, 10)(1))
  
  //------------------------------
  def toCurry(f:(Int, Int)=> Int):(Int=>Int=>Int)={
    (x)=>(y)=> f(x,y)
  }
  
  val f1:(Int,Int)=>Int = (x:Int, y:Int) => x+y
  var tc = toCurry(f1)
  println(tc(3)(4))
  
  //------------------------------------
  def fromCurry(f:Int=> Int=> Int):(Int,Int)=>Int={
    (x,y) => f(x)(y)
  }
  
  val f2:Int=>Int=>Int = (x:Int)=> (y:Int) => x+y
  var fc = fromCurry(f2)
  println(fc(5,4))
  
  //---------------------------------------
  def compose[A,B,T](f:(A=> B), g:(T=>A)): T=>B ={
    x => f(g(x))
  }
  
  var f11 = (x:Int) => x*x
  var g11 = (x:Int) => x+1
  val comp = compose(f11, g11)
  println(comp(2))
  
  
  //---------------------------------------------
  def andThen[A,B,C](f:(A=> B), g:(B=>C)): A=>C ={
    x => g(f(x))
  }
  
  var f12 = (x:Int) => x*x
  var g12 = (x:Int) => x+1
  val andThn = andThen(f11, g11)
  println(andThn(2))
}