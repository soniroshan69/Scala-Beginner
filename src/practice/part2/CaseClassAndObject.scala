package practice.part2

object CaseClassAndObject extends App{
  
  case class CC(name:String){
    
  }
  
  val cc1 = CC("roshan")
  println(cc1.name)
  
  
  
  case object CO{
    
  }
  
  val co1=CO
  val co2 = CO
  println(co1.eq(co2))
  println(co1.equals(co2))
}