package practice.part2

object ListTest extends App {

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

  trait MyList {
    def head: Int
    def tail: MyList
    def isEmpty: Boolean
    def add(ele: Int): MyList
    def printList: String
    override def toString(): String = s"[ $printList ]"
  }

  object Empty extends MyList {
    def head: Int = throw new NoSuchElementException
    def tail: MyList = throw new NoSuchElementException
    def isEmpty = true
    def add(ele: Int) = new NotEmpty(ele, Empty)
    def printList = ""
  }

  class NotEmpty(h: Int, t: MyList) extends MyList {
    def head = h
    def tail = t
    def isEmpty = false
    def add(ele: Int) = new NotEmpty(ele, this)
    def printList: String = {
      if (t.isEmpty) "" + h
      else s"$h  ${t.printList}"
    }

  }
  object NotEmpty {
    def apply(elements: Int*): MyList = {

      def createMyList(elements: Seq[Int]): MyList = {
        if (elements.isEmpty) Empty
        else new NotEmpty(elements.head, createMyList(elements.tail))
      }
      createMyList(elements)
    }
  }

}