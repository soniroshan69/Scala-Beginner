package practice.part2

object VariancesPOC extends App{
  
  
  val myclassCovar:MyClassCovar[Animal] = new MyClassCovar[Cat]
  myclassCovar.fun(new Pussy)
  
   val myclassContra:MyClassContra[Cat] = new MyClassContra[Animal]
  myclassContra.fun(new Pussy)
  
  class MyClassCovar[+A <: Animal]{
    def fun[B>:A](param:B):B = {
      println( param.getClass().getName)
     param
    }
  }
  
  class MyClassContra[-A <: Animal]{
    def fun[B<:A](param:B):B = {
      println( param.getClass().getName)
     param
    }
  }
  
  class God
  class Animal extends God
  class Cat extends Animal
  class Pussy extends Cat
  class Dog extends Animal
  class Food
  
}