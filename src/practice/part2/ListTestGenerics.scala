package practice.part2

import scala.annotation.tailrec

object ListTestGenerics extends App {

  println("HI")
  val list = new NotEmpty(1, new NotEmpty(2, new NotEmpty(3, Empty)))
  println(list.head)
  println(list.toString())
  println(Empty.toString())
  println(list.add(4).toString())
println("-------------")
  val list1 = NotEmpty(1, 2, 3, 4)
  println(list1.head)
  println(list1.toString())
  println(Empty.toString())
  println(list1.add(4).toString())

  trait MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B>:A](ele: B): MyList[B]
    def printList: String
    override def toString(): String = s"[ $printList ]"
  }

  object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty = true
    def add[B>:Nothing](ele: B) = new NotEmpty(ele, Empty)
    def printList = ""
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