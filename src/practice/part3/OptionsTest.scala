package practice.part3

object OptionsTest extends App {

  val add1 = Address("4th", None/*Some("202")*/, "Bang", "ka", "560016")
  val per1 = Person("rosh", Some(add1))

  println(getStreet2(Some(per1)))
  println("----------------")
  getStreet2(Some(per1)).foreach(println)
  println("----------------")
  println(None.flatMap(x => None))
  println("----------------")
  println(getStreet2For(Some(per1)))
  
  case class Address(
    street1: String,
    street2: Option[String],
    city:    String,
    state:   String,
    zip:     String)

  case class Person(
    name:    String,
    address: Option[Address])

  def getStreet2(maybePerson: Option[Person]): Option[String] = {
    val a = maybePerson flatMap { person =>
      {
        println(s"person: $person")
        val b = person.address flatMap { address =>
          {
            println(s"address : $address")
            address.street2
          }
        }
        println(s"b: $b")
        b
      }
    }
    println(s"a: $a")
    a
  }

  def getStreet2For(maybePerson: Option[Person]): Option[String] = {
    val ret = for{
      person <- maybePerson
      adress <- person.address
      street2 <- adress.street2
    } yield street2
    ret
  }
  
  /////////random test
  /*  def fun(param: Boolean): Option[Int] = {
    if (param) Some(1)
    else None
  }

 println(fun(true))
  println(fun(false))
  println(fun(true).orElse(Some(0)))
  println(fun(false).orElse(Some(0)))

  println()

  def fun2(param: Boolean): Option[String] = {
    if (param) Some("a")
    else None
  }

  println(fun2(true))
  println(fun2(false).getOrElse(1))
  println(fun2(true).orElse(Some(0)))
  println(fun2(false).orElse(Some(0)))

  println()

  println(fun(true).map(_ + 2))
  println(fun(false).map(_ + 2))

  println(fun(true).flatMap(x => None))
  println(fun(false).flatMap(x => Some(1)))
*/
  println(List(Some(Some(3)), None, Some(2)).flatMap(x => x))

}