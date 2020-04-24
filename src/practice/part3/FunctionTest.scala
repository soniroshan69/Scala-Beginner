package practice.part3

object FunctionTest extends App {

  val concat: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  val concat1:(String, String) => String = (a: String, b: String) => a + b

  println(concat("roshan", "soni"))
  println(concat("soni", "roshan"))
  
  val fun = new Function[Int, Function[Int, Int]] {
    override def apply(a: Int): Function[Int, Int] = new Function[Int, Int] {
      override def apply(b: Int): Int = a + b
    }
  }
  
  val fun1 = (a:Int) => (b:Int) => a+b
  

  println(fun(1)(2))
  println(fun1(3)(4))

}