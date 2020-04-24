package practice.part2

object Inheritance extends App{
  
  class Animal(age:Int){
    protected def eat()=Unit
    protected def sneez()=new Animal
    
    def this() = this(0)
  }
  
  class Animal1(age:Int){
    protected def eat()=Unit
    protected def sneez()=new Animal
    
    def this() = this(0)
  }
  
  class Cat(name:Int) extends Animal{
    eat
    override def sneez()={
      new Cat(name)
    }
  }
  
  class Cat1(name:Int) extends Cat(name){
    eat
  }
  
  //-----------------------------------------------
  
  trait Trait1{
    def fun():String
  }
  trait Trait2 extends Trait1{
    def fun2():String
  }
  
  
  
   abstract class Class1 extends Trait1 with Trait2{
    def fun():String = "Class1"
  }
  
  class Class2 extends Class1 with Trait1 with  Trait2{
    def fun2():String="Class2"
  }
  
  
}