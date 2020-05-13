package advanced.monad

object MonadAttempt extends App {
  
  def calc(x:Int):Attempt[Int] = Attempt{
    x/(x-1)
  }
  
  println(calc(2))
  println(calc(1))
  println(calc(2).flatMap(x=>Attempt(x*2)))
  println(calc(1).flatMap(x=>Attempt(x*2)))
  
  
  
  trait Attempt[+A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B]
  }
  object Attempt {
    def apply[A](e: =>A): Attempt[A] = {
      try {
        Success(e)
      } catch {
        case ex: Throwable => Failure(ex)
      }
    }
  }

  case class Success[+ A](value: A) extends Attempt[A] {
    def flatMap[B](f: A => Attempt[B]): Attempt[B] = {
      try {
        f(value)
      } catch {
        case ex: Throwable => Failure(ex)
      }
    }
  }

  case class Failure(e: Throwable) extends Attempt[Nothing] {
    def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
  }

}