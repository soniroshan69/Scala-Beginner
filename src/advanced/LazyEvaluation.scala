package advanced

object LazyEvaluation extends App{
  
  lazy val a = {
    println("hello")
    1
  }
  println(a)
  println(a) 
  
  /////////////////
  lazy val simpleCondition = false
  lazy val sideEffectCondition = {
    println("side effect def")
    true
  }
  
  if(simpleCondition && sideEffectCondition) println("y") else println("N")
  
  //////////
  def callByName(x: =>Int) = {
    lazy val a = x
    a+a+a
  }
  
  def giveInt = {
    println("giveInt called")
    2
  }
  
  println(callByName(giveInt))
  
  //////////////////////////
  val list = List(1, 12, 21)
 val gt10 = list.filter((x)=> {println(s"gt10 $x"); x>10})
 val lt20 = gt10.filter((x)=> {println(s"lt20 $x"); x<20})
 println(lt20)
 
 val list1 = List(1, 11, 21)
 val gt10_1 = list1.withFilter((x)=> {println(s"gt10 $x"); x>10})
 val lt20_1 = gt10_1.withFilter((x)=> {println(s"lt20 $x"); x<20})
 println(lt20_1)
 lt20_1.foreach(println)
 
 /////
 for{
   a <- list if(a%2==0)
 } yield a+1
 
 //equivalent to
 list.withFilter(_%2==0).map(_+1)
  
}