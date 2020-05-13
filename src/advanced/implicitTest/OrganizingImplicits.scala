package advanced.implicitTest

object OrganizingImplicits extends App {

  /*implicit scopes precedence
   * local
   * imported scope // both local and import cant have similar implicit
   * Companions of the types involved in method signature
   *  - List
   *  - Ordering
   *  - Types A, B
   *
   *  def sorted[B >: A](implicit ord: Ordering[B]): List
   * */

  //Finally most used implicits should be inside companion then in imports then local
  
  case class Person(name: String, age: Int, salary:Double)
  val personList = List(Person("roshan", 29, 2000), Person("divya", 23, 1000), Person("shubham",25, 1500))
  
  //Companion scope
  object Person {
    implicit val nameOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.name.compareTo(p2.name) < 0)
  }
  
  //import scope
  object SalaryOrdering{
    implicit val salaryOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.salary < p2.salary)
  }
  //import SalaryOrdering._
  
  //Local scope
  //age ordering will take precedence as it local scope and name ordering is Companion scope
  implicit val ageOrdering: Ordering[Person] = Ordering.fromLessThan((p1, p2) => p1.age < p2.age)
  
  println(personList.sorted)

}