package practice.part3

object RecustionPoc extends App{
  fun(4)
  
  def fun(n:Int):Int={
    if(n==0) 0
    else {
      var a = fun(n-1)
      println(a)
      add1(a)
    }
  }
  
  def add1(a:Int):Int={
    a+1
  }
  
  //fun(4) = 4+fun(3) = 4+6 = 10
  //fun(3) = 3+fun(2) = 3+3 = 6
  //fun(2) = 2+fun(1) = 2+1 = 3
  //fun(1) = 1+fun(0) = 1+0 = 1
  //fun(0) = 0
}