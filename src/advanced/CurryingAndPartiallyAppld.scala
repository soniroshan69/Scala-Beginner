package advanced

object CurryingAndPartiallyAppld extends App{
 
  val simpleAddFun = (x:Int, y:Int) => x+y
  def simpleAddMethod(x:Int, y:Int) = x+y
  def curriedAddMethod(x:Int)(y:Int) = x+y
   
  val add7_1 = (y:Int) => simpleAddFun(7,y)
  val add7_2 = simpleAddFun(7, _:Int) // internally converted as above
  val add7_3 = simpleAddFun.curried(7)
  
  val add7_4 = (y:Int) => simpleAddMethod(7,y)
  val add7_5 = simpleAddMethod(7, _:Int)
  
  val add7_6 = curriedAddMethod(7) _ //lift
  val add7_7 = curriedAddMethod(7)(_) //lift
  
  ///////////////////////////
  
  val list = List(1.0,2.0,3.0)
  
 def formatter(format:String)(num:Double) = format.format(num)
 
 val formatter1 = formatter("%3.2f")_
 val formatter2 = formatter("%4.3f")(_)
 
 list.map(formatter1).foreach(println)
 list.map(formatter2).foreach(println)
  
}