package practice.part3

object MapFlatMapFilterFor extends App{
  
  val list1 = List(1,2,3,4)
  val list2 = List('a', 'b', 'c', 'd')
  
  val list3 = list1 flatMap((a) => list2 map((b) => b+(a.toString())))
  println(list3)
  
  for{
    n1 <- list1 if(n1%2==0)
    n2 <- list2
  } yield ""+n2+n1
}