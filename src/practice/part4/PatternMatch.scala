package practice.part4

object PatternMatch extends App{
  
  val a = 4
  a match {
    case x if x>3=> println(x)
  }
  /////////////////////////
  
  val n1 = Number(1)
  val n2 = Number(2)
  val n3 = Number(3)
  val n4 = Number(4)
  
  println(fun(Sum(n1,n2)))
  println(fun(Prod(n1,n2)))
  println(fun(Prod(n1,Sum(n2, n3))))
  //println(fun(Prod(Sum(n1, n2), n1)))
  
  trait Exp
  case class Number(n:Int) extends Exp
  case class Sum(e1:Exp, e2:Exp) extends Exp
  case class Prod(e1:Exp, e2:Exp) extends Exp

  def fun(e:Exp):String= e match {
    case Number(n)=> s"$n"
    case Sum(Number(n1),Number(n2)) => s"$n1+$n2"
    case Prod(Number(n1),Number(n2)) => s"$n1*$n2"
    case Prod(Number(n1),Sum(Number(n2), Number(n3))) => s"$n1*($n2+$n3)"
    case _ => throw new RuntimeException("unknow operation")
  }
  
  
  ///////////////////
  println
  println(funRec(Sum(n1,n2)))
  println(funRec(Prod(n1,n2)))
  println(funRec(Prod(n1,Sum(n2, n3))))
  println(funRec(Prod(Sum(n1, n2), n3)))
  println(funRec(Prod(Prod(n1, n2), n3)))
  println(funRec(Sum(Prod(n1,n2),Sum(n3, n4))))
  
  def funRec(e:Exp): String= e match{
    case Number(n) => s"$n"
    case Sum(e1:Prod, e2:Sum) => s"${funRec(e1)} + (${funRec(e2)})"
    case Sum(e1:Sum, e2:Prod) => s"(${funRec(e1)}) + ${funRec(e2)}"
    case Sum(e1:Exp, e2:Exp) => s"${funRec(e1)}+${funRec(e2)}"
    case Prod(e1:Exp, e2:Exp) => {
      def mayBeBracketProd(exp:Exp):String= exp match{
        case Prod(_,_) => funRec(exp)
        case Number(_) => funRec(exp)
        case _ => s"(${funRec(exp)})"
      }
      s"${mayBeBracketProd(e1)}*${mayBeBracketProd(e2)}"
    }
    
  }
  
}