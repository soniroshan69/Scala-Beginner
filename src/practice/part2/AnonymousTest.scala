package practice.part2

object AnonymousTest extends App{
  
  val list = NotEmpty(1, 2, 3, 4)
  
  println(list.filter((n) => n%2==0).toString())
  println(list.map[Int]((n) => n*2).toString())
  println(list.map[MyList[Int]]((n) => NotEmpty(n, n+1)).toString())
  println(list.flatMap[Int]((n) => NotEmpty(n, n+1)))
  
  trait MyPredicate[-T]{
    def test(param:T):Boolean
  }
  trait MyTransformer[-A,B]{
    def transform(param:A):B
  }
  
  trait MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B>:A](ele: B): MyList[B]
    def printList: String
    override def toString(): String = s"[ $printList ]"
    def filter(myPred:MyPredicate[A]):MyList[A]
    def map[B](myTrans:MyTransformer[A,B]):MyList[B]
    def flatMap[B](myTrans:MyTransformer[A,MyList[B]]):MyList[B]
    def ++[B>:A](list:MyList[B]):MyList[B]
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty = true
    def add[B>:Nothing](ele: B) = new NotEmpty(ele, Empty)
    def printList = ""
    def filter(myPred:MyPredicate[Nothing]):MyList[Nothing]=Empty
    def map[B](myTrans:MyTransformer[Nothing,B]):MyList[B] = Empty
    def flatMap[B](myTrans:MyTransformer[Nothing,MyList[B]]):MyList[B] = Empty
    def ++[B>:Nothing](list:MyList[B]):MyList[B] = list
  }

  class NotEmpty[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head = h
    def tail = t
    def isEmpty = false
    def add[B>:A](ele: B) = new NotEmpty(ele, this)
    def printList: String = {
      if (t.isEmpty) "" + h
      else s"$h  ${t.printList}"
    }

    def filter(myPred:MyPredicate[A]):MyList[A] = {
      if(myPred.test(h)) new NotEmpty(h, t.filter(myPred))
      else t.filter(myPred)
    }
    
    def map[B](myTrans:MyTransformer[A,B]):MyList[B]={
      new NotEmpty(myTrans.transform(h), t.map(myTrans))
    }
    
    def flatMap[B](myTrans:MyTransformer[A,MyList[B]]):MyList[B] = {
      myTrans.transform(h) ++ t.flatMap(myTrans)
    }
    
    def ++[B>:A](list:MyList[B]):MyList[B]= new NotEmpty(h, t ++ list)
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