package advanced

object PotentialyInfiniteStream extends App{
  
  val infiniteStream = MyStream.from(1)((x)=>x+1)
  
  println(infiniteStream.head)
  println(infiniteStream.tail)
  
  val infiniteStream1 = 0 #:: infiniteStream
  println(infiniteStream1.head)
  println(infiniteStream1.tail)
  
  val infiniteStream2 = infiniteStream ++ infiniteStream1
  println(infiniteStream2.head)
  println(infiniteStream2.tail)
  
  infiniteStream.take(10000).foreach(println)
  
  infiniteStream.take(10000).filter(_%2==0).foreach(println)
  
  infiniteStream.take(10).map(_+1).foreach(println)
  
  infiniteStream.take(10).flatMap(x => new NotEmpty(x, new NotEmpty(x+1, Empty))).foreach(println)
  
  println(infiniteStream.take(10).toList(Nil).toString)
  
  println(fibonacci(1, 1).take(10).toList(Nil).toString)
  
  println(erastosthenes(MyStream.from(2)(_+1)).take(10).toList(Nil).toString)
  
  
  
  
  trait MyStream[+A] {
    def isEmpty:Boolean
    def head:A
    def tail:MyStream[A]
    def #::[B>:A](element:B):MyStream[B]
    def ++[B>:A](anotherStream: =>MyStream[B]):MyStream[B]
    def foreach(f:A=>Unit):Unit
    def map[B](f:A=>B):MyStream[B]
    def flatMap[B](f:A=>MyStream[B]):MyStream[B]
    def filter(f:A=>Boolean):MyStream[A]
    def take(n:Int):MyStream[A]
    def toList[B>:A](acc:List[B]):List[B]={
      if(isEmpty) acc.reverse
      else tail.toList(head::acc)
    }
  }
  
  object Empty extends MyStream[Nothing] {
    def isEmpty:Boolean = true
    def head:Nothing = throw new NoSuchElementException
    def tail:MyStream[Nothing] = throw new NoSuchElementException
    def #::[B>:Nothing](element:B):MyStream[B]= new NotEmpty(element, Empty)
    def ++[B>:Nothing](anotherStream: =>MyStream[B]):MyStream[B] = anotherStream
    def foreach(f:Nothing=>Unit):Unit = ()
    def map[B](f:Nothing=>B):MyStream[B] = Empty
    def flatMap[B](f:Nothing=>MyStream[B]):MyStream[B] = Empty
    def filter(f:Nothing=>Boolean):MyStream[Nothing] = Empty
    def take(n:Int):MyStream[Nothing] = Empty
  }
  
  class NotEmpty[+A](h:A, t: => MyStream[A]) extends MyStream[A] {
    def isEmpty:Boolean = false
    override val head:A= h
    override lazy val tail:MyStream[A]=t
    def #::[B>:A](element:B):MyStream[B] = new NotEmpty(element, this)
    def ++[B>:A](anotherStream: => MyStream[B]):MyStream[B]={
      lazy val as = anotherStream
     new NotEmpty(head, tail++as)
    }
    def foreach(f:A=>Unit):Unit={
      f(h)
      tail.foreach(f)
    }
    def map[B](f:A=>B):MyStream[B]={
      new NotEmpty(f(h), tail.map(f))
    }
    def flatMap[B](f:A=>MyStream[B]):MyStream[B]={
      f(h)++tail.flatMap(f)
    }
    def filter(f:A=>Boolean):MyStream[A]={
      if(f(h)) new NotEmpty(h, tail.filter(f))
      else tail.filter(f)
    }
    def take(n:Int):MyStream[A]={
        if(n==0) Empty
        else new NotEmpty(h, tail.take(n-1))
    }
  }
  
  object MyStream {
    def from[A](start:A)(generator: A=>A):MyStream[A]={
      new NotEmpty(start, from(generator(start))(generator))
    }
  }
  
  def fibonacci(first:Int, second:Int):MyStream[Int]={
    new NotEmpty(first, fibonacci(second, first+second))
  }
  
  //erastosthenes sieve prime numbers
  def erastosthenes(naturalNums:MyStream[Int]):MyStream[Int]={
    if(naturalNums.isEmpty) naturalNums
    else new NotEmpty(naturalNums.head, erastosthenes(naturalNums.tail.filter(_%naturalNums.head!=0)))
  }
}