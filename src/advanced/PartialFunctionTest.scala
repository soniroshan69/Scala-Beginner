package advanced

import java.text.Normalizer.Form

object PartialFunctionTest extends App{
  
  val aPartFun:PartialFunction[Int, String] = {
    case 1=> s"1+1"
  }
 
  println(aPartFun.isDefinedAt(1))
  println(aPartFun.isDefinedAt(2))
  println(aPartFun.andThen(_+1)(1))
  println(aPartFun.applyOrElse(2, (y:Int)=> y+1))
  println(aPartFun.orElse[Int,String]({
    case 2 => s"2+1" 
  })(2))
  
  println(aPartFun.lift(4))
  
  val seq = (1 to 1)
  seq.map(aPartFun).foreach(println)
  
  val fun:(Int=>String) = aPartFun
  println(fun(1))
  println(fun(2))
}