package practice.part3

object ListTestFunctional extends App {

  val list = NotEmpty(1, 2, 3, 4)
  val list1 = NotEmpty("a", "b", "c", "d")
  
  println(list.filter((n) => n % 2 == 0).toString())
  println(list.map[Int]((n) => n * 2).toString())
  println(list.map[MyList[Int]]((n) => NotEmpty(n, n + 1)).toString())
  println(list.flatMap[Int]((n) => NotEmpty(n, n + 1)))
  list.foreach((n) => println(n))
  println(list.sort((x,y) => y-x))
  println(list.zipWith[String, String](list1, (x,y)=> x+y))
  println(list.fold[Int](1)((_+_)))
  val list3= for{
    n1 <- list
    n2 <- list1
  } yield ""+n2+n1
  println(list3)
  
  for{
    n1 <- list
    n2 <- list1
  } println (""+n2+n1)
  
  trait MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A](ele: B): MyList[B]
    def printList: String
    override def toString(): String = s"[ $printList ]"
    def filter(myPred: A => Boolean): MyList[A]
    def map[B](myTrans: A => B): MyList[B]
    def flatMap[B](myTrans: A => MyList[B]): MyList[B]
    def ++[B >: A](list: MyList[B]): MyList[B]
    def foreach(f: A => Unit): Unit
    def sort(compare: (A, A) => Int): MyList[A]
    def zipWith[B,C](list:MyList[B], zip:(A,B)=>C): MyList[C]
    def fold[B](init:B)(operator:(B,A)=>B):B
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty = true
    def add[B >: Nothing](ele: B) = new NotEmpty(ele, Empty)
    def printList = ""
    def filter(myPred: Nothing => Boolean): MyList[Nothing] = Empty
    def map[B](myTrans: Nothing => B): MyList[B] = Empty
    def flatMap[B](myTrans: Nothing => MyList[B]): MyList[B] = Empty
    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
    def foreach(f: Nothing => Unit): Unit = ()
    def sort(compare: (Nothing, Nothing) => Int) = Empty
    def zipWith[B,C](list:MyList[B], zip:(Nothing,B)=>C):MyList[C] = {
      if(!list.isEmpty) throw new RuntimeException("cannot zip")
      else Empty
    }
    def fold[B](init:B)(operator:(B,Nothing)=>B):B=init
  }

  class NotEmpty[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head = h
    def tail = t
    def isEmpty = false
    def add[B >: A](ele: B) = new NotEmpty(ele, this)
    def printList: String = {
      if (t.isEmpty) "" + h
      else s"$h  ${t.printList}"
    }

    def filter(myPred: A => Boolean): MyList[A] = {
      if (myPred(h)) new NotEmpty(h, t.filter(myPred))
      else t.filter(myPred)
    }

    def map[B](myTrans: A => B): MyList[B] = {
      new NotEmpty(myTrans(h), t.map(myTrans))
    }

    def flatMap[B](myTrans: A => MyList[B]): MyList[B] = {
      myTrans(h) ++ t.flatMap(myTrans)
    }

    def ++[B >: A](list: MyList[B]): MyList[B] = new NotEmpty(h, t ++ list)

    def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }
    
    def sort(compare: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedList: MyList[A]): MyList[A] =
        if (sortedList.isEmpty) new NotEmpty(x, Empty)
        else if (compare(x, sortedList.head) <= 0) new NotEmpty(x, sortedList)
        else new NotEmpty(sortedList.head, insert(x, sortedList.tail))

      val sortedTail = t.sort(compare)
      println(sortedTail)
      insert(h, sortedTail)
    }
    
    //[1,2,3,4].sort = [2,3,4].sort
    //[2,3,4].sort = [3,4].sort
    //[3,4].sort = [4].sort
    //[4].sort = Empty.sort
    //Empty.sort = Empty (Changed to 4)
    
    def zipWith[B,C](list:MyList[B], zip:(A,B)=>C):MyList[C] = {
      new NotEmpty(zip(h,list.head), t.zipWith(list.tail, zip))
    }
    
    def fold[B](init:B)(operator:(B,A)=>B):B = {
     t.fold(operator(init,h))(operator)
    }

  }
  object NotEmpty {
    def apply[A](elements: A*): MyList[A] = {

      def createMyList[A](elements: Seq[A]): MyList[A] = {
        if (elements.isEmpty) Empty
        else new NotEmpty(elements.head, createMyList(elements.tail))
      }
      createMyList(elements)
    }
  }
}