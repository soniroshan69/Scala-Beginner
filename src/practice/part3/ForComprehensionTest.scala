package practice.part3

object ForComprehensionTest extends App{
  val list = List(1,2,3)
  val  a = for{
    l <- list; if(l%2==0)
  } println(l)
  println("------------")
  //translation
  list.withFilter(l => l%2==0).foreach(println)
  println("------------")
  
  val list1 = List(1,2,3)
  val list2 = List(1,2,3)
  for{
    l1 <- list1
    l2 <- list2
  } println( l1+l2)
  println("------------")
  //translation
  list1.foreach(l1 => list2.foreach(l2=> println(l1+l2)))
  println("------------")
  
  val list11 = List(1,2,3)
  val list21 = List(1,2,3)
  val yieldList  = for{
    l1 <- list11
    l2 <- list21
  } yield ( l1+l2)
  println(yieldList)
  println("------------")
  //translation
  val yieldTrans = list11.flatMap(l1=> list21.map(l2=>l1+l2))
  println(yieldTrans)
  
  println("-----------------")
  val l = List(List(22, 222))
   val yield1 =  for{
    sl <- l
    el <- sl if(el>0)
  } yield el.toString.length
  println(yield1)
  //translation
  println(l.flatMap(sl => sl.filter(el => el > 0).map(el => el.toString.length)))
  

}