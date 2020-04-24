package practice.part1

object Functions extends App{
  
  def fun(){
   val a = 1+1
  }
  
  println("big fun is "+bigFun(3))
  
  def bigFun(a:Int):Int={
    
    def smallFun(b:Int):Int={
      println("inside smallFun "+(a+b))
      a+b
      
      }
    var x= smallFun(2)
    println("return")
    x
    
  }
  
  //recursion
  def concatStrRec(x:String, n:Int):String ={
    if(n==0) ""
    else x+""+concatStrRec(x, n-1)
  }
  
  //Tail recursion
  def concatStrTailRec(x:String, n:Int, acc:String=""):String={
    
    if(n==0) acc
    else concatStrTailRec(x, n-1, acc+""+x)
  }
    
  
  println(concatStrRec("W", 4))
  
  println(concatStrTailRec("W", 5))
  
  
  //factorial
  def factoRec(n:Int):Int={
    if(n==0) 1
    else n*factoRec(n-1)
  }
  
  def factoTailRec(n:Int, facto:Int=1):Int={
    if(n==0) facto
    else factoTailRec(n-1, facto*n)
  }
  
  println("facto rec "+factoRec(4))
  println("facto rec "+factoTailRec(4))
  
  
  //fibonacci
  def fiboRec(n:Int):Int={
    if(n==1 || n==2) 1
    else fiboRec(n-1)+fiboRec(n-2)
  }
  
  def fiboTailRec(n:Int, prevResult:Int=0, result:Int=1):Int={
    if(n==0) prevResult
    else if(n==1) result
    else fiboTailRec(n-1, prevResult, prevResult+result)
  }
  
  
  println("fiboRec "+fiboRec(4))
  println("fiboTailRec "+fiboTailRec(4))
  
  //prime
  def isPrimeRec(x:Int, d:Int=0):Boolean={
    if(x<=2) true
    else if(d==(x/2)-1) true
    else {
      println("d is "+d)
      x%((x/2)-d)!=0 && isPrimeRec(x, d+1)
    }
    
  }
  
  def isPrimeTailRec(x:Int, d:Int=0, result:Boolean=true):Boolean={
    if(result==false) result
    else if(x<=2) result
    else if ((x/2)-d==1) result
    else {
      println("tail d is "+d)
      isPrimeTailRec(x, d+1, result && x%((x/2)-d)!=0)
    }
  }
  
  println("isPrimeRec 1"+isPrimeRec(1))
  println("isPrimeRec 2"+isPrimeRec(2))
  println("isPrimeRec 8"+isPrimeRec(8))
  println("isPrimeRec 31"+isPrimeRec(31))
  
  println("isPrimeTailRec 1"+isPrimeTailRec(1))
  println("isPrimeTailRec 2"+isPrimeTailRec(2))
  println("isPrimeTailRec 8"+isPrimeTailRec(8))
  println("isPrimeTailRec 31"+isPrimeTailRec(31))
  
  
  
  
}