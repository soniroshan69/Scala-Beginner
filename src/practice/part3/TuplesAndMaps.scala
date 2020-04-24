package practice.part3

object TuplesAndMaps extends App{
  
  val tuple1 = new Tuple1(1, "a", "b")
  println(tuple1)
  val tuple2 = 1->"a"->"c"->"d"
  println(tuple2)
  
  val tuple3 =(3, "v", 6)
  val tuple31 = tuple3.copy(_1="5")
  println(tuple31)
  
  println(tuple3)
  
  //Map
  val map:Map[Int, String] = Map()
  val map1 = map+(1->"a")+(2->"b")
  println(map1)
  
  println(map1.map(pair => pair._1-> pair._2.toUpperCase()))
  println(map1.map(pair => pair._2-> pair._1))
  //println(map1.map((k,v) => (k, v.toUpperCase()))) doesn't work
  
//Exercise
  
  val map2 = Map("a"->1, "A"->2, "B"->3)
  println(map2.map(pair => pair._1.toUpperCase()-> pair._2))
  
  
  
}